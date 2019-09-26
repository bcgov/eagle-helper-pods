package Admin.modules

import geb.Module
import geb.waiting.WaitTimeoutException

/**
 * Contains objects and methods for interacting with the global header bar.
 */
class SideBarModule extends Module {
  static content = {
    allProjects { $('#allProjects') }
    activityPosts { $("#activityPosts") }
    contacts { $('#contacts') }
    orgs { $("#orgs") }
    settings { $("#settings") }
    eguide { $("#eguide") }
    projectCP { $("#comment-periods") }
    projectCompliance { $("#compliance") }
    projectDocuments { $("#documents") }
    projectGroups { $("#groups") }
    projectUpdates { $("#updates") }
    projectVC { $("#value-components") }
    sideBar { $('.sidebar .full-nav') }
  }

  void clickAllProjects() {
    allProjects.click()
  }

  void clickActivityPosts() {
    activityPosts.click()
  }

  void clickContacts() {
    contacts.click()
  }

  void clickOrganizations() {
    orgs.click()
  }

  void clickSettings() {
    settings.click()
  }

  // external link, might be removed from EPIC?
  // void clickEguide() {
  //   eguide.click()
  // }

  void clickCommentPeriod() {
    sideBar.$("#comment-periods").click()
  }

  void clickCompliance() {
    projectCompliance.click()
  }

  void clickDocuments() {
    projectDocuments.click()
  }

  void clickGroups() {
    projectGroups.click()
  }

  void clickUpdates() {
    projectUpdates.click()
  }

  void clickVC() {
    projectVC.click()
  }
}