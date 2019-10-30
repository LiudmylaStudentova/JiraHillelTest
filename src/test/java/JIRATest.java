import pages.LoginPage;
import pages.NewIssuePage;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.NewIssuePage;
import utils.WebDriverFactory;

public class JIRATest extends BaseTest {

    @Feature("Login")
    @Test(groups = {"Smoke"})
    public void createIssueTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.Login("webinar5", "webinar5");
        Assert.assertEquals(WebDriverFactory.getDriver().getCurrentUrl(), "https://jira.hillel.it/secure/Dashboard.jspa");
       //  Assert.assertEquals(1,2);
    }
    @Feature("Incorrect Data Entry")
    @Test(groups = {"Regression", "SKIP"})
    public void testLoginWrongLassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.Login("webinar5", "web5");
        Assert.assertEquals(loginPage.errorMassage(), "Sorry, your username and password are incorrect - please try again.");
    }

    @Feature("Issue")
    @Test(groups = {"Regression", "SKIP"})
    public void createIssue() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.Login("webinar5", "webinar5");
        Assert.assertEquals(WebDriverFactory.getDriver().getCurrentUrl(), "https://jira.hillel.it/secure/Dashboard.jspa");

        NewIssuePage createIssuePage = new NewIssuePage(driver);
        createIssuePage.clickCreateNewIssueButton();
        createIssuePage.enterProjectName("QAAUTO-8 (QAAUT8)");
        createIssuePage.enterIssueType("Test");
        createIssuePage.enterIssueSummary("This is an automatic test for QA Automations.");
        createIssuePage.enterIssueDescription("This is an automatic test for QA Automations.");
        createIssuePage.clickCreateIssue();

        Assert.assertTrue(createIssuePage.issueCreatedPopupPresent());
    }
    @Feature("Issue")
    @Test(groups = {"Regression", "SKIP"})
    public void testToBeSkipped() throws InterruptedException {
    }
}
