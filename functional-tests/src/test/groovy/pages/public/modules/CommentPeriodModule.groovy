package Public.modules

import geb.Module
import geb.waiting.WaitTimeoutException

class CommentPeriodModule extends Module {
  static at = { isAngularReady() }
  static content = {
    pageTitle { $('h1') }
    commentPeriodStatus { $('#cp-status') }
    commentPeriodDate { $('#cp-date') }
    submitComment { $('#submit-c') }
    backButton { $('#back') }
    noComments { $('#no-comm') }
  }

  String getCommentText() {
    return noComments.text()
  }
}