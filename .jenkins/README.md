# Jenkins build and deploy

This directory builds and deploys `jenkins-epic` in the `6cdc9e-tools` namespace with BCDK (BC Gov Developer Kit). Run the commands from anywhere inside the git working tree.

## Prerequisites

1. Create the GitHub and slave-user secrets `template.<Your Jenkins>-github` and `template.<Your Jenkins>-slave-user`.
2. Apply these templates from `.jenkins/openshift/` to the tools namespace:
   - `deploy-prereq.yaml`
   - `kc-uri-secrets.yaml`
   - `ns-config.yaml`
   - `jobs-config.yaml`

GitHub webhooks are created only by the prod deploy. A Jenkinsfile that runs the pipeline uses `npmw` in place of `npm`; `npmw` installs node and npm through `nvm`.

## Build

```
( cd "$(git rev-parse --show-toplevel)/.jenkins/.pipeline" && npm run build -- --pr=0 --dev-mode=true )
```

| Flag | Meaning |
|---|---|
| `--pr=0` | Pull request number to build from. |
| `--dev-mode=true` | Build the files in the working directory instead of a fresh `git clone`. |

## Deploy

```
( cd "$(git rev-parse --show-toplevel)/.jenkins/.pipeline" && npm run deploy -- --pr=0 --env=dev )
( cd "$(git rev-parse --show-toplevel)/.jenkins/.pipeline" && npm run deploy -- --pr=0 --env=prod )
```

## Clean

The clean script runs against `build` and `dev`.

```
( cd "$(git rev-parse --show-toplevel)/.jenkins/.pipeline" && npm run clean -- --pr=0 --env=build )
( cd "$(git rev-parse --show-toplevel)/.jenkins/.pipeline" && npm run clean -- --pr=0 --env=dev )
```

Never run clean against `test` or `prod`. It deletes every object in the environment, persistent volume claims included.
