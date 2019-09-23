packages Pages.Admin

import Admin.modules.CPTableRows
import geb.waiting.WaitTimeoutException

class CommentPeriodListPage extends BaseAppPage {
  static content = {
    title { $('.container-fluid-padding h1') }
    projectList {
      $('table tr').tail().moduleList(CPTableRows) // tailing to skip header row 
    }
  }
}