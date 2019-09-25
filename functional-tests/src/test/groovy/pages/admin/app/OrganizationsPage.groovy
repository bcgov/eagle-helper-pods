package Pages.Admin

import Admin.modules.OrgTableRows

class OrganizationsPage extends BaseAppPage {
  static at = {}
  static url = '/orgs'
  static content = {
    addOrgButton { $('#add-org') }
    filterSection { $('.mb-3') }
    orgList {
        $('table tr').tail().moduleList(OrgTableRows) // tailing to skip header row , is necessary?
    }
  }

  void clickFilterBoxByText(String filterText) {
    filterSection.$('input', text:filterText).click()
  }

  void clickEditOrg() {
    orgList[0].clickEdit()
  }

  String clickItem(){
    orgList[0].clickCell()
    return orgList[0].name.value()
  }

  void clickNewOrg(){
    addOrgButton.click()
  }

}