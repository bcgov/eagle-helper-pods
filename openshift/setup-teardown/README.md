# Using pr-cleanup-helper Script

log into OpenShift

```oc project``` to target namespace

```cd``` to this directory

run ```git checkout master```
run ```git fetch upstream```
run ```git reset --hard upstream/master```

where upstream is the bcgov repository

run ```./pr-cleanup-helper.sh your-pr-name```

check that ```TARGET_PROJECT_SET``` in projectset.config is set to ```PR```

check some of the params in the pr directory and make sure they've been changed to match the pr name that you provided

run  ```./teardown-all.sh```

run ```git reset --hard upstream/master``` again to revert changes to local env

# Other PR Based Pipeline Documentation

https://github.com/bcgov/eagle-dev-guides/blob/master/dev_guides/pull_request_pipeline.md