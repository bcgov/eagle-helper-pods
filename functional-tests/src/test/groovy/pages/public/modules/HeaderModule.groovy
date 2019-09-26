package Public.modules

import geb.Module

import geb.waiting.WaitTimeoutException

/**
 * Contains objects and methods for interacting with the global header bar.
 */
class HeaderModule extends Module {
  static content = {
    bcLogo { $('.navbar-brand__title') }
    // todo update tags/ids
    findProjects { $('#projectSearch') }
    listProjects { $('#projectList') }
    eaProcess {}
    contactUs { $('#contact') }
    // todo verify this selector is right
    headerNavigationBar { $('#header #mainNav .navbar-nav') }
  }

  void clickMenuItem(item) {
    headerNavigationBar.$('a', item).click()
  }

  void clickListProjects() {
    listProjects.click()
  }
}
