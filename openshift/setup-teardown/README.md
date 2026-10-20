# Tear down a PR deployment

`pr-cleanup-helper.sh` points the params at a PR name, and `teardown-all.sh` then deletes that PR's objects. `pr-cleanup-helper.sh` uses BSD `sed -i ""` syntax, so it runs on macOS, not with GNU sed.

1. Log in to OpenShift and run `oc project` to select the target namespace.
2. Change to this directory.
3. Reset the checkout to the bcgov repository, where `upstream` is the bcgov remote:
   ```
   git checkout master
   git fetch upstream
   git reset --hard upstream/master
   ```
4. Run `./pr-cleanup-helper.sh your-pr-name`.
5. Check that `TARGET_PROJECT_SET` in `projectset.config` is `OCP4_PR`.
6. Check that the params in `params/CUSTOM_SETTINGS/OCP4_PR` carry your PR name.
7. Run `./teardown-all.sh`.
8. Run `git reset --hard upstream/master` again to revert the local changes.

See [pull_request_pipeline.md](https://github.com/bcgov/eagle-dev-guides/blob/master/dev_guides/pull_request_pipeline.md) for the PR pipeline.
