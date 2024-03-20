package goEventProject.Test;

import goEventProject.Base.BaseTest;
import goEventProject.Utilities.Constants;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessLoginTest extends BaseTest {

    @Test
    public void successfulUserLoginTest() {
        landingPage = startTest();

        loginPage = landingPage.clickOnLoginBtn();
        loginPage.setAddressOrUserInput(utilities.getPropertyByValue(prop,"testing_goevent_user"));
        loginPage.clickOnContinueBtn(false);

        WebElement emailSetted = loginPage.getEmailSetted();
        Assert.assertEquals(emailSetted.getText(),utilities.getPropertyByValue(prop,"testing_goevent_user"));

        loginPage.setPasswordInput(utilities.getPropertyByValue(prop,"testing_goevent_password"));

        mainPage = loginPage.clickOnContinueBtn(true);

        WebElement userAvatar = mainPage.getUserAvatar();
        Assert.assertTrue(userAvatar.isDisplayed());

        WebElement homeLink = mainPage.getHomeLink();
        Assert.assertEquals(homeLink.getText(), "Home");

        WebElement createEventLink = mainPage.getCreateEventLink();
        Assert.assertEquals(createEventLink.getText(), "Create Event");

        WebElement myProfileLink = mainPage.getMyProfileLink();
        Assert.assertEquals(myProfileLink.getText(), "My Profile");

    }
    @Test
    public void successfulUserOTPLoginTest() {
        landingPage = startTest();

        loginPage = landingPage.clickOnLoginBtn();
        loginPage.setAddressOrUserInput(utilities.getPropertyByValue(prop,"testing_clerk_otp_user"));
        loginPage.clickOnContinueBtn(false);

        WebElement emailSetted = loginPage.getEmailSetted();
        Assert.assertEquals(emailSetted.getText(), utilities.getPropertyByValue(prop,"testing_clerk_otp_user"));

        loginPage.clickOnUseAnotherMethodLink();
        loginPage.clickOnEmailCodeLink();

        mainPage = loginPage.completeOTPCode(Constants.CLERK_TEST_OTP_CODE);

        WebElement userAvatar = mainPage.getUserAvatar();
        Assert.assertTrue(userAvatar.isDisplayed());

        WebElement homeLink = mainPage.getHomeLink();
        Assert.assertEquals(homeLink.getText(), "Home");

        WebElement createEventLink = mainPage.getCreateEventLink();
        Assert.assertEquals(createEventLink.getText(), "Create Event");

        WebElement myProfileLink = mainPage.getMyProfileLink();
        Assert.assertEquals(myProfileLink.getText(), "My Profile");

    }
}
