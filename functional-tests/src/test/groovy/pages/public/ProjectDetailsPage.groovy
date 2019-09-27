package Pages.Public

import Public.modules.CommentPeriodModule

class ProjectDetailsPage extends BaseAppPage {
  static at = { isAngularReady() }
//   todo pass in project id? ie. /p/58851158aaecd9001b81e83f/projectDetails
//  regex ex: browser.getCurrentUrl() =~ /(admin)?\/users\/edit\/[0-9]+$/
  static url = '/p'
  static content = {
    pageTitle { $('.project .project-info h1') }
    certificate { $('.project .project-info .ea-decision') }
    // create module for basic info, ngcontent has no unique class/id to use
    legislation { $('.legislation-button') }
    commentPeriodDate { $('h5') }
    commentPeriodStatus { $('#cp-status') }
    viewCommentPeriod { $('#view-cp') }
    submitComment { $('#submit-c') }
    commentText { $('#no-cp') }
    
    projectDetailsTab { $('a[ng-reflect-router-link=project-details]') }
    projectCommentButton(wait:true) { $('a[ng-reflect-router-link=commenting]') }
    projectDocumentsTab { $('a[ng-reflect-router-link=documents]') }
    commentTab { module(CommentPeriodModule) }
  }

  void clickViewPeriod() {
    viewCommentPeriod.click()
  }

  void clickSubmitComment() {
    submitComment.click()
  }

  void clickProjectDetailsTab() {
    projectDetailsTab.click()
  }

  void clickCommentTab() {
    projectCommentButton.click()
  }

  void clickDocumentsTab() {
    projectDocumentsTab.click()
  }


}