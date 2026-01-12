# TBC Cross-Collab Project
### Test Automation for Event Management
This repository contains automated tests for the Event Management System. These tests verify the key functionalities of the system, including user sign in, event creation, event browsing, and event publishing. The tests are written in **Java** using the **Selenium WebDriver** for browser automation and **TestNG** as the testing framework. The tests are integrated with **Zephyr** for test management and are successfully integrated in the **CI/CD pipeline**.

## Test Scenarios

### 1. **Registering for an Event**  
These tests verify the user registration journey for an event. The following actions are tested:
- **Sign In**: Verifies that the user can log in to the system.
- **Navigating to the Event Page**: Ensures that the user can browse and access the event list.
- **Searching for an Event**: Verifies that the user can search for a specific event.
- **Viewing Event Details**: Ensures that the user can view the detailed information of a selected event.
- **Checking Event Capacity and Availability**: Verifies that the available spots for the event are displayed correctly.
- **Registering for the Event**: Tests the registration button to ensure the user can register successfully.

### 2. **Creating an Event**  
These tests verify the admin functionalities related to event creation and publishing. The following actions are tested:
- **Sign In**: Ensures that the admin can log in to the system.
- **Navigating to the Event Management Page**: Verifies that the admin can access the event management page.
- **Creating a New Event**: Ensures that the admin can create a new event by filling in necessary details like title, description, date, time, and location.
- **Publishing the Event**: Verifies that after entering event details, the event can be published successfully.


## Testing Framework & Tools
- **Selenium WebDriver**: Used for browser automation.
- **TestNG**: A testing framework to structure and run the tests.
- **Zephyr**: Test management tool to track the test cases and results.
- **ChromeDriver**: Used to interact with the Chrome browser in headless mode.
  
## CI/CD Pipeline Integration
- The tests are integrated into the **CI/CD pipeline** to run automatically.

## Project Setup

### Prerequisites
1. **Java 8 or higher** should be installed.
2. **Maven** for dependency management.
3. The **`pom.xml`** file already includes all necessary dependencies for **Selenium WebDriver** and **TestNG**.
4. **ChromeDriver**: The tests are configured to run in headless mode by default.

### Running Tests Locally
1. Clone the repository.
- git clone https://github.com/Hikarunnie/TBC-Cross-Collab.git
- cd TBC-Cross-Collab
2. Open the project in your IDE (IntelliJ, Eclipse, etc.).
3. Install Dependencies
4. Run Tests Using testing.xml file
