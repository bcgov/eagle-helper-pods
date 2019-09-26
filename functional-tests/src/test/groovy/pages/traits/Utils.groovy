package pages.traits

/**
 * Generic re-usable utility methods.
 */
trait Utils {
  /**
   * Wait for the document to finish loading
   * @return true if document has finished loading, false otherwise.
   */
  //  todo not sure if this works for angular
  Boolean isAngularReady() {
    waitFor {
      js.exec('return document.readyState;') == 'complete'
    }
  }
}