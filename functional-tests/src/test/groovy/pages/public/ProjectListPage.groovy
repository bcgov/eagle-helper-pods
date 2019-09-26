package Pages.Public

import Public.modules.TableRows
import geb.waiting.WaitTimeoutException
import org.openqa.selenium.Keys

class ProjectListPage extends BaseAppPage {
  static at = { isAngularReady() && verifyTitle() }
  static url = '/projects-list'
  static content = {
    pageTitle { $('h1') }
    searchButton { $('button[type=submit]') }
    advancedSearchButton { $('#show-advanced-search-button')}
    searchField { $('#keywordInput') }
    // todo get a count of projects?
    projectList(wait:true) {
        $('table tr').tail().moduleList(TableRows) // tailing to skip header row 
    }
    body { $('#top') }
  }
  private final String expectedTitle = 'Environmental Assessments in British Columbia'

  Boolean verifyTitle() {
    pageTitle.text() == expectedTitle
  }

  void clickSearchButton() {
    searchButton.click()
  }

  void clickAdvancedSearchButton() {
    advancedSearchButton.click()
  }

  void setSearchTerms(String searchTerms) {
    searchField.value(searchTerms)
  }

  void scrollDown() {
    for (int i = 0; i <= 6; i++) {
      body << Keys.ARROW_DOWN
    }
  }
// todo click by name?
  void clickProjectLink() {
      projectList[0].clickCell()
  }
}