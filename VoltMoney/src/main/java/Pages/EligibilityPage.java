package Pages;

import Utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EligibilityPage {

    private WebDriver driver;
    private WaitUtils waitUtils;


    @FindBy(xpath = "//button[contains(text(),'Check eligibility for mutual fund portfolio')]")
    private WebElement formTitle;
    @FindBy(xpath = "//button[contains(text(),'No impact on CIBIL score')]")
    private WebElement formSubTitle;
    @FindBy(xpath = "//input[contains(@placeholder,'mobile number')]")
    private WebElement mobileInput;
    @FindBy(xpath = "//div[contains(normalize-space(), 'MFCentral has sent an OTP')]")
    private WebElement successFullSubmission;
    @FindBy(xpath = "//div[contains(text(),'No investment found')]")
    private WebElement failureSubmission;
    @FindBy(xpath = "//input[contains(@placeholder,'PAN')]")
    private WebElement panInput;
    @FindBy(xpath = "//span[text()='Edit details']")
    private WebElement editDetailsSpan;
    @FindBy(xpath = "//button[span[text()='Check eligibility for FREE']]")
    private WebElement checkEligibilityBtn;
    @FindBy(xpath = "//div[text()='Enter a valid PAN']")
    private WebElement enterValidPanMessage;
    @FindBy(xpath = "//div[text()='Enter a valid mobile number']")
    private WebElement enterValidMobileMessage;

    public EligibilityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.waitUtils = new WaitUtils(driver);
    }

    public void enterMobile(String mobile) {
        waitUtils.waitForVisibility(mobileInput, 10);
        mobileInput.clear();
        mobileInput.sendKeys(mobile);
    }

    public void enterPAN(String pan) {
        waitUtils.waitForVisibility(panInput, 10);
        panInput.clear();
        panInput.sendKeys(pan);
    }

    public void clickCheckEligibility() {
        waitUtils.waitForVisibility(checkEligibilityBtn, 10);
        checkEligibilityBtn.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
    public String getSuccesfullSubmission(){
        waitUtils.waitForVisibility(successFullSubmission, 10);
        return successFullSubmission.getText();
    }
    public String getFormTitle(){
        waitUtils.waitForVisibility(formTitle, 10);
        return formTitle.getText();
    }
    public String getFormSubTitle(){
        waitUtils.waitForVisibility(formSubTitle, 10);
        return formSubTitle.getText();
    }
    public String getFailureSubmission(){
        waitUtils.waitForVisibility(failureSubmission, 10);
        return failureSubmission.getText();
    }

    public void editDetails(){
        editDetailsSpan.click();
    }

    public boolean isMobileFieldDisplayed() {
        waitUtils.waitForVisibility(mobileInput, 10);
        return mobileInput.isDisplayed();
    }

    public boolean isPANFieldDisplayed() {
        waitUtils.waitForVisibility(panInput, 10);
        return panInput.isDisplayed();
    }

    public boolean isCheckEligibilityButtonDisplayed() {
        waitUtils.waitForVisibility(checkEligibilityBtn, 10);
        return checkEligibilityBtn.isDisplayed();
    }

    public String failurePan() {
        waitUtils.waitForVisibility(enterValidPanMessage, 10);
        return enterValidPanMessage.getText();
    }
    public String failureNumber() {
        waitUtils.waitForVisibility(enterValidMobileMessage, 10);
        return enterValidMobileMessage.getText();
    }

    public Boolean isFailurePan() {
        waitUtils.waitForVisibility(enterValidPanMessage, 10);
        return enterValidPanMessage.isDisplayed();
    }
    public Boolean isFailureNumber() {
        waitUtils.waitForVisibility(enterValidMobileMessage, 10);
        return enterValidMobileMessage.isDisplayed();
    }
}
