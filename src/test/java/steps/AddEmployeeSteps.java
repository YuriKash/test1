package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {

        click(dashboardPage.pimOption);
    }

    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {

        click(dashboardPage.addEmployeeButton);
    }

    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {

        sendText("aendro", addEmployeePage.firstNameField);
        sendText("farewell", addEmployeePage.lastNameField);
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {

        click(addEmployeePage.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }

    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstName, String middleName, String lastName) {

        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);

    }

    @When("user enters {string} and {string} and {string} in data driven format")
    public void user_enters_and_and_in_data_driven_format(String firstName, String middleName, String lastName) {
        sendText(firstName, addEmployeePage.firstNameField);
        sendText(middleName, addEmployeePage.middleNameField);
        sendText(lastName, addEmployeePage.lastNameField);
    }

    @When("user enters firstname and middlename and lastname and verify employee has added")
    public void user_enters_firstname_and_middlename_and_lastname_and_verify_employee_has_added
            (io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for (Map<String, String> employee : employeeNames) {
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");

            sendText(firstNameValue, addEmployeePage.firstNameField);
            sendText(middleNameValue, addEmployeePage.middleNameField);
            sendText(lastNameValue, addEmployeePage.lastNameField);

            click(addEmployeePage.saveButton);
            click(dashboardPage.addEmployeeButton);

        }
    }

    @When("user adds multiple employees using excel from {string} and verify it")
    public void user_adds_multiple_employees_using_excel_from_and_verify_it
            (String sheetName) throws InterruptedException {
        List<Map<String, String>> newEmployees = ExcelReader.read(sheetName, Constants.EXCEL_READER_PATH);
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        while (itr.hasNext()) {
            Map<String, String> mapNewEmp = itr.next();
            sendText(mapNewEmp.get("firstName"), addEmployeePage.firstNameField);
            sendText(mapNewEmp.get("lastName"), addEmployeePage.lastNameField);
            sendText(mapNewEmp.get("middleName"), addEmployeePage.middleNameField);
            sendText(mapNewEmp.get("photograph"), addEmployeePage.photograph);

            if (!addEmployeePage.checkBoxLocator.isSelected()) {
                click(addEmployeePage.checkBoxLocator);
            }
            sendText(mapNewEmp.get("username"), addEmployeePage.usernameTextFieldBox);
            sendText(mapNewEmp.get("password"), addEmployeePage.passwordTextFieldBox);
            sendText(mapNewEmp.get("confirmPassword"), addEmployeePage.confirmPasswordBox);

            String empIdValue = addEmployeePage.employeeIdField.getAttribute("value");
            Assert.assertTrue(addEmployeePage.saveButton.isDisplayed());
            click(addEmployeePage.saveButton);
            Thread.sleep(3000);
            click(dashboardPage.empListOption);
            sendText(empIdValue, employeeSearchPage.idTextField);
            click(employeeSearchPage.searchButton);

            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop");
                //it will return one by one all the data from the row
                String rowText = rowData.get(i).getText();
                //it will print the complete row data
                //output of this will be empid firstname middlename lastname
                System.out.println(rowText);
                //we have to verify this data against the data coming from excel

                String expectedData = empIdValue + " " + mapNewEmp.get("firstName") + " " +
                        mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");
                Assert.assertEquals(expectedData, rowText);
            }


            click(dashboardPage.addEmployeeButton);
        }
    }
}

