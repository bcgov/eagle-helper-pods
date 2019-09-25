package Pages.Admin

import Admin.modules.WorkingGroupTableRows
import Pages.Admin.BaseAppPage
import geb.waiting.WaitTimeoutException

class ProjectGroupsPage extends BaseAppPage {
  static at = {}
  static content = {
    selectAllButton { $('#button-sa') }
    addButton { $('#button-a') }
    editButton { $('#button-e') }
    deleteButton { $('#button-d') }
    exportButton { $('#button-ex') }
    copyEmailsButton { $('#button-ce') }
    workingGroupList {
        $('table tr').tail().moduleList(WorkingGroupTableRows) // tailing to skip header row 
    }
  }
  
  String clickWorkingGroup() {
    workingGroupList[0].clickCell()
    return workingGroupList[0].name.value()
  }

  void selectAll() {
    selectAllButton.click()
  }

  void addGroup() {
    addButton.click()
  }

  void editGroup() {
    editButton.click()
  }

  void deleteGroup() {
    deleteButton.click()
  }

  void exportGroup() {
    exportButton.click()
  }

  void copyEmails() {
    copyEmailsButton.click()
  }

  Boolean checkGroup(String name){
    for (group in workingGroupList) {
      if(group.name.value() == name){
        return true
      }
    }
    return false
  }
}