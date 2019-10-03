package Public.modules

import geb.Module
import geb.waiting.WaitTimeoutException

class CommentPeriodRowsModule extends Module {
  static at = { isAngularReady() }
  static content = {
    cpStatus { $('.title') }
    commentInfo { $('.commentInfo') }
    // todo add id to get date?
    dateRange { $('.mb-0') }
  }

  void clickCommentPeriod() {
    commentInfo.click()
  }
}