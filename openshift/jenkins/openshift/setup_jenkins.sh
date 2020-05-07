#!/usr/bin/env sh

oc -n $tools process -f "$templates_url/secrets.yaml" -p GH_USERNAME=$gh_username -p GH_PASSWORD=$gh_password | oc  -n $tools create -f -
oc -n $tools process -f "$templates_url/ns-config.yaml" -p DEV=$dev -p TEST=$test -p PROD=$prod -p TOOLS=$tools | oc  -n $tools create -f -
oc -n $tools process -f "$templates_url/jobs-config.yaml" -p REPO_OWNER=$repo_owner -p REPO_NAME_ADMIN=$repo_name_admin -p REPO_NAME_PUBLIC=$repo_name_public -p REPO_NAME_API=$repo_name_api -p APP_NAME=$app_name -p APP_DOMAIN=$app_domain | oc -n $tools create -f -

oc -n $tools process -f "$templates_url/build-master.yaml" -p NAME=jenkins -p SUFFIX=-prod -p VERSION=prod-1.0.0 -p SOURCE_REPOSITORY_URL=$tools_repo_url -p SOURCE_REPOSITORY_REF=$tools_repo_ref -o yaml | oc -n $tools create -f -

oc -n $tools process -f "$templates_url/build-slave.yaml" -p NAME=jenkins -p SUFFIX=-prod -p VERSION=prod-1.0.0 -p SLAVE_NAME=main -p SOURCE_IMAGE_STREAM_TAG=jenkins:prod-1.0.0 -o yaml | oc -n $tools create -f -

oc -n $tools set triggers bc jenkins-slave-main-prod --from-image=$tools/jenkins:prod-1.0.0

oc -n $tools start-build bc/jenkins-prod

oc -n $tools process -f "$templates_url/deploy-master.yaml" -p NAME=jenkins -p SUFFIX=-prod -p VERSION=prod-1.0.0 -p ROUTE_HOST=jenkins-prod-$tools.$app_domain -p GH_USERNAME=$gh_username -p GH_PASSWORD=$gh_password -o yaml | oc -n $tools create -f -


oc -n $tools process -f "$templates_url/deploy-slave.yaml" -p NAME=jenkins -p SUFFIX=-prod -p VERSION=prod-1.0.0 -p NAMESPACE=$tools -p SLAVE_NAME=build -o yaml | oc -n $tools create -f -