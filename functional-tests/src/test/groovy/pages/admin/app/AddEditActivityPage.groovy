package Pages.Admin
import geb.module.TextInput

class AddEditActivityPostPage extends BaseAppPage {
  static at = { isAngularReady() }
  static url = ''
  static content = {
    pageTitle { $('h2') }
    cancelButton { $('button[type=cancel]') }
    saveButton { $('button[type=submit]') }

    iframeName { $('input', id:'fname').module(TextInput) }

    headline { $('#headline') }
    textbox { $('#tinymce') }
    iframe(wait:true) { $('#content_ifr') }
    tinymce(wait:true)  { $('[class="mce-content-body"]') }
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

  void inputText() {
    // TODO Handle iFrame Content here
    println(iframe)
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

  void setPublish() {
    publishedYes.click()
  }

  void setUnPublish() {
    publishedNo.click()
  }

  void clickSave() {
    saveButton.click()
  }

  void clickCancel() {
    cancelButton.click()
  }
}