package Pages.Admin

import Admin.modules.OrgTableRows

class OrganizationsPage extends BaseAppPage {
  static at = {isAngularReady()}
  static url = 'http://localhost:4200/admin/orgs'
  static content = {
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
      if(orgList[1]){
        orgName = orgList[1].orgName.text()
        orgList[1].clickCell()
      }
      return orgName
    }
  }



}