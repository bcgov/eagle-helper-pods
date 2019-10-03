package Pages.Admin

import Admin.modules.ActivityPostTableRows

class ActivityPostPage extends BaseAppPage {
  static at = { isAngularReady() }
  static url = 'http://localhost:4200/admin/activity'

  static content = {
    newActivityButton { $('[id=button-d]') }
    filterSection  {('[class= "btn-group btn-group-toggle"]')}
    activityPostList {
        $('table tr').tail().moduleList(ActivityPostTableRows) // tailing to skip header row 
    }
    saveButton { $('button[type=submit]') }
    resetButton { $('button[type=reset]') }
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
