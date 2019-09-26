package Pages.Admin

import geb.waiting.WaitTimeoutException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddEditCommentPeriodPage extends BaseAppPage {
  static at = { isAngularReady() }
  static content = {
    // dateStart { $('#inputStartDate') }
    dateStart { $('#inputStartDate') }
    dateEnd { $('#inputEndDate') }
    publishStateDropdown { $('#publishedState') }
    information { $('#infoForComment') }
    description { $('#description') }
    cpStatus { $('#status') }
    milestoneDropdown { $('select[formcontrolname=milestoneSel') }

    openHouseDate { $('#inputOpenHouseDate') }
    openHouseDescription { $('#openHouseDescription') }

    cancelButton { $('button[type=cancel]') }
    submitButton { $('button[type=submit]') }
  }

  LocalDate date = LocalDate.now()
  
  void setStartDateFuture() {
    dateStart.value(date + 7)
  }

  void setEndDateFuture() {
    dateEnd.value(date + 14)
  }

  void setStartDateNow() {
    // currdate
    dateStart.value(date)
  }

  void setEndDateNow() {
    dateEnd.value(date + 7)
  }

  void enterInformation(String info) {
    information.value(info)
  }

  void enterDescription(String desc) {
    description.value(desc)
  }
  void selectPublishState(String action) {
    waitFor {
      publishStateDropdown.$('option', text:action).click()
    }
  }

  void selectMilestone(String action) {
    waitFor {
      milestoneDropdown.$('option', text:action).click()
    }
  }

  void setDateOpenHouse() {
    // to start date?
  }

  void setOHDescription(String desc) {
    openHouseDescription.value(desc)
  }

  void clickSave() {
    submitButton.click()
  }

  void clickCancel() {
    cancelButton.click()
  }
}