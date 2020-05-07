# EPIC Jenkins
This Jenkins setup and configuration is based on the example provided by [nr-showcase-devops-tools](https://github.com/bcgov/nr-showcase-devops-tools/tree/master/tools).

The configuration has been modified to support EPIC's multi-repository structure.

## Prerequisites

All of the following instructions and examples are written for a *nix based system.  If you are on Windows, you may have to make some adjustments; particularly around piping `|`, setting environment variables and environment variable substitution. We suggest that Windows users consider using the Windows Subsystem for Linux (WSL) in order to minimize potential conflicts with the local environment. Check the [Microsoft Documentation](https://docs.microsoft.com/en-us/windows/wsl/install-win10) for more information on how to install WSL on your system.

You should have your 4 OpenShift namespaces (dev, test, prod, tools) and you should have admin access. We assume that whomever is running these commands has admin privileges to the 4 OpenShift projects.

You will need to install the OpenShift command line tools and have them in your path.  Login to OpenShift via web console, click on the Help icon (question mark), click Command Line Tools and follow the instructions.

You will need a github account and token (preferrably a team shared account) with access to your repo: [New Personal Access Token](https://github.com/settings/tokens/new?scopes=repo,read:user,user:email,admin:repo_hook).

## Environment Variables

For simplicity sake, we will be setting all our project specific values as environment variables and substitute them into the oc commands.  The following are just examples and will need to be changed to your specific projects/namespaces, credentials, etc.

### DevOps Tools Repository

Configure environment variables to access and run the Jenkins tools scripts.

```sh
export tools_repo_owner=bcgov
export tools_repo_name=eagle-helper-pods
export tools_repo_url=https://github.com/$tools_repo_owner/$tools_repo_name.git
export tools_repo_ref=master
export tools_repo_raw=https://raw.githubusercontent.com/$tools_repo_owner/$tools_repo_name/$tools_repo_ref
export templates_url=$tools_repo_raw/tools/jenkins/openshift
```

### Namespace Enumeration

Configure the 4 environment namespaces for your application.

```sh
export ns_prefix=<your-namespace-prefix>
export tools=$ns_prefix-tools
export dev=$ns_prefix-dev
export test=$ns_prefix-test
export prod=$ns_prefix-prod
```

### Github Repository and Credentials for Application

Configure your application repository details.  This is the application that will be built and deployed by the pipeline.

```sh
export gh_username=<github account>
export gh_password=<personal access token, see above>
export repo_owner=<your github account>
export repo_name=<your application repo>
```

### Application Details

Configure any remaining application specific details here.

```sh
export app_name=<your application acronym/short name>
export app_domain=pathfinder.gov.bc.ca
```

### Login to OpenShift

Once you have all of the environment variables defined, login to Openshift via web console, click your login name at top tight and click "Copy Login Command".  Go to your terminal, go to your project root and paste the copy command.

### Go to Tools Namespace/Project

Once logged into the OpenShift console, go to your tools project.  Each oc command should use the `-n <NAMESPACE>` option, but being in your tools project is just another safeguard.

```sh
oc project $tools
```

### Customise Jenkins Jobs

* **init.groovy.d/003-create-jobs.groovy**

    Edit this file to add or remove pipeline jobs as needed. Each job is defined by an *Expando* block. When Jenkins is started up this script will be run and jobs created. See [nr-showcase-devops-tools](https://github.com/bcgov/nr-showcase-devops-tools/tree/master/tools) for more details.

### Build & Deploy

Ensure all the required environment variables above have been sourced to your environment and the active OpenShift project is your tools namespace. Then:

```sh
./setup_jenkins.sh
```

If there were no errors, wait a couple minutes then check your tools namespace for deployments.

### Delete Deployment


