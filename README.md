# eagle-helper-pods

`eagle-helper-pods` holds deployment helpers for the EAO EPIC application suite on OpenShift.

## Live parts

| Path | Purpose |
|---|---|
| `helm/clamav` | Helm chart for the ClamAV virus scanner. |
| `openshift/secret-sync` | RBAC for the Azure job that writes secrets into the OpenShift namespaces. See its README. |
| `openshift/templates/network-security-policies` | Network policy template. |

## Older tooling

These parts have no live objects in dev, test or prod (measured 2026-10-20).

| Path | Purpose |
|---|---|
| `.jenkins` | BCDK build and deploy of `jenkins-epic`. |
| `openshift/jenkins` | Jenkins setup script and templates. |
| `openshift/setup-teardown` | Scripts that set up and tear down a full EPIC environment. |
| `openshift/templates/db-backup` | Database backup templates. |

## Related projects

The other EPIC repositories are listed on the [eagle-dev-guides wiki](https://github.com/bcgov/eagle-dev-guides/wiki).
