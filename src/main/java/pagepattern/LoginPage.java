package pagepattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.TestUtility;

public class LoginPage {
    WebDriver driver;
    TestUtility testUtility;
    @FindBy(css = ".card.mt-4.top-card:nth-child(6)")
    WebElement bookStoreApplication;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "userName")
    WebElement userNameField;
    @FindBy(id = "password")
    WebElement passwordField;

    public LoginPage(WebDriver driver) {
        this.driver = driver; PageFactory.initElements(driver,this);
        testUtility=new TestUtility(driver);
    }
    public void openBookStoreLoginPage(){
        testUtility.sleep(5);
        testUtility.waitForElementPresent(bookStoreApplication);
        bookStoreApplication.click();
        Log.info("open book store login page");
        testUtility.sleep(2);
    }
    public boolean verifyLoginPageOpened() {
        testUtility.waitForElementPresent(loginButton);
        if (loginButton.isDisplayed()) {
            Log.info("book store login page opened");
            return true;
        }
        else {
            Log.error("login page can not open");
            return false;
        }
    }
    public void clickOnLoginButton() {
        testUtility.waitForElementPresent(loginButton);
        testUtility.scrollToView(loginButton).click();
        Log.info("login button clicked");
        testUtility.sleep(2);
    }
    public void enterUserName(String userName){
        testUtility.waitForElementPresent(userNameField);
        userNameField.clear();
        userNameField.sendKeys(userName);

    }
    public void enterPassword(String password){
        testUtility.waitForElementPresent(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);

    }

   public HomePage loginMethod(String userName,String password){
        enterUserName(userName);
        enterPassword(password);
        clickOnLoginButton();
        testUtility.sleep(2);
        Log.info("user name and password entered and login button clicked");
        return new HomePage(driver);
   }
}
