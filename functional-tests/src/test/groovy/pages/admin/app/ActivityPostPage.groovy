package Pages.Admin

import Admin.modules.ActivityPostsTableRows

class ActivityPostPage extends BaseAppPage {
  static at = {}
  static url = '/activity'

  static content = {
    newActivityButton { $('[id=button-d]') }
    filterSection = $('[class= "btn-group btn-group-toggle"]')
    activityPostList {
        $('table tr').tail().moduleList(ActivityPostsTableRows) // tailing to skip header row 
    }
    saveButton = { $('button[type=submit]') }
    resetButton = { $('button[type=reset]') }
  }

  void clickNewActivityPost() {
    newActivityButton.click()
  }

  void clickEditActivityPost() {
    activityPostList[0].clickAction()
  }

  void clickFilterBoxByText(String filterText) {
    filterSection.$('input', text:filterText).click()
  }

  void clickSearch() {
    saveButton.click()
  }

  void clickReset() {
    resetButton.click()
  }

}
