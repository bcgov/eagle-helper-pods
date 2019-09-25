package Pages.Admin

import Admin.modules.TableRows
import Pages.Admin.BaseAppPage
import geb.waiting.WaitTimeoutException

class AdminHomePage extends BaseAppPage {
  static at = { verifyTitle() }
  static content = {
    pageTitle { $('h1') }
    keywordInput { $('#keywordInput') }
    searchSubmit { $('button[type=submit') }
    // todo add selectors for docs/projects/vc and necessary tableRow modules
    projectList {
      $('table tr').tail().moduleList(TableRows) // tailing to skip header row?
    }
    docsRadio { $('input', text:'Documents') }
    projectsRadio { $('input', text:'Projects') }
    vcRadio { $('input', text:'Valued Components')}
  }
  private final String expectedTitle = 'Search Environmental Assessment Projects'

  Boolean verifyTitle() {
    pageTitle.text() == expectedTitle
  }

  void clickSearchButton() {
    searchSubmit.click()
  }

  void setSearchTerms(String searchTerms) {
    keywordInput.value(searchTerms)
  }

// todo update based on new selectors and page content
  // void clickProjectLink() {
  //     projectList[0].clickCell()
  // }
}