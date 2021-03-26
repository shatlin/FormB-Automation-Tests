#Author Aji
  @StoryId=HomePageFunctionality
  @FeatureFileName=S2_homepage.feature
  @smoke
  Feature: Feature2_Formbay HomePage
      Scenario Outline: Formbay HomePage
      Given i am Formbay User for homepage
      When i login to Formbay application for homepage
      Then I should see joblist page for homepage
        Examples:
          |UserId|Password|
          |client_trade|solar@123|

    Scenario Outline: Formbay HomePage2
      Given i am Formbay User for homepage
      When i login to Formbay application for homepage
      Then I should execute last test
      Examples:
        |UserId|Password|
        |client_trade|solar@123|