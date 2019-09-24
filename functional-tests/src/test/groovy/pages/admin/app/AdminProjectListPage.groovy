package Pages.Admin

import Admin.modules.TableRows
import Pages.Admin.BaseAppPage
import geb.waiting.WaitTimeoutException

class AdminProjectListPage extends BaseAppPage {
  static at = { verifyTitle() }
  static url = 'http://localhost:4200/admin/projects'
  static content = {
    pageTitle { $('h2') }
    searchField { $('#keywordInput') }
    searchButton { $('button[type=submit]') }
    advancedSearchButton { $('#adv-search') }
    // todo get a count of projects?
    projectList {
        $('table tr').tail().moduleList(TableRows) // tailing to skip header row 
    }
  }
 
  String expectedTitle = "Environmental Assessments in British Columbia"
 
  Boolean verifyTitle() {
    pageTitle.text() == expectedTitle
  }

  void clickSearchButton() {
    searchButton.click()
  }

  void clickAdvancedSearchButton() {
    advancedSearchButton.click()
  }

  // void setSearchTerms(String searchTerms) {
  //   searchField.value(searchTerms)
  // }

  String getProjectName() {
    return projectList[0].name.value()
  }
// todo verify what we click is the same each time
  void clickProjectLink() {
    projectList[0].clickCell()
  }
}