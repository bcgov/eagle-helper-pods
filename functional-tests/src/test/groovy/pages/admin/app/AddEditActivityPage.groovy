package Pages.Admin

class AddEditActivityPostPage extends BaseAppPage {
  static at = {}
  static content = {
    pageTitle = { $('h2') }
    cancelButton = { $('button[type=cancel]') }
    saveButton = { $('button[type=submit]') }

    headline { $('#headline') }
    content { $('#content') }
    project { $('select[formcontrolname=project]')  }
    type { $('select[formcontrolname=type]') }

    dateAdded { $('[name=dateAdded]') }
    pcpDropDown { $('select[formcontrolname=pcp]') }
    documentUrl { $('#documentUrl') }
    publishedYes { $('#yes') }
    publishedNo { $('#no') }

    // todo deal with notes iframe
    // notes 
  }

  void setHeadline(String name) {
    headline.value(name)
  }

  void setContent(String name) {
    content.value(name)
  }

  void selectProject(String selection) {
    project.$('option', text:selection).click()
  }

  void selectType(String selection) {
    type.$('option', text:selection).click()
  }

  void setDate(String date) {
    dateAdded.value(date)
  }

  void selectPCP(String selection) {
    pcpDropDown.$('option', text:selection).click()
  }

  void setDocumentUrl(String url) {
    orgButton.click()
  }

  void setPublish(String num) {
    publishedYes.click()
  }

  void setUnPublish(String num) {
    publishedNo.click()
  }

  void clickSave() {
    saveButton.click()
  }

  void clickCancel() {
    cancelButton.click()
  }
}