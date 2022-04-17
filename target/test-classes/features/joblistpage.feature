#Author Aji
  @StoryId=JoblistPageFunctionality
  @FeatureFileName=joblistpage.feature
  @smoke3
  Feature: Feature2_Formbay JoblistPage
      Scenario Outline: Formbay JoblistPage
        Given i am Formbay User logging in with "<UserId>" and "<Password>"
        When i login successfully
        Then I should see joblist page
        And I should see create job button
        Examples:
          |UserId|Password|SeletedDataRow|
          |client_trade|solar@123|1      |

#    Scenario Outline: Formbay HomePage2
#      Given i am Formbay User for homepage
#      When i login to Formbay application for homepage
#      Then I should execute last test
#      Examples:
#        |UserId|Password|
#        |client_trade|solar@123|