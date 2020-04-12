package com.visy.springboot.demo.bdd.steps;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.visy.springboot.demo.bdd.config.AbstractSteps;
import com.visy.springboot.demo.model.Employee;
import com.visy.springboot.demo.model.Phone;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

/**
 * Step Definition Class for Employee.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeSteps extends AbstractSteps implements En {
    private RequestSpecification request;
    private Response response;

    public EmployeeSteps() {

        Given("user wants to create an employee with the following attributes", (DataTable employeeDt) -> {
            testContext().reset();
            List<Employee> employeeList = employeeDt.asList(Employee.class);

            // First row of DataTable has the employee attributes hence calling get(0) method.
            super.testContext()
                    .setPayload(employeeList.get(0));
        });

        And("with the following phone numbers", (DataTable phoneDt) -> {
            List<Phone> phoneList = phoneDt.asList(Phone.class);
            super.testContext()
                    .getPayload(Employee.class)
                    .setPhones(phoneList);
        });

        When("user saves the new employee {string}", (String testContext) -> {
            String createEmployeeUrl = "/v1/employees";

            // AbstractSteps class makes the POST call and stores response in TestContext
            executePost(createEmployeeUrl);
        });

        /**
         * This can be moved to a Class named 'CommonSteps.java so that it can be reused.
         */
        Then("the save {string}", (String expectedResult) -> {
            Response response = testContext().getResponse();

            switch (expectedResult) {
                case "IS SUCCESSFUL":
                    assertThat(response.statusCode()).isIn(200, 201);
                    break;
                case "FAILS":
                    assertThat(response.statusCode()).isBetween(400, 504);
                    break;
                default:
                    fail("Unexpected error");
            }
        });

    }
}