# Test Automation Framework

This is a Java-based test automation framework.

The framework leverages various libraries and tools to facilitate as Hybrid Framework, data-driven testing, logging, reporting, and selenium grid integration. 

**WEBSITE** : [Luma-WebApplication](https://magento.softwaretestingboard.com/)

## Author

- [@hari0101](https://github.com/hari0101)

- EmailAddress: hariprasath.qa@gmail.com

## Prerequisites

Before running this framework, ensure the following software is installed on your system:

- **Java 8 and above** - Make sure Java is installed and the JAVA_HOME environment variable is set.

- **Maven** - Ensure Maven is installed and the MAVEN_HOME added to the system path.

- Downlod Link: https://maven.apache.org/download.cgi

## Features

- **Data-Driven Testing**: Using Apache POI, and JackSon for reading test data from Excel files and JSON.

- **Cross-Browser Testing**: Supports running tests on different browsers.

- **Headless Mode**: Faster execution by running tests in headless mode.

- **Selenium Grid**: Integrated selenium grid with docker to run linux-based different browsers.

- **Logging**: Uses Log4j for detailed logs.

- **Reporting**: Generates detailed reports using Extent Reports.

## Dependencies	
The project relies on the following major dependencies:  

- **Selenium**: Different Browsers Automation.
- **Maven**: To Execute the Test Scripts at CI/CD pipelines.
- **TestNG**: Test Management, Test Execution and Parallel Execution.  
- **ExtentReports**: Generating HTML test reports.
- **Faker**: For generating random test data.  
- **Log4j**: For logging framework and debugging.
 
## Setup Instructions

**Clone the Repository:**

```bash

  git clone https://github.com/hari0101/Luma-WebApp-QA-Testing.git

  cd Luma-WebApp-QA-Testing

```
**Running Tests on Chrome, Edge and Firefox on Local Machine:**

```bash
     mvn test
```
## Reports

Reports: After execution, a detailed HTML report will be generated at ./target/Extent-Reports/.

The report contains information on test cases executed, passed, failed, and skipped, along with screenshots for failed tests.

## Logs: 

Logs are created during the test execution and stored in the ./logs/

## **Contact**

For any questions or issues, feel free to reach out:  
📧 **hariprasath.qa@gmail.com**

---

**Happy Testing! 🎉**
