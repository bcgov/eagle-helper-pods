packages Pages.Admin

import Admin.modules.TableRows
import geb.waiting.WaitTimeoutException

class HomePage extends BaseAppPage {
  static at {}
  static content {
    pageTitle { $('.search-container h2') }
    keywordInput { $('#keywordInput') }
    newProject { $('#addProject') }
    searchSubmit { $('#searchSubmit') }
    projectList {
      $('table tr').tail().moduleList(TableRows) // tailing to skip header row?
    }
  }
  private final String expectedTitle = 'Environmental Assessments in British Columbia'

  Boolean verifyTitle() {
    pageTitle.text() == expectedTitle
  }

  void clickNewProjectButton() {
    newProject.click()
  }

  void clickSearchButton() {
    searchSubmit.click()
  }

  void setSearchTerms(String searchTerms) {
    keywordInput.value(searchTerms)
  }

// todo verify what we click is the same each time
  void clickProjectLink() {
      projectList[0].clickCell()
  }
}