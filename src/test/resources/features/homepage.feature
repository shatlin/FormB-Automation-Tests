#Author Aji
  @StoryId=HomePageFunctionality
  @FeatureFileName=S2_homepage.feature
  @smoke3
  Feature: Feature2_Formbay HomePage
      Scenario Outline: Formbay HomePage
      Given i am Formbay User for homepage testing with <SeletedDataRow>
      When i login to Formbay application for homepage
      Then I should see joblist page for homepage
        And I should execute last test
        Examples:
          |SeletedDataRow|
          |1|

#    Scenario Outline: Formbay HomePage2
#      Given i am Formbay User for homepage
#      When i login to Formbay application for homepage
#      Then I should execute last test
#      Examples:
#        |UserId|Password|
#        |client_trade|solar@123|