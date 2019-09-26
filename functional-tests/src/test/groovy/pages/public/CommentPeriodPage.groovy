package Pages.Public

class CommentPeriodPage extends BaseAppPage {
  static at = { isAngularReady() }
  static content = {
    pageTitle { $('h1') }
    commentPeriodStatus { $('#cp-status') }
    commentPeriodDate { $('#cp-date') }
    submitComment { $('#submit-c') }
    backButton { $('#back') }
    noComments(wait:true) { $('#no-comm') }
  // todo add selector for tab box?
  }

  String getCommentText() {
    System.out.format("Comment text: %s", noComments.text())
    return noComments.text()
  }
}