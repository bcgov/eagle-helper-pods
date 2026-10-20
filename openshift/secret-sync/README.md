# secret-sync

`secret-sync` holds the ServiceAccount and Role for the Azure job that syncs application secrets from `demi-kv-<env>` into this namespace. The job runs inside the Key Vault's VNet and writes `Secret` objects here. It then stamps the pod template of the workloads that consume them, so their pods pick up the new value.

For a Deployment, the stamp is a patch to `spec.template.metadata.annotations`, which rolls the pods. A CronJob has no rollout, so the patch goes to `spec.jobTemplate.spec.template.metadata.annotations`. The next scheduled run starts with the new secret, and running pods from an earlier run are left alone.

## Apply

Each namespace has its own file, because the Role names the exact Secrets and workloads it may touch and those differ per environment. The names come from the sync job's mapping. Keep the two in step.

```
oc login ...   # personal login, not the eagle-automation context
oc apply -n 6cdc9e-dev  -f openshift/secret-sync/rbac-dev.yaml
oc apply -n 6cdc9e-test -f openshift/secret-sync/rbac-test.yaml
oc apply -n 6cdc9e-prod -f openshift/secret-sync/rbac-prod.yaml
```

Apply with a personal login in every environment, dev and test included. The `eagle-automation` service account cannot manage `roles` or `rolebindings`, so the apply fails under that context.

Every Secret named in the Role must already exist in the namespace before the sync runs. The Role has no `create` verb, so a new mapping entry needs its Secret created by hand first:

```
oc create secret generic <name> --from-literal=<key>=placeholder -n <ns>
```

Then add the entry to the sync job's mapping and to the matching `rbac-<env>.yaml`. The sync overwrites the placeholder with the real value.

## Read the token (once)

The `secret-sync-token` Secret holds a long-lived token for the `secret-sync` ServiceAccount. Read it once after applying:

```
oc get secret secret-sync-token -n <ns> -o jsonpath='{.data.token}' | base64 -d
```

Store the value by hand, from the devbox, in the Azure Key Vault secret `openshift-token-<env>`. Never commit the token or print it outside this step.

## Revoke

```
oc delete secret secret-sync-token -n <ns>
```

OpenShift issues a fresh token if the Secret is recreated. Update the Key Vault entry afterward.
