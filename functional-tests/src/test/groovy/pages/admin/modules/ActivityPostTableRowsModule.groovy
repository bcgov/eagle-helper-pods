package Admin.modules

import geb.Module
import geb.waiting.WaitTimeoutException


class ActivityPostTableRows extends Module {
  static content = {
      pin { $('[data-label=Select]') }
      headline { $('[data-label=Headline]') }
      project { $('[data-label=Project]')}
      type { $('[data-label=Type]') }
      date { $('[data-label="Date Added"]') }
      status { $('[data-label=Status]') } //All activities table only
      delete { $$('[class="delete col-1"]') }
  }

  void clickCell() {
      headline.click()
  }

  void clickDelete() {
    delete.click()
  }
}