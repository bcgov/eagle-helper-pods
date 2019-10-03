package specs

import Pages.Admin.AdminHomePage
import Pages.Admin.AdminProjectListPage
import Pages.Admin.ProjectDetailsPage

import Pages.Public.HomePage

import Pages.Admin.ActivityPostPage
import Pages.Admin.AddEditActivityPostPage

import spock.lang.Title
import spock.lang.Stepwise
import spock.lang.Narrative
import spock.lang.Shared

@Stepwise
@Title('Test comment period creation and comment submission')
@Narrative('''I want to see this run in browserstack''')
class ActivityPostSpec extends LoggedInSpec {

  @Shared
  String currentProject

  // This test suite is blocked by handling iFrame content, which is required to create an activity
  void 'Create activity post and publish, then verify on public'() {
    given: 'I am logged in as an Admin user'
      login()
      // todo break this up?
    and: 'I navigate to Activity Posts Page and create a new Activity Post'
      to ActivityPostPage
      navBarModule.clickNewAP()
      at AddEditActivityPostPage
      setHeadline("Test")
      selectType("News")
      setPublish()

    // Handle iframe content here
      inputText()

      clickSave()
      at ActivityPostPage
      newActivity = activityPostList[0]
    when: 'I go to the public home page and see the recent activities and updates headlines'
      to HomePage
      newestHeadline = publicActivitiesHeadlines[0]
    then: 'The posts that come up have a keyword match'
      newestHeadline == newActivity
  }

  // Same as before but check that it is not on public
  void 'Create activity post and do not publish, then verify not visible on public'() {
    given: 'I am logged in as an Admin user'
          login()
          // todo break this up?
        and: 'I navigate to Activity Posts Page and create a new Activity Post'
          to ActivityPostPage
          navBarModule.clickNewAP()
          at AddEditActivityPostPage
          setHeadline("Test")
          selectType("News")
          setPublish()

        // Handle iframe content here
          inputText()

          clickSave()
          at ActivityPostPage
          newActivity = activityPostList[0]
        when: 'I go to the public home page and see the recent activities and updates headlines'
          to HomePage
          newestHeadline = publicActivitiesHeadlines[0]
        then: 'The posts that come up have a keyword match'
          newestHeadline != newActivity
  }

  // go to admin page
  // activity post page
  // search in keywords for a post
  // do the posts that come up match the keyword somewhere
  void 'Verify the keyword search'() {
    
  }

  // does news show only News items
  // unselect
  // does PCP show only PCP items
  // unselect
   void 'Verify the filter buttons'() {


  }

  // need to unpin all posts
  // create activity A
  // create another activity B
  // create another activity C
  // check does A show first on the main page
   void 'Create several posts, verify the first lands on main page'() {

  }

  // edit one of the posts
  // does it stay on the main page
   void 'Edit the first post, and verify it stays on the main page'() {

  }
}