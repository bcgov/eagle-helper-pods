package Admin.modules

import geb.Module
import geb.waiting.WaitTimeoutException


class CPTableRows extends Module {
  static content = {
      status { $('[data-label=Status]') }
      startDate { $('[data-label=Start Date') }
      endDate { $('[data-label=End Date') }
      daysRemaining { $('[data-label=Days Remaining') }
      published { $('[data-label=Phase') }
      commentData { $('[data-label=Decision') }
  }

  void clickCell() {
      status.click()
  }
}