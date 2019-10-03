package specs

import Pages.Admin.AdminHomePage
import Pages.Admin.AdminProjectListPage
import Pages.Admin.ProjectDetailsPage


import Pages.Admin.ContactsPage
import Pages.Admin.GroupContactsPage
import Pages.Admin.OrganizationsPage
import Pages.Admin.AddEditContactPage
import Pages.Admin.AddEditOrgPage

import spock.lang.Title
import spock.lang.Stepwise
import spock.lang.Narrative
import spock.lang.Shared

@Stepwise
@Title('Test contact page and related functionality')
@Narrative('''I want to see this run in browserstack''')
class ContactsSpec extends LoggedInSpec {

  @Shared
  String currentProject
  String newFirstName = "NOT"
  String newLastName = "HUMAN"

  String newOrgName = "SHELL CORP"
  String newOrgType = "Other"
  String newAddress = "123 NOWHERE"
  String newCity = "VANCOUVER"
  String newCountry = "CANADA"

  //TODO: these should be distinct from the values above, we should come up with some way of determining the expected values
  String testFirstName ="NOT" //TODO: 
  String testLastName = "HUMAN" //TODO:
  String testOrgName = "445026 BC Limited" //TODO:


  String editedFirstName = "FAKE"

  // Adding new contact
    // verify mandatory fields are mandatory
  // void 'Adding a contact is not possible without filling in mandatory fields'(){
  //   given: 'I am logged in as an Admin user'
  //     login()
  //   and: 'I navigate to the contact creation form'
  //   when: 'I fill in all but one mandatory field'
  //   then: 'I should not be able to submit'
  // }
    // link an org works
    // verify created

  // void 'Adding a contact and linking to an org works'(){
  //   given: 'I am logged in as an Admin user'
  //     login()
  //   and: 'I create a new contact and link it to an org'
  //     to ContactsPage
  //     navBarModule.clickNewContact()
  //     at AddEditContactPage
  //     // only mandatory fields
  //     setFirstName(newFirstName)
  //     setLastName(newLastName)
  //     clickLinkOrg()
  //     at OrganizationsPage
  //     // String org = getItemName()
  //     String org = clickItem()
  //     at AddEditContactPage
  //     clickSave()
  //   when: 'I navigate to the contact in the contact browser'
  //     to ContactsPage
  //     clickShowAll()
  //   then: 'I should see that it exists and is linked to the correct org'
  //     checkContact(newFirstName,newLastName,org)
  // }
    

  // // Edit a contact
  //   // verify edit saved
  // void 'Editing a contact works'(){
  //   given: 'I am logged in as an Admin user'
  //     login()
  //   when: 'I edit the contact'
  //     to ContactsPage
  //     clickShowAll()
  //     clickEditContact(testFirstName,testLastName,testOrgName)
  //     at AddEditContactPage
  //     setFirstName(editedFirstName)
  //     clickSave()
  //     at ContactsPage
  //     clickShowAll()
  //   then: 'I should see that the edit is saved'
  //     checkContact(editedFirstName,testLastName,testOrgName)
  // }
  // Search contacts
    // verify search
  // void 'Searching for a contact works'(){
  //   given: 'I am logged in as an Admin user'
  //     login()
  //   when: 'I search for a contact'
  //     to ContactsPage
  //     setSearchTerms(editedFirstName+" "+testLastName)
  //     clickSearchButton()
  //   then: 'I should find the contact'
  //     checkContact(editedFirstName,testLastName,testOrgName)
  // }
  // Organizations
    // Create new and link to parent company
      // todo: verify mandatory fields are mandatory
      // verify created
  void 'Creating a new organization works'(){
    given: 'I am logged in as an Admin user'
      login()
    and: 'I create a new organization and link it to a parent company'
      to OrganizationsPage
      navBarModule.clickNewOrg()
      at AddEditOrgPage
      // fill mandatory fields
      setOrgName(newOrgName)
      selectOrgType(newOrgType) 
      setAddress1(newAddress)
      setCity(newCity)
      setCountry(newCountry)
      clickParentLink()
      at OrganizationsPage
      String parent = clickItem()
      clickSave()
    when: 'I navigate to the org in the org browser'
      to OrganizationsPage
    then: 'I should see that it exists and is linked to the correct parent'
      checkOrgParent(newOrgName,parent)
  }
    // edit existing org
      // verify edit saved
  void 'Editing an org works'(){
    given: 'I am logged in as an Admin user'
      login()
    when: 'I edit the org'
      to OrganizationsPage
      clickEditOrg()
      String newName = "TEST"
      setOrgName(newName)
      clickSave()
    then: 'I should see that the edit is saved'
      checkOrg(newName)
  }

  // Working Groups
    // add new group
      // verify in list
  void 'Creating a group works'(){
    given: 'I am logged in as an Admin user'
      login()
    and: 'I am on a project page'
      to AdminProjectListPage
      currentProject = getProjectName()
      clickProjectLink()
    when: 'I create a new group'
      sidebarModule.clickGroups()
      addGroup()
      String testGroupName = "testgroup"
      setGroupName(testGroupName)
      clickSave()
    then: 'I should see that it exists'
      checkGroup(testGroupName)
  }
    // select group, add/edit contacts
      // verify
  void 'Editing a group works'(){
    given: 'I am logged in as an Admin user'
      login()
    and: 'I am on a project page'
      to AdminProjectListPage
      currentProject = getProjectName()
      clickProjectLink()
    when: 'I edit a contact group'
      sidebarModule.clickGroups()
      clickWorkingGroup()
      editGroup()
      addContact()
      String contactName = selectContact()
      clickSave()

    then: 'I should be able to see the change'
      checkContact(contactName)
  }
    // todo: export spreadsheet
    // todo: copy emails 
    // delete a group
  void 'Deleting a group works'(){
    given: 'I am logged in as an Admin user'
      login()
    when: 'I delete a contact group'
    then: 'I should not be able to find it'
  }
}