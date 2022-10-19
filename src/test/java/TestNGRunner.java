import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagepattern.HomePage;
import pagepattern.LoginPage;
import utility.TestBase;
import utility.TestUtility;

public class TestNGRunner extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    static String bookName;

   private String config = "config.properties";
   private String userName= TestUtility.readFromConfigProperties(config,"username");
   private String userPassword=TestUtility.readFromConfigProperties(config,"password");
   final static String EXPECTED_ALERT_MESSAGE="Book added to your collection.";

    @BeforeClass
    public void setUp(){
        browserSetUp(TestUtility.readFromConfigProperties(config,"url"));
        loginPage=new LoginPage(driver);
        loginPage.openBookStoreLoginPage();

    }
    @Test(priority = 1,description = "add a book to the collection")
    public void addBookToCollection(){
        Assert.assertTrue(loginPage.verifyLoginPageOpened());
        loginPage.clickOnLoginButton();
        homePage= loginPage.loginMethod(userName,userPassword);
        Assert.assertTrue(homePage.verifyLogin());
        bookName = homePage.selectBook();
        homePage.addToCollection();
        Assert.assertEquals(homePage.getAlertMessage(),EXPECTED_ALERT_MESSAGE);
        homePage.acceptAlert();
        homePage.goToProfile();
        Assert.assertEquals(bookName,homePage.getTheBookInCollection());
    }

    @Test(priority = 2,description = "test is about add book and remove it from collection")
    public void removeTheBookFromCollection(){

        homePage = new HomePage(driver);
        homePage.goToProfile();
        Assert.assertTrue(homePage.isBookExistInCollection(bookName));
        homePage.removeSelectedBookFromCollection(bookName);
        Assert.assertFalse(homePage.isBookExistInCollection(bookName));

    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();

    }
}
