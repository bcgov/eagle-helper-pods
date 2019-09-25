package Pages.Admin

import Admin.modules.ContactsTableRows

class ContactsPage extends BaseAppPage {
  static at = {}
  static content = {
    newContactButton { $('#add-contact') }
    searchField { $('#keywordInput') }
    searchButton { $('button[type=submit]') }
    contactList {
        $('table tr').tail().moduleList(ContactsTableRows) // tailing to skip header row 
    }
  }

  void clickNewContact() {
    newContactButton.click()
  }

  void clickEditContact() {
    contactList[0].clickAction()
  }

  Boolean checkContact(String firstName, String lastName, String org){
    for (contact in contactList) {
      if(contact.name.value() == firstName + " " + lastName && contact.organization.value() == org){
        return true
      }
    }
    return false

  }

  void clickSearchButton() {
    searchButton.click()
  }

  void setSearchTerms(String searchTerms) {
    searchField.value(searchTerms)
  }

}
