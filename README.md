Volt Mutual Fund Loan Eligibility - Automation Suite  Framework Overview

This project automates the Volt Mutual Fund Loan Eligibility Page:
🔗 Eligibility Page

Language: Java

Frameworks: Selenium WebDriver + TestNG

Design Pattern: Page Object Model (POM) with PageFactory

Build Tool: Maven

Driver Management: WebDriverManager (auto-handles browser drivers)

Test Types Covered:

Functional flows (valid/invalid submissions, OTP handling)

UI/UX verification

Error/validation handling

Edge/boundary cases

Setup Instructions
Prerequisites

Java JDK 17+

Maven 3.8+

IDE (IntelliJ IDEA / Eclipse recommended)

Git installed

Steps

Clone repository

git clone https://github.com/<your-username>/volt-eligibility-tests.git
cd volt-eligibility-tests


Install dependencies

mvn clean install

Run tests

Run all tests: mvn clean test

Project Structure
volt-eligibility-tests/
├── pom.xml                # Maven dependencies
├── testng.xml             # TestNG suite configuration
├── README.md              # Setup & instructions
├── src
│   ├── main
│   │   └── java
│   │       └── pages/     # Page Object classes
│   └── test
│       └── java
│           ├── base/      # BaseTest class
│           ├── tests/     # TestNG test classes
│           └── utils/     # Helpers like ConfigReader, WaitUtils

Deliverables

Manual Test Cases:- https://docs.google.com/spreadsheets/d/1gVzbWuEkXkHEkZCfslkfE0VKgpwXWCVEnuyS5fLkMPA/edit?gid=1330963922#gid=1330963922

Automation Code: 

⏳ Pending Items / To-Do


Improve assertions for OTP and error messages

Add cross-browser support (Firefox, Edge)

Integrate with Allure Reports for reporting

Add CI/CD pipeline (GitHub Actions) for continuous testing# Volt-Mutual-Fund-Loan-Eligibility