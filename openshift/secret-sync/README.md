# secret-sync

ServiceAccount and Role for the Azure job that syncs application secrets from
`demi-kv-<env>` into this namespace. The job runs inside the Key Vault's VNet,
writes `Secret` objects here, then stamps the pod template of the workloads that
consume them so their pods pick the new value up.

For a Deployment that stamp is a patch to `spec.template.metadata.annotations`,
which rolls the pods. A CronJob has no rollout: the patch goes to
`spec.jobTemplate.spec.template.metadata.annotations`, and the next scheduled
run starts with the new secret. Running pods from an earlier run are left alone.

## Apply

One file per namespace, because the Role names the exact Secrets and workloads
it may touch and those differ per environment. The names come from the sync
job's mapping; keep them in step.

```
oc login ...   # personal login, not the eagle-automation context
oc apply -n 6cdc9e-dev  -f openshift/secret-sync/rbac-dev.yaml
oc apply -n 6cdc9e-test -f openshift/secret-sync/rbac-test.yaml
oc apply -n 6cdc9e-prod -f openshift/secret-sync/rbac-prod.yaml
```

**Apply with a personal login in every environment, dev and test included** —
the `eagle-automation` service account cannot manage `roles`/`rolebindings` and
this apply will fail under that context.

Every Secret named in the Role must already exist in the namespace — they all
do today. The Role has no `create` verb, so a new mapping entry needs its
Secret created by hand first:

```
oc create secret generic <name> --from-literal=<key>=placeholder -n <ns>
```

Add it to the sync job's mapping and the matching `rbac-<env>.yaml` after
that; the sync then overwrites the placeholder with the real value.

## Read the token (once)

The `secret-sync-token` Secret holds a long-lived token for the `secret-sync`
ServiceAccount. Read it once after applying:

```
oc get secret secret-sync-token -n <ns> -o jsonpath='{.data.token}' | base64 -d
```

Store the value by hand, from the devbox, into the Azure Key Vault secret
`openshift-token-<env>`. Never commit the token or print it outside this step.

## Revoke

```
oc delete secret secret-sync-token -n <ns>
```

OpenShift issues a fresh token if the Secret is recreated; update the Key
Vault entry afterward.
