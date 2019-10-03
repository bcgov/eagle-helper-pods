package specs

import Pages.Admin.AdminHomePage
import Pages.Admin.AdminProjectListPage
import Pages.Admin.ProjectDetailsPage
import Pages.Admin.CommentPeriodListPage
import Pages.Admin.AdminCommentPeriodPage
import Pages.Admin.AddEditCommentPeriodPage
import Pages.Admin.CommentBannerPage
import Pages.Public.HomePage

import Pages.Public.WelcomePage
import Pages.Public.ProjectListPage
import Pages.Public.ProjectDetailsPage

import spock.lang.Title
import spock.lang.Stepwise
import spock.lang.Narrative
import spock.lang.Shared

@Stepwise
@Title('Test comment period creation and comment submission')
@Narrative('''I want to see this run in browserstack''')
class CommentPeriodSpec extends LoggedInSpec {

  // todo add cleanup step to clean up created CPs
  @Shared
  String currentProject

  void 'An unpublished comment period is not visible from public'() {
    given: 'I am logged in as an Admin user'
      login()
      // todo break this up?
    and: 'I create a new comment period and set the date to the future'
      to AdminProjectListPage
      currentProject = getProjectName()
      clickProjectLink()
      sidebarModule.clickCommentPeriod()
      navBarModule.clickNewCP()
      at AddEditCommentPeriodPage
      setStartDateFuture()
      setEndDateFuture()
      // verify if this test case should be pub/unpub
      selectPublishState("Unpublished")
      String commentInfo = "Sample information for a comment period"
      enterInformation(commentInfo)
      String milestone = "Section 7"
      selectMilestone(milestone)
      clickSave()
    when: 'I verify the details in the admin comment period banner'
      at CommentPeriodListPage
      clickCommentPeriod()
      at AdminCommentPeriodPage
      publishState.text() == "Not Published"
      milestone.value() == "Section 7"
    and: 'I find the project on the Public page'
      to HomePage
      at WelcomePage
      clickCloseButton() // close welcome splash
      at HomePage 
      // clickMenuItem([text:'List of Projects'])
      headerModule.clickListProjects()
      at ProjectListPage
      setSearchTerms(currentProject)
      scrollDown()
      clickProjectLink()
      at ProjectDetailsPage
      clickCommentTab()
    then: 'I should see no comment periods'
      commentTab.getCommentText() == "No comment periods are currently scheduled for this project."

  }

  void 'A published future comment period is pending on public'() {
    // todo login each time (do we clean up and reset browser each test? each suite?)
    // given: 'I am logged in as an Admin user'
    //   login()
    given: 'I am at Admin home page'
      to AdminProjectListPage
    and: 'I create a new comment period and set the date to the future'
      currentProject = getProjectName()
      clickProjectLink()
      sidebarModule.clickCommentPeriod()
      navBarModule.clickNewCP()
      at AddEditCommentPeriodPage
      setStartDateFuture()
      setEndDateFuture()
      selectPublishState("Published")
      String commentInfo = "Sample information for a comment period"
      enterInformation(commentInfo)
      String milestone = "Section 7"
      selectMilestone(milestone)
      clickSave()
    when: 'I verify the details in the admin comment period banner'
      at CommentPeriodListPage
      clickCommentPeriod()
      at AdminCommentPeriodPage
      publishState.text() == "Published"
      milestone.value() == "Section 7"
    and: 'I find the project on the Public page'
      to HomePage
      at WelcomePage
      clickCloseButton() // close welcome splash
      at HomePage
      headerModule.clickListProjects()
      at ProjectListPage
      setSearchTerms(currentProject)
      scrollDown()
      clickProjectLink()
      at ProjectDetailsPage
      clickCommentTab()
    then: 'I verify the comment period is pending'
    // todo not pick the fist one everytime
      commentPeriods[0].cpStatus[0].text() == "Pending"

  }

  void 'A published comment period is visible from public'() {
    // given: 'I am logged in as an Admin user'
    //   login()
    given: 'I am at the Admin home page'
      to AdminProjectListPage
    and: 'I create a new comment period and set the date to the future'
      currentProject = getProjectName()
      clickProjectLink()
      sidebarModule.clickCommentPeriod()
      navBarModule.clickNewCP()
      at AddEditCommentPeriodPage
      setStartDateNow()
      setEndDateNow()
      selectPublishState("Published")
      String commentInfo = "Sample information for a comment period"
      enterInformation(commentInfo)
      String milestone = "Section 7"
      selectMilestone(milestone)
      clickSave()
    when: 'I verify the details in the admin comment period banner'
      at CommentPeriodListPage
      clickCommentPeriod()
      at AdminCommentPeriodPage
      publishState.text() == "Published"
      milestone.value() == "Section 7"
    and: 'I find the project on the Public page'
      to HomePage
      at WelcomePage
      clickCloseButton() // close welcome splash
      at HomePage
      headerModule.clickListProjects()
      at ProjectListPage
      setSearchTerms(currentProject)
      scrollDown()
      clickProjectLink()
      at ProjectDetailsPage
      clickCommentTab()
    then: 'I verify the comment period is open'
      commentPeriods[0].cpStatus[0].text() == "Open"

  }

  // create cp on project x
  // publish
  // verify details on public
  // submit an anon comment
  // submit comment with name
  // verify comments in admin and publish
  // verify in public anon is anon
  // verify in public that name is shown
  void 'Published comment respects anonymity settings'() {

  }

  // create cp on project x
  // publish
  // submit comment
  // reject comment
  // verify on public comment is not shown
  void 'Rejected comment does not display on public'() {

  }
}