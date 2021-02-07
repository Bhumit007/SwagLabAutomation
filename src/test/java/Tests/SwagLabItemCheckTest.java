package Tests;

import drivermanager.DriverFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Reporter.log;

@Listeners({Tests.TestListener.class})
@Epic("Regression Test Suite - Login Details")
public class SwagLabItemCheckTest extends DriverFactory {
    @Step("Success Message: ")
    public void logToReport(String message) {
        log(message);
    }

    @Test(priority = 0)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_001 - verify Login Application")
    public void swagLabItemCheck_001_verifyLoginApplication() {
        try {
            swagLabPage.loginToApplication();
            Log.info("Login Successfully.");
            logToReport("verify user login successfully to application.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not login to application.");
            Log.error("User could not login to application." + E);
            Assert.fail("User Could not login to application." + E);
        }
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_002 - verify product added")
    public void swagLabItemCheck_002_verifyProductAdded() {
        try {
            swagLabPage.selectPriceFilter();
            Log.info("Select Price Filter from drop down.");
            swagLabPage.addTwoProductByClickingAddToCartButton();
            Log.info("Two Product Add into Cart.");
            swagLabPage.verifyAddedProductWithCart().verifyAddedProduct();
            Log.info("verify product added.");
            logToReport("verify product added.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not verify product added.");
            Log.error("User could not verify product added." + E);
            Assert.fail("User Could not verify product added." + E);
        }
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_003 - verify product details")
    public void swagLabItemCheck_003_verifyProductDetails() {
        try {
            swagLabPage.getProductNameBeforeClickingIconCart();
            Log.info("Get Product Details before Click on Cart Icon.");
            swagLabPage.clickOnCartIcon();
            Log.info("Click on Cart Icon.");
            swagLabPage.getProductNameAfterClickingIconCart();
            Log.info("Get Product Details After Click on Cart Icon.");
            swagLabPage.verifyProductDetails();
            Log.info("Verify Product Details.");
            logToReport("Verify Product Details.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not verify product details.");
            Log.error("User could not verify product details." + E);
            Assert.fail("User Could not verify product details." + E);
        }
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_004 - click checkout button")
    public void swagLabItemCheck_004_clickOnCheckOutButton() {
        try {
            swagLabPage.clickOnCheckOut();
            Log.info("Click on CheckOut Button.");
            logToReport("click on CheckOut Button.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not click checkout button.");
            Log.error("User could not click checkout button." + E);
            Assert.fail("User Could not click checkout button." + E);
        }
    }

    @Test(priority = 4)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_005 - enter checkout details And Click On Continue")
    public void swagLabItemCheck_005_enterCheckoutDetailsAndClickOnContinue() {
        try {
            swagLabPage.enterFirstName().enterLastName().enterZipCode();
            Log.info("Enter Checkout details.");
            TestListener.saveScreenshotPNG(driver);
            swagLabPage.clickOnContinueButton();
            Log.info("Click On Checkout Continue Button.");
            logToReport("Click On Checkout Continue Button.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not enter checkout details And Click On Continue.");
            Log.error("User could not enter checkout details And Click On Continue." + E);
            Assert.fail("User could not enter checkout details And Click On Continue." + E);
        }
    }

    @Test(priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_006 - Verify Payment Information")
    public void swagLabItemCheck_006_verifyPaymentInformation() {
        try {
            swagLabPage.verifyPaymentInformation();
            Log.info("Verify Payment Information.");
            logToReport("Verify Payment Information.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not Verify Payment Information.");
            Log.error("User could not Verify Payment Information." + E);
            Assert.fail("User Could not Verify Payment Information." + E);
        }
    }

    @Test(priority = 6)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_007_ Verify Shipping Information")
    public void swagLabItemCheck_007_verifyShippingInformation() {
        try {
            swagLabPage.verifyShippingInformation();
            Log.info("Verify Shipping Information.");
            logToReport("Verify Shipping Information.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not Verify Shipping Information.");
            Log.error("User could not Verify Shipping Information." + E);
            Assert.fail("User Could not Verify Shipping Information." + E);
        }
    }

    @Test(priority = 7)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_008_ Verify Total Product Price")
    public void swagLabItemCheck_008_verifyTotalAmount() {
        try {
            swagLabPage.getProductSubTotal();
            Log.info("Get Product sub total.");
            swagLabPage.getProductTax();
            Log.info("Get Product Tax.");
            swagLabPage.getTotalAmount();
            Log.info("Get Product Total Amount.");
            swagLabPage.verifyTotalProductPrice();
            Log.info("verify Total Product Price.");
            logToReport("verify Total Product Price.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not Verify Total Product Price.");
            Log.error("User could not Verify Total Product Price." + E);
            Assert.fail("User Could not Verify Total Product Price." + E);
        }
    }

    @Test(priority = 8)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_009_ Verify Success Message For Order Placed")
    public void swagLabItemCheck_009_verifyOrderPlaced() {
        try {
            swagLabPage.clickOnFinish();
            Log.info("Click on Finish Button.");
            swagLabPage.verifySuccessMessage();
            Log.info("Verify Success Message For Order Placed.");
            logToReport("Verify Success Message For Order Placed.");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not Verify Success Message For Order Placed.");
            Log.error("User could not Verify Success Message For Order Placed." + E);
            Assert.fail("User Could notVerify Success Message For Order Placed." + E);
        }
    }

    @Test(priority = 9)
    @Severity(SeverityLevel.NORMAL)
    @Step("TestCase_009_ Verify Successfully Logout")
    public void swagLabItemCheck_010_verifyLogout() {
        try {
            swagLabPage.clickOnMenu();
            Log.info("Click on Menu Button.");
            TestListener.saveScreenshotPNG(driver);
            swagLabPage.clickOnLogout();
            Log.info("Click on Logout Button.");
            swagLabPage.verifySuccessfullyLogout();
            Log.info("Verify Successfully Logout");
        } catch (AssertionError | Exception E) {
            System.out.println("User could not Verify Successfully Logout.");
            Log.error("User could not Verify Successfully Logout." + E);
            Assert.fail("User Could not Verify Successfully Logout." + E);
        }
    }

}
