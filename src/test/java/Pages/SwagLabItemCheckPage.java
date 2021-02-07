package Pages;

import Tests.TestListener;
import Utility.Utilities;
import drivermanager.Constant;
import drivermanager.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class SwagLabItemCheckPage extends DriverFactory {

    @FindBy(id = "user-name")
    protected WebElement txtUserName;

    @FindBy(id = "password")
    protected WebElement txtPassWord;

    @FindBy(id = "login-button")
    protected WebElement btnLogin;

    @FindBy(xpath = "//div[@class='product_label']")
    protected WebElement labelProduct;

    @FindBy(xpath = "//select[contains(@class,'product_sort')]")
    protected WebElement selectPrice;

    @FindBy(xpath = "//button[text()='ADD TO CART']")
    protected List<WebElement> btnAddToCart;

    @FindBy(xpath = "//span[contains(@class,'shopping_cart_badge')]")
    protected WebElement counter;

    @FindBy(xpath = "//div[contains(@class,'inventory_item_name')]//following::button[text()='REMOVE']")
    protected List<WebElement> btnRemove;

    @FindBy(xpath = "//div[contains(@class,'inventory_item_name')]")
    protected List<WebElement> getProductName;

    @FindBy(xpath = "//button[text()='REMOVE']/ancestor::div[contains(@class,'inventory_item')]//div[@class='inventory_item_label']/a/div")
    protected List<WebElement> getCartAddedProductName;

    @FindBy(xpath = "//a[contains(@href,'./cart.html')]")
    protected WebElement iconCart;

    @FindBy(xpath = "//a[contains(@class,'checkout_button')]")
    protected WebElement btnCheckOut;

    @FindBy(id = "first-name")
    protected WebElement txtFirstName;

    @FindBy(id = "last-name")
    protected WebElement txtLastName;

    @FindBy(id = "postal-code")
    protected WebElement txtZipCode;

    @FindBy(xpath = "//input[@value='CONTINUE']")
    protected WebElement btnContinue;

    @FindBy(xpath = "(//div[contains(@class,'summary_value_label')])[1]")
    protected WebElement paymentInformation;

    @FindBy(xpath = "//div[contains(text(),'Shipping')]//following-sibling::div[contains(@class,'summary_value_label')]")
    protected WebElement shippingInformation;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    protected WebElement getProductSubTotal;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    protected WebElement getTax;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    protected WebElement getTotalAmount;

    @FindBy(xpath = "//a[@href='./checkout-complete.html']")
    protected WebElement btnFinish;

    @FindBy(xpath = "//h2[@class='complete-header']")
    protected WebElement successMessage;

    @FindBy(xpath = "//div[contains(@class,'burger-button')]")
    protected WebElement btnMenu;

    @FindBy(xpath = "//a[text()='Logout']")
    protected WebElement btnLogout;

    private String userName, password, subtotal, tax,totalAmount,firstName,lastname,zipcode;
    private int noOfProduct;
    private float subtotalValue,taxValue,totalProductPrice=0;
    private ArrayList<String> beforeProductNameList = new ArrayList<>();
    private ArrayList<String> afterProductNameList = new ArrayList<>();

    /**
     * Enter User name
     *
     * @return
     */
    public SwagLabItemCheckPage enterUserName() {
        Utilities.getUtilities().waitForVisibilityOfElement(txtUserName, driver);
        userName = Utilities.getUtilities().readDataFromExcel(Constant.swagLabExcelFile, "loginCredential", "Username", 2);
        Utilities.getUtilities().sendKey(txtUserName, userName);
        Log.info("Enter Username.");
        return this;
    }

    /**
     * Enter Password
     *
     * @return
     */
    public SwagLabItemCheckPage enterPassword() {
        Utilities.getUtilities().waitForVisibilityOfElement(txtPassWord, driver);
        password = Utilities.getUtilities().readDataFromExcel(Constant.swagLabExcelFile, "loginCredential", "Password", 2);
        Utilities.getUtilities().sendKey(txtPassWord, password);
        Log.info("Enter Password.");
        TestListener.saveScreenshotPNG(driver);
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * Click on login button
     *
     * @return
     */
    public SwagLabItemCheckPage clickOnLogin() {
        Utilities.getUtilities().waitForVisibilityOfElement(btnLogin, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnLogin, driver);
        Utilities.getUtilities().clickOnElement(btnLogin);
        Log.info("Click On Login Button.");
        return this;
    }

    /**
     * login to application
     *
     * @return
     */
    public SwagLabItemCheckPage loginToApplication() {
        driver.get(prop.getProperty("baseUrl"));
        enterUserName().enterPassword().clickOnLogin();
        Utilities.getUtilities().waitForVisibilityOfElement(labelProduct, driver);
        Assert.assertTrue(labelProduct.isDisplayed());
        Log.info("Product Label Displayed.");
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * select price filter
     *
     * @return
     */
    public SwagLabItemCheckPage selectPriceFilter() {
        Utilities.getUtilities().waitForVisibilityOfElement(selectPrice, driver);
        Utilities.getUtilities().selectFromDropDownByVisibleText(selectPrice, prop.getProperty("priceFilter"));
        return this;
    }

    /**
     * Add two Product
     *
     * @return
     */
    public SwagLabItemCheckPage addTwoProductByClickingAddToCartButton() {
        noOfProduct = Integer.parseInt(prop.getProperty("noOfProduct"));
        for (int i = 0; i < noOfProduct; i++) {
            Utilities.getUtilities().waitForVisibilityOfElement(btnAddToCart.get(i), driver);
            Utilities.getUtilities().waitForElementTobeClickable(btnAddToCart.get(i), driver);
            Utilities.getUtilities().clickOnElement(btnAddToCart.get(i));
            TestListener.saveScreenshotPNG(driver);
            Utilities.getUtilities().captureScreenshot();
        }

        return this;
    }

    /**
     * Verify Added Product
     *
     * @return
     */
    public SwagLabItemCheckPage verifyAddedProductWithCart() {
        Utilities.getUtilities().waitForVisibilityOfElement(counter, driver);
        System.out.println(counter.getText());
        Assert.assertEquals(counter.getText(), prop.getProperty("noOfProduct"));
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * Verify Added Product
     *
     * @return
     */
    public SwagLabItemCheckPage verifyAddedProduct() {
        Utilities.getUtilities().waitForVisibilityOfElement(btnRemove.get(0), driver);
        System.out.println(btnRemove.size());
        Assert.assertEquals(btnRemove.size(), noOfProduct);
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * get product name before clicking Icon Cart
     *
     * @return
     */
    public SwagLabItemCheckPage getProductNameBeforeClickingIconCart() {
        for (int i = 0; i < noOfProduct; i++) {
            Utilities.getUtilities().waitForVisibilityOfElement(getCartAddedProductName.get(i), driver);
            System.out.println(getCartAddedProductName.get(i).getText());
            beforeProductNameList.add(getCartAddedProductName.get(i).getText());
        }
        System.out.println(beforeProductNameList);
        TestListener.saveScreenshotPNG(driver);
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * click on Cart Icon
     *
     * @return
     */
    public SwagLabItemCheckPage clickOnCartIcon() {
        Utilities.getUtilities().waitForVisibilityOfElement(iconCart, driver);
        Utilities.getUtilities().waitForElementTobeClickable(iconCart, driver);
        Utilities.getUtilities().clickOnElement(iconCart);
        return this;
    }

    /**
     * get product name After clicking con Cart
     *
     * @return
     */
    public SwagLabItemCheckPage getProductNameAfterClickingIconCart() {
        for (int i = 0; i < noOfProduct; i++) {
            Utilities.getUtilities().waitForVisibilityOfElement(getProductName.get(i), driver);
            System.out.println(getProductName.get(i).getText());
            afterProductNameList.add(getProductName.get(i).getText());
        }
        System.out.println(afterProductNameList);
        return this;
    }

    /**
     * Verify Product Details
     *
     * @return
     */
    public SwagLabItemCheckPage verifyProductDetails() {
        for (int i = 0; i < noOfProduct; i++) {
            Assert.assertEquals(beforeProductNameList.get(i), afterProductNameList.get(i));
        }
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * Click on checkout button
     *
     * @return
     */
    public SwagLabItemCheckPage clickOnCheckOut() {
        Utilities.getUtilities().waitForVisibilityOfElement(btnCheckOut, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnCheckOut, driver);
        Utilities.getUtilities().clickOnElement(btnCheckOut);
        Utilities.getUtilities().captureScreenshot();
        return this;
    }


    /**
     * Enter First Name
     *
     * @return
     */
    public SwagLabItemCheckPage enterFirstName() {
        Utilities.getUtilities().waitForVisibilityOfElement(txtFirstName, driver);
        firstName = Utilities.getUtilities().readDataFromExcel(Constant.swagLabExcelFile, "CheckoutDetails", "Firstname", 2);
        Utilities.getUtilities().sendKey(txtFirstName,firstName);
        Log.info("Enter First Name.");
        return this;
    }

    /**
     * Enter Last Name
     *
     * @return
     */
    public SwagLabItemCheckPage enterLastName() {
        Utilities.getUtilities().waitForVisibilityOfElement(txtLastName, driver);
        lastname= Utilities.getUtilities().readDataFromExcel(Constant.swagLabExcelFile, "CheckoutDetails", "Lastname", 2);
        Utilities.getUtilities().sendKey(txtLastName,lastname);
        Log.info("Enter Last Name.");
        return this;
    }

    /**
     * Enter Zip code
     *
     * @return
     */
    public SwagLabItemCheckPage enterZipCode() {
        Utilities.getUtilities().waitForVisibilityOfElement(txtZipCode, driver);
        zipcode= Utilities.getUtilities().readDataFromExcel(Constant.swagLabExcelFile, "CheckoutDetails", "Zipcode", 2);
        zipcode=zipcode.substring(0,zipcode.indexOf("."));
        Utilities.getUtilities().sendKey(txtZipCode,zipcode);
        Log.info("Enter ZipCode.");
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * Click on Checkout Continue
     *
     * @return
     */
    public SwagLabItemCheckPage clickOnContinueButton() {
        Utilities.getUtilities().waitForVisibilityOfElement(btnContinue, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnContinue, driver);
        Utilities.getUtilities().clickOnElement(btnContinue);
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * verify Payment Information
     *
     * @return
     */
    public SwagLabItemCheckPage verifyPaymentInformation() {
        Utilities.getUtilities().waitForVisibilityOfElement(paymentInformation, driver);
        Assert.assertEquals(paymentInformation.getText(), prop.getProperty("paymentInformation"));
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * verify Shipping Information
     *
     * @return
     */
    public SwagLabItemCheckPage verifyShippingInformation() {
        Utilities.getUtilities().waitForVisibilityOfElement(shippingInformation, driver);
        Assert.assertEquals(shippingInformation.getText(), prop.getProperty("shippingInformation"));
        Utilities.getUtilities().captureScreenshot();
        return this;
    }

    /**
     * get product price
     *
     * @return
     */
    public SwagLabItemCheckPage getProductSubTotal() {
        Utilities.getUtilities().waitForVisibilityOfElement(getProductSubTotal, driver);
        System.out.println(getProductSubTotal.getText());
        subtotal = getProductSubTotal.getText();
        subtotalValue = Float.parseFloat(subtotal.substring(subtotal.indexOf("$") + 1));
        System.out.println(subtotalValue);
        totalProductPrice=totalProductPrice+subtotalValue;
        return this;
    }

    /**
     * get product price
     *
     * @return
     */
    public SwagLabItemCheckPage getProductTax() {
        Utilities.getUtilities().waitForVisibilityOfElement(getTax, driver);
        System.out.println(getTax.getText());
        tax = getTax.getText();
        taxValue =Float.parseFloat(tax.substring(tax.indexOf("$") + 1));
        System.out.println(taxValue);
        totalProductPrice=totalProductPrice+taxValue;
        return this;
    }
     /**
     * get total price
     *
     * @return
     */
    public SwagLabItemCheckPage getTotalAmount() {
        Utilities.getUtilities().waitForVisibilityOfElement(getTotalAmount, driver);
        System.out.println(getTotalAmount.getText());
        totalAmount = getTotalAmount.getText();
        totalAmount =totalAmount.substring((totalAmount.indexOf("$") + 1));
        System.out.println(totalAmount);
        TestListener.saveScreenshotPNG(driver);
        Utilities.getUtilities().captureScreenshot();
        return this;
    }


    /**
     * verify total Amount
     *
     * @return
     */
    public SwagLabItemCheckPage verifyTotalProductPrice() {
        Assert.assertTrue(totalAmount.contains(String.valueOf(totalProductPrice)));
        return this;
    }

    /**
     * Click on login button
     *
     * @return
     */
    public SwagLabItemCheckPage clickOnFinish() {
        Utilities.getUtilities().waitForVisibilityOfElement(btnFinish, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnFinish, driver);
        Utilities.getUtilities().clickOnElement(btnFinish);
        return this;
    }
    /**
     * Verify Success message
     *
     * @return
     */
    public SwagLabItemCheckPage verifySuccessMessage() {
        Utilities.getUtilities().waitForVisibilityOfElement(successMessage, driver);
       Assert.assertEquals(successMessage.getText(),prop.getProperty("successMessage"));
        Utilities.getUtilities().captureScreenshot();
        return this;
    }
    /**
     * Click on Menu
     *
     * @return
     */
    public SwagLabItemCheckPage clickOnMenu() {
        Utilities.getUtilities().waitForVisibilityOfElement(btnMenu, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnMenu, driver);
        Utilities.getUtilities().clickOnElement(btnMenu);
        Utilities.getUtilities().captureScreenshot();
        return this;
    }
    /**
     * Click on Logout
     *
     * @return
     */
    public SwagLabItemCheckPage clickOnLogout() {
        Utilities.getUtilities().waitForVisibilityOfElement(btnLogout, driver);
        Utilities.getUtilities().waitForElementTobeClickable(btnLogout, driver);
        Utilities.getUtilities().clickOnElement(btnLogout);
        return this;
    }

    /**
     * verify Successfully Logout
     * @return
     */
    public SwagLabItemCheckPage verifySuccessfullyLogout(){
        Assert.assertTrue(btnLogin.isDisplayed());
        Utilities.getUtilities().captureScreenshot();
        return this;
    }
}
