//package hellocucumber.Pages.Login;
//
//import hellocucumber.Pages.BasePage;
//import io.cucumber.java.Scenario;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.How;
//import utils.HelperMethods;
//import utils.constants.DataConstant;
//import utils.enums.UserRole;
//
//public class ForgotPasswordPage extends BasePage {
//    @FindBy(how = How.XPATH, using = "//*[text()='Next']")
//    private WebElement RESET_MY_PASSWORD_BUTTON_TEXT;
//    @FindBy(how = How.XPATH, using = "//*[@type='submit']")
//    private WebElement RESET_MY_PASSWORD_BUTTON;
//    @FindBy(how = How.XPATH, using = "//*[@formcontrolname='emailAddress']")
//    private WebElement EMAIL_FIELD;
//    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'check-email')]")
//    private WebElement SUCCESS_MESSAGE_RESET;
//
//    HelperMethods helperMethods;
//
//    public ForgotPasswordPage(WebDriver driver, Scenario scenario) {
//        super(driver, scenario);
//        helperMethods = new HelperMethods(driver);
//    }
//
//    public boolean isForgotPasswordPageDisplayed() {
//        return helperMethods.isElementExistWaitLongTime(RESET_MY_PASSWORD_BUTTON_TEXT);
//    }
//
//    public void completeInvalidEmail() {
//        helperMethods.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_WRONG);
//    }
//
//    public boolean isResetMyPasswordButtonEnabled() {
//        return helperMethods.isElementEnabled(RESET_MY_PASSWORD_BUTTON);
//    }
//
//    public void completeEmail(UserRole role) {
//        switch (role) {
//            case superAdmin -> helperMethods.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_SUPER_ADMIN);
//            case firmAdvisor -> helperMethods.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_FIRM_ADVISOR);
//            case firmAdmin -> helperMethods.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_FIRM_ADMIN);
//            case advisor -> helperMethods.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_ADVISOR);
//            case household -> helperMethods.sendKeysInElement(EMAIL_FIELD, DataConstant.EMAIL_HOUSEHOLD);
//            default -> {
//            }
//        }
//    }
//
//    public void clickResetMyPasswordButton() {
//        helperMethods.clickWithActions(RESET_MY_PASSWORD_BUTTON);
//    }
//
//    public boolean isSuccessMessageShown() {
//        return helperMethods.isElementExistWaitLongTime(SUCCESS_MESSAGE_RESET);
//    }
//}
