package Pages.Admin

import Admin.modules.CPTableRows
import geb.waiting.WaitTimeoutException

class CommentPeriodListPage extends BaseAppPage {
  static at = { isAngularReady() }
  static content = {
    pageTitle { $('.container-fluid-padding h1') }
    commentPeriodList {
      $('table tr').tail().moduleList(CPTableRows) // tailing to skip header row 
    }
  }

  void clickCommentPeriod() {
    commentPeriodList[0].clickCell()
  }
}