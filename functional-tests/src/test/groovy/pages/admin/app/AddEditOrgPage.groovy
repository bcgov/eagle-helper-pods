package Pages.Admin
import org.openqa.selenium.Keys
class AddEditOrgPage extends BaseAppPage {
  static at = {isAngularReady()}
  static content = {
    pageTitle { $('h2') }
    cancelButton { $('button[type=cancel]') }
    saveButton { $('button[type=submit]') }

    orgName { $('#name') }
    orgType { $('select[formcontrolname=companyType]')}
    legalName { $('#companyLegal') }
    parentLink { $('#link-parent') }

    phoneNumber { $('#phoneNumber') }
    faxNumber { $('#faxNumber') }
    cellNumber { $('#cellPhoneNUmber') }
    emailField { $('#email') }
    address { $('#address1') }
    address2 { $('#address2') }
    city { $('#city') }
    province { $('#province') }
    postalCode { $('#postal') }
    country { $('#country') }

    // todo deal with notes iframe
    // notes 
  }

  void setOrgName(String name) {
    orgName.value(name)
  }

  void selectOrgType(int idx) {
    orgType.click()
    1.upto(idx){
      orgType << Keys.ARROW_DOWN
    }
    orgType << Keys.ENTER
  }

  void clickParentLink(){
    parentLink.click()
  }
/////////////////////////////////////

  void setLastName(String name) {
    lastName.value(name)
  }

 

  void setTitle(String name) {
    title.value(name)
  }

  void setDepartment(String dept) {
    department.value(dept)
  }

  void clickLinkOrg() {
    orgButton.click()
  }

  void setPhoneNumber(String num) {
    phoneNumber.value(num)
  }

  void setFaxNumber(String num) {
    faxNumber.value(num)
  }

  void setCellNumber(String num) {
    cellPhoneNUmber.value(num)
  }

  void setEmail(String email) {
    emailField.value(email)
  }

  void setAddress1(String address) {
    address1.value(address)
  }

  void setAddress2(String address) {
    address2.value(address)
  }

  void setCity(String name) {
    city.value(name)
  }

  void selectProvince(String selection) {
    provinceDropDown.$('option', text:selection).click()
  }

  void setPostalCode(String code) {
    postalCode.value(code)
  }

  void setCountry(String name) {
    country.value(name)
  }

  void clickSave() {
    saveButton.click()
  }

  void clickCancel() {
    cancelButton.click()
  }
}