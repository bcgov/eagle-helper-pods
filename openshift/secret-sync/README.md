# secret-sync

ServiceAccount and Role for the Azure job that syncs application secrets from
`demi-kv-<env>` into this namespace. The job runs inside the Key Vault's VNet,
writes `Secret` objects here, then restarts the Deployments, DeploymentConfigs,
or CronJobs that consume them (rollout restart is a patch to the pod template
annotation).

## Apply

```
oc login ...   # personal login, not the eagle-automation context
oc apply -n 6cdc9e-<env> -f openshift/secret-sync/rbac.yaml
```

Run once per namespace (`6cdc9e-dev`, `6cdc9e-test`, `6cdc9e-prod`). **Apply
with a personal login in every environment, dev and test included** — the
`eagle-automation` service account cannot manage `roles`/`rolebindings` and
this apply will fail under that context.

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
