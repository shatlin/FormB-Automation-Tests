#Author Aji
  @StoryId=LoginFunctionality
  @FeatureFileName=S1_loginpage.feature
@smokes
  Feature: Feature1_Login to Formbay
      Scenario Outline: Login to Formbay
      Given i am Formbay User logging in with "<UserId>" and "<Password>"
      When i login to Formbay application
      Then I should see joblist page
    Examples:
    |UserId|Password|
    |client_trade|solar@123|