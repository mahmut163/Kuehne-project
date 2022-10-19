package pagepattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.TestUtility;

public class HomePage {
    WebDriver driver;
    TestUtility testUtility;
    static String selectedBook1;


    @FindBy(id = "submit")
    WebElement logOutButton;
    @FindBy(xpath = "//a[contains(text(),'Git Pocket Guide')]")
    WebElement book1;
    @FindBy(xpath = "//a[contains(text(),'Learning JavaScript Design Patterns')]")
    WebElement book2;
    @FindBy(xpath = "//button[contains(text(),'Add To Your Collection')]")
    WebElement addToYourCollectionButton;
    @FindBy(xpath = "//span[contains(text(),'Profile')]")
    WebElement profileButton;
    @FindBy(xpath = "//*[contains(text(),'Git Pocket Guide')]")
    WebElement addedBookInCollection;
    @FindBy(id = "closeSmallModal-ok")
    WebElement deleteOkButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        testUtility = new TestUtility(driver);
    }

    public boolean verifyLogin() {
        testUtility.waitForElementPresent(logOutButton);
        Log.info("login successfully");
        return logOutButton.isDisplayed();
    }
    public String selectBook(){
        testUtility.waitForElementPresent(book1);
        selectedBook1=book1.getText().trim();
        testUtility.scrollToView(book1).click();
        //book1.click();
        Log.info("book selected");
        System.out.println(selectedBook1);

        return selectedBook1;

    }

    public void addToCollection() {
        testUtility.waitForElementPresent(addToYourCollectionButton);
        testUtility.scrollToView(addToYourCollectionButton).click();
        Log.info(" add collection button clicked and book added to collection ");
        testUtility.sleep(4);
    }

    public String getAlertMessage() {
        testUtility.waitForAlertPresent();
        return driver.switchTo().alert().getText();

    }

    public void acceptAlert () {

        driver.switchTo().alert().accept();

        testUtility.sleep(2);


        Log.info(" added alert accepted");
        }
        public String getTheBookInCollection(){
        testUtility.waitForElementPresent(addedBookInCollection);
        System.out.println(addedBookInCollection.getText());
        Log.info("get added book title");
        return addedBookInCollection.getText().trim();
    }
    public void goToProfile(){
        testUtility.waitForElementPresent(profileButton);
        testUtility.scrollToView(profileButton);
        profileButton.click();
        testUtility.sleep(3);
    }
    public void removeSelectedBookFromCollection(String bookName){

        String deleteButtonRow="//a[contains(text(),'?')]//ancestor::div[@role=\"row\"]//span[@title='Delete']";
        WebElement selectedDeleteButton=driver.findElement(By.xpath(deleteButtonRow.replace("?",bookName)));
        testUtility.scrollToView(selectedDeleteButton).click();
        testUtility.sleep(3);
        deleteOkButton.click();
        testUtility.sleep(2);
        testUtility.waitForAlertPresent();
        driver.switchTo().alert().accept();
        Log.info("delete alert accepted");

    }
    public boolean isBookExistInCollection(String bookName) {
        boolean result = false;
        WebElement collectionTable  = driver.findElement(By.cssSelector(".rt-tbody"));
        try {
            collectionTable.findElement(By.xpath(".//*[contains(text(), '?')]".replace("?", bookName)));
            result = true;
        } catch (Exception e) {
        }
        return result;

    }

    }

