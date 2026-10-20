# DB backup container

The templates and jobs here are copies of [BCDevOps/backup-container](https://github.com/BCDevOps/backup-container) at tag 2.1.1.

The parameters used for the backups are on the team's [Confluence page](https://apps.nrs.gov.bc.ca/int/confluence/display/DEVGUILD/Database+Backups), which only team members can open.

Prod Mongo backups run from eagle-api's `mongodb-backup-cronjob.yaml`, not these templates.
