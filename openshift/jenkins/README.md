# EPIC Jenkins

This directory sets up Jenkins for EPIC's multi-repository structure. It is based on [nr-showcase-devops-tools](https://github.com/bcgov/nr-showcase-devops-tools/tree/master/tools).

## Prerequisites

- A Linux or macOS shell. On Windows, use the Windows Subsystem for Linux (WSL), because the commands rely on pipes and environment variable substitution.
- The `oc` command line tools in your path.
- Admin access to the four OpenShift namespaces: dev, test, prod and tools.
- A GitHub account and a [personal access token](https://github.com/settings/tokens/new?scopes=repo,read:user,user:email,admin:repo_hook) with access to the application repos. Use a shared team account.

## Environment variables

`setup_jenkins.sh` reads its settings from environment variables. Replace the example values with your own.

Tools repository:

```sh
export tools_repo_owner=bcgov
export tools_repo_name=eagle-helper-pods
export tools_repo_url=https://github.com/$tools_repo_owner/$tools_repo_name.git
export tools_repo_ref=master
export tools_repo_raw=https://raw.githubusercontent.com/$tools_repo_owner/$tools_repo_name/$tools_repo_ref
export templates_url=$tools_repo_raw/openshift/jenkins/openshift
```

Namespaces:

```sh
export ns_prefix=<your-namespace-prefix>
export tools=$ns_prefix-tools
export dev=$ns_prefix-dev
export test=$ns_prefix-test
export prod=$ns_prefix-prod
```

GitHub credentials and application repos:

```sh
export gh_username=<github account>
export gh_password=<personal access token>
export repo_owner=<your github account>
export repo_name_admin=<admin repo>
export repo_name_public=<public repo>
export repo_name_api=<api repo>
```

Application details:

```sh
export app_name=<your application acronym/short name>
export app_domain=<cluster apps domain>
```

## Build and deploy

1. Log in to OpenShift and run `oc project $tools`. Each `oc` command in the script passes `-n $tools`; the active project is a second safeguard.
2. Edit `openshift/jenkins/docker/contrib/jenkins/configuration/init.groovy.d/003-create-jobs.groovy` to add or remove pipeline jobs. Each job is an `Expando` block, and Jenkins creates the jobs at start-up.
3. Source the environment variables above, then run the setup script:

   ```sh
   openshift/jenkins/openshift/setup_jenkins.sh
   ```

4. Wait a few minutes, then check the tools namespace for deployments.
