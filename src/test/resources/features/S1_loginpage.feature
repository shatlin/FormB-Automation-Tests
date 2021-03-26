#Author Aji
  @StoryId=LoginFunctionality
  @FeatureFileName=S1_loginpage.feature
@smoke
  Feature: Feature1_Login to Formbay
      Scenario Outline: Login to Formbay
      Given i am Formbay User
      When i login to Formbay application
      Then I should see joblist page
    Examples:
    |UserId|Password|
    |client_trade|solar@123|