package specs

import Pages.Admin.AdminHomePage
import Pages.Admin.AdminProjectListPage
import Pages.Admin.ProjectDetailsPage
import Pages.Public.HomePage
import Pages.Public.WelcomePage
import Pages.Public.ProjectListPage
import Pages.Public.ProjectDetailsPage

import Pages.Public.ContactsPage
import Pages.Public.GroupContactsPage
import Pages.Public.OrganizationsPage

import spock.lang.Title
import spock.lang.Stepwise
import spock.lang.Narrative
import spock.lang.Shared

@Stepwise
@Title('Test comment period creation and comment submission')
@Narrative('''I want to see this run in browserstack''')
class CommentPeriodSpec extends LoggedInSpec {

  @Shared
  String currentProject
  String firstName = "NOT"
  String lastName = "HUMAN"

  // Adding new contact
    // verify mandatory fields are mandatory
  void 'Adding a contact is not possible without filling in mandatory fields'(){
    given: 'I am logged in as an Admin user'
      login()
    and: 'I navigate to the contact creation form'
    when: 'I fill in all but one mandatory field'
    then: 'I should not be able to submit'
  }
    // link an org works
    // verify created
  void 'Adding a contact and linking to an org works'(){
    given: 'I am logged in as an Admin user'
      login()
    and: 'I create a new contact and link it to an org'
      to ContactsPage
      clickNewContact()
      setFirstName(firstName)
      setLastName(lastName)
      String org = clickLinkOrg()
      clickItem()
      clickSave()
    when: 'I navigate to the contact in the contact browser'
      to ContactsPage
    then: 'I should see that it exists and is linked to the correct org'
      checkContact(firstName,lastName,org)
  }
    

  // Edit a contact
    // verify edit saved
  void 'Editing a contact works'(){
    given: 'I am logged in as an Admin user'
      login()
    and: 'I create a new contact'
      to ContactsPage
      clickNewContact()
      setFirstName(firstName)
      setLastName(lastName)
      String org = clickLinkOrg()
      clickItem()
      clickSave()
    when: 'I edit the contact'
      to ContactsPage
      clickEditContact()
      String newName = "FAKE"
      setFirstName(newName)
      clickSave()
    then: 'I should see that the edit is saved'
      checkContact(newName,lastName,org)
  }
  // Search contacts
    // verify search

  // Organizations
    // Create new and link to parent company
      // verify mandatory fields are mandatory
      // verify created
    // edit existing org
      // verify edit saved

  // Working Groups
    // add new group
      // verify in list
    // select group, add/edit contacts
      // verify
    // export spreadsheet
    // copy emails 
    // delete a group




  void 'An unpublished comment period is not visible from public'() {
    given: 'I am logged in as an Admin user'
      login()
      // todo break this up?
    and: 'I create a new comment period and set the date to the future'
      to AdminProjectListPage
      currentProject = clickProjectLink()
      clickCommentPeriod()
      clickNewCP()
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
      getPublishState() == "Not Published"
      getMilestone() == milestone
    and: 'I find the project on the Public page'
      to HomePage
      clickCloseButton() // close welcome splash
      clickMenuItem([text:'List of Projects'])
      setSearchTerms(currentProject)
      // todo parameterize
      clickProjectLink()
    then: 'I should see no comment periods'
      // at ProjectDetailsPage
      clickCommentTab()
      getCommentText() == "No comment periods are currently scheduled for this project."

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