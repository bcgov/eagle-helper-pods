source ./lib/extended-oc.sh
source ./projectset.config

DEFAULT_TEMPLATE=DEFAULT_TEMPLATE
PROJECT_SET=PROJECT_SETS/${TARGET_PROJECT_SET}
PARAMS_FOLDER=./params/${PROJECT_SET}/
ENV_ARGS_FILE=${PARAMS_FOLDER}environment.config
API_ARGS_FILE=${PARAMS_FOLDER}api/api.config
ADMIN_ARGS_FILE=${PARAMS_FOLDER}admin/admin.config
PUBLIC_ARGS_FILE=${PARAMS_FOLDER}public/public.config

loadEnvSettings() {
    checkOpenshiftSession;

    source ./params/DEFAULT_TEMPLATE/environment.config;

    checkFileExists "config" ${ENV_ARGS_FILE};
    source ${ENV_ARGS_FILE};

    checkProjectExists ${TOOLS_PROJECT};
    checkProjectExists ${TARGET_PROJECT};
}

cleanApi() {
    checkOpenshiftSession;
    
    source ./params/DEFAULT_TEMPLATE/api/api.config;

    checkFileExists "config" ${API_ARGS_FILE};
    source ${API_ARGS_FILE};

    echo -e \\n"clean-api: Removing builds."\\n;

    cleanProject ${API_NODEJS_BUILD_NAME} ${TOOLS_PROJECT}

    echo -e \\n"clean-api: Removing deployments."\\n;

    cleanProject ${API_NODEJS_DEPLOYMENT_NAME} ${TARGET_PROJECT}
    cleanProject ${API_MONGODB_DEPLOYMENT_NAME} ${TARGET_PROJECT}
    cleanProject ${API_MINIO_DEPLOYMENT_NAME} ${TARGET_PROJECT}

    echo -e \\n"clean-api: Removing storage."\\n;

    _cli_output=$(oc -n ${TARGET_PROJECT} delete pvc ${API_MONGODB_DEPLOYMENT_NAME}-data 2>&1)
    outputRelevantOnly "${_cli_output}"

    _cli_output=$(oc -n ${TARGET_PROJECT} delete pvc ${API_MINIO_DEPLOYMENT_NAME}-docs-pvc-gf 2>&1)
    outputRelevantOnly "${_cli_output}"

    echo -e \\n"clean-api: Completed clean."\\n
}

cleanAdmin() {
    checkOpenshiftSession;
    
    source ./params/DEFAULT_TEMPLATE/admin/admin.config;

    checkFileExists "config" ${ADMIN_ARGS_FILE};
    source ${ADMIN_ARGS_FILE};

    echo -e \\n"clean-admin: Removing builds."\\n;

    cleanProject ${ADMIN_ANGULAR_BUILDER_BUILD_NAME} ${TOOLS_PROJECT};
    cleanProject ${ADMIN_NGINX_RUNTIME_BUILD_NAME} ${TOOLS_PROJECT};
    cleanProject ${ADMIN_ANGULAR_ON_NGINX_BUILD_NAME} ${TOOLS_PROJECT};

    echo -e \\n"clean-admin: Removing deployments."\\n;

    cleanProject ${ADMIN_ANGULAR_ON_NGINX_DEPLOYMENT_NAME} ${TARGET_PROJECT};

    echo -e \\n"clean-admin: Completed clean."\\n
}

cleanPublic() {
    checkOpenshiftSession;
    
    source ./params/DEFAULT_TEMPLATE/public/public.config;

    checkFileExists "config" ${PUBLIC_ARGS_FILE};
    source ${PUBLIC_ARGS_FILE};

    echo -e \\n"clean-public: Removing builds."\\n;

    cleanProject ${PUBLIC_ANGULAR_BUILDER_BUILD_NAME} ${TOOLS_PROJECT};
    cleanProject ${PUBLIC_NGINX_RUNTIME_BUILD_NAME} ${TOOLS_PROJECT};
    cleanProject ${PUBLIC_ANGULAR_ON_NGINX_BUILD_NAME} ${TOOLS_PROJECT};

    echo -e \\n"clean-public: Removing deployments."\\n;

    cleanProject ${PUBLIC_ANGULAR_ON_NGINX_DEPLOYMENT_NAME} ${TARGET_PROJECT};

    echo -e \\n"clean-public: Completed clean."\\n
}

loadEnvSettings $(<${ENV_ARGS_FILE});
cleanApi $(<${API_ARGS_FILE});
cleanAdmin $(<${ADMIN_ARGS_FILE});
cleanPublic $(<${PUBLIC_ARGS_FILE});
