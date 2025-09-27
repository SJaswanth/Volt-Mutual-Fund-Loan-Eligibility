package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Pages.EligibilityPage;

public class EligibilityTests extends BaseTest {

    @Test(priority = 1)
    public void verifyPageLoadsSuccessfully() {
        EligibilityPage page = new EligibilityPage(driver);
        String title = page.getPageTitle();
        Assert.assertTrue(title.contains("eligibility"),
                "Page title does not contain 'Eligibility'. Actual title: " + title);
        System.out.println("Page loaded successfully with title: " + title);
    }

    @Test(priority = 2, dataProvider = "eligibilityData")
    public void testFormSubmissionWithValidData(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        Assert.assertEquals(page.getSuccesfullSubmission(), "MFCentral has sent an OTP");
        System.out.println("Form submitted successfully");
    }

    @Test(priority = 3, dataProvider = "eligibilityData")
    public void testFormSubmissionWithInvalidData(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        page.getFailureSubmission();
        Assert.assertEquals(page.getFailureSubmission(),
                "No investment found");
        System.out.println("Invalid mobile test passed");
    }

    @Test(priority = 4, dataProvider = "eligibilityData")
    public void testFormSubmissionWithEmptyFields(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        String title = page.getPageTitle();
        Assert.assertTrue(title.contains("eligibility"),
                "Page title does not contain 'Eligibility'. Actual title: " + title);
        System.out.println("Empty fields validation passed");
    }
    @Test(priority = 5, dataProvider = "eligibilityData")
    public void testMobileNumberBoundaryValues(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        Assert.assertEquals(page.getSuccesfullSubmission(), "MFCentral has sent an OTP");

        page.enterMobile("999999999");
        page.clickCheckEligibility();
        Assert.assertEquals(page.failureNumber(), "No investment found");

        System.out.println("Mobile number boundary test passed");
    }
    @Test(priority = 6, dataProvider = "eligibilityData")
    public void testPANValidation(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        Assert.assertEquals(page.failurePan(), "Enter a valid PAN");
        System.out.println("PAN format validation passed");
    }
    @Test(priority = 7, dataProvider = "eligibilityData")
    public void testUIElementsPresence() {
        EligibilityPage page = new EligibilityPage(driver);
        Assert.assertTrue(page.isMobileFieldDisplayed(), "Mobile input field is not displayed");
        Assert.assertTrue(page.isPANFieldDisplayed(), "PAN input field is not displayed");
        Assert.assertTrue(page.isCheckEligibilityButtonDisplayed(), "Check Eligibility button is not displayed");
        System.out.println("All required UI elements are displayed");
    }

    @Test(priority = 8, dataProvider = "eligibilityData")
    public void testMobileNumberWithAlphabets(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        Assert.assertEquals(page.failureNumber(), "Enter a valid mobile number");
        System.out.println("Mobile number with alphabets validation passed");
    }
    @Test(priority = 9, dataProvider = "eligibilityData")
    public void testPANWithSpecialCharacters(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        Assert.assertEquals(page.failurePan(), "Enter a valid PAN");
        System.out.println("PAN with special characters validation passed");
    }
    @Test(priority = 10, dataProvider = "eligibilityData")
    public void testInputWithLeadingTrailingSpaces(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        Assert.assertEquals(page.getSuccesfullSubmission(), "MFCentral has sent an OTP");
        System.out.println("Leading/trailing spaces handled correctly");
    }
    @Test(priority = 11)
    public void testMobileNumberMaxLength(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        Assert.assertEquals(page.failureNumber(), "Enter a valid mobile number");
        System.out.println("Mobile number max length validation passed");
    }
    @Test(priority = 13)
    public void testMultipleInvalidSubmissions(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        String[] invalidMobiles = {"123", "abcdefghij", "999999999"};
        for (String mobile : invalidMobiles) {
            page.enterMobile(mobile);
            page.enterPAN(PAN);
            page.clickCheckEligibility();
            Assert.assertEquals(page.failureNumber(), "Enter a valid mobile number");
        }
        System.out.println("Multiple consecutive invalid submissions validated");
    }
    @Test(priority = 15)
    public void testCheckEligibilityButtonDisabledForEmptyFields(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);
        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);

        Assert.assertFalse(page.isCheckEligibilityButtonDisplayed(),
                "Check Eligibility button should be disabled for empty fields");

        System.out.println("Check Eligibility button disabled validation passed");
    }
    @Test(priority = 16)
    public void testErrorMessagesDisappearAfterCorrection(String phoneNumber, String PAN) {
        EligibilityPage page = new EligibilityPage(driver);

        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();
        Assert.assertEquals(page.failureNumber(), "Enter a valid mobile number");
        Assert.assertEquals(page.failurePan(), "Enter a valid PAN");

        page.enterMobile(phoneNumber);
        page.enterPAN(PAN);
        page.clickCheckEligibility();

        Assert.assertTrue(page.getSuccesfullSubmission().contains("MFCentral has sent an OTP"),
                "Success message not displayed after correcting inputs");

        System.out.println("Error messages disappear after correction validation passed");
    }


    @DataProvider(name = "eligibilityData")
    public Object[][] eligibilityInputData() {
        return new Object[][] {
                {"9876543210", "ABCDE1234F"},

                {"1234567891", "ABCDE1234F"},
                {"98765ABCD1", "ABCDE1234F"},

                {"", ""},

                {"9999999999", "ABCDE1234F"},
                {"999999999", "ABCDE1234F"},

                {"9876543210", "1234ABCDE"},
                {"9876543210", "ABCD@1234F"},

                {" 9876543210 ", " ABCDE1234F "},

                {"9876543210123", "ABCDE1234F"}
        };
    }

}
