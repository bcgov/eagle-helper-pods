#!/usr/bin/env sh

# intended for manual cleanup of PR based deploys
setParamsToPR(){
	local _deploy_name=$1

	find . -name "projectset.config" -exec sed -i "" -e "s/^\( *TARGET_PROJECT_SET=*\)[^ ]*\(.*\)*$/\1PR\2/" {} \;

	# set unique name (ie. pr-branchName) for pr builds & deployments
	cd params/CUSTOM_SETTINGS/PR
	find . -name "*.params" -exec sed -i "" -e "s/pr-placeholder/${_deploy_name}/g" {} \;
	find . -name "*.config" -exec sed -i "" -e "s/pr-placeholder/${_deploy_name}/g" {} \;
}

echo "replacing pr-placeholder with $1"
setParamsToPR $1