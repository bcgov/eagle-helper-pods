package Pages.Admin

import Admin.modules.OrgTableRows

class OrganizationsPage extends BaseAppPage {
  static at = {isAngularReady()}
  static url = '/orgs'
  static content = {
    addOrgButton { $('#add-org') }
    filterSection { $('.mb-3') }
    orgList{
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
    return waitFor{
      String orgName = ""
      if(orgList[0]){
        orgName = orgList[0].orgName.text()
        orgList[0].clickCell()
      }
      return orgName
    }
  }

  void clickNewOrg(){
    addOrgButton.click()
  }

}