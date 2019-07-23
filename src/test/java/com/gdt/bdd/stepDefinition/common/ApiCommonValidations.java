package com.gdt.bdd.stepDefinition.common;

import com.gdt.baseClient.beans.RestIterationDto;
import com.gdt.enviroment.EnvironmentConstantsNames;
import com.gdt.enviroment.ScenarioContext;
import com.gdt.exception.APIException;
import com.gdt.exception.beans.ErrorDto;
import com.gdt.exception.constants.ErrorsEnum;
import com.gdt.models.beans.FAQsDto;
import com.gdt.models.controllers.FAQsController;
import com.gdt.utilsType.EnvPropertiesManagement;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.CucumberConstants;

public class ApiCommonValidations {

    /**
     * The Logger
     */
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ApiCommonValidations.class);

    private ScenarioContext scenarioContext;
    private String envMaxTimeForRespond = (String) EnvPropertiesManagement.getProperty(EnvironmentConstantsNames.MAX_TIMEOUT_FOR_RESPOND, String.class);

    public ApiCommonValidations(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


    @Then("^The (.+) Http (\\d+) API code will be received in less than (.+) milliseconds")
    public void the_api_response_is(String requestId, int httpCodeExpected, String elapsedTime) throws Throwable {

        Long elapsedTimeExpected = null;

        Response responseReceived = scenarioContext.getRestAssuredResponse(requestId);

        if (responseReceived == null) {
            Assert.fail("DON'T HAVE A RestIterationDto respond valid");
        }

        String assertTrace = " Bad Http Code - Received: " + responseReceived.getStatusCode() + " & Expected: " + httpCodeExpected;
        Assert.assertEquals(assertTrace, httpCodeExpected, responseReceived.getStatusCode());

        if(elapsedTime.equalsIgnoreCase(CucumberConstants.ENVIROMENT_MAX_TIMEOUT_FOR_RESPOND)){
            elapsedTimeExpected = Long.parseLong(envMaxTimeForRespond);
        }else {
            elapsedTimeExpected = Long.parseLong(elapsedTime);
        }

        assertTrace = " Bad max timeout - Received: " + responseReceived.getTime() + " & Expected <: " + elapsedTimeExpected;
        Assert.assertTrue(assertTrace, responseReceived.getTime() < elapsedTimeExpected);
    }

    @And("^The (.+) api response content will be (.+)$")
    public void the_api_response_content_will_be(String requestId, String resultExpected) throws Throwable {
        String resultReceived = "BAD TYPE OF RESPONSE";
        Object responseObject = scenarioContext.get(requestId);
        switch (resultExpected) {

            case FAQsController.FAQ_RESPONSE:


                if (responseObject != null && responseObject instanceof RestIterationDto) {
                    FAQsDto faQsDto = ((RestIterationDto) responseObject).getResponse().getBody().as(FAQsDto.class);
                    if (faQsDto != null) {
                        resultReceived = FAQsController.FAQ_RESPONSE;
                    }
                    break;
                }

                String assertTrace = " The api response content will be ERROR  Received: " + resultReceived + " & Expected: " + resultExpected;
                Assert.assertEquals(assertTrace, resultExpected, resultReceived);
        }
    }

    @And("^The api message error code will be (\\d+)$")
    public void the_api_message_will_be(Integer errorCodeResultExpected) throws Throwable {
        Scenario scenario = scenarioContext.getScenario();
        ErrorsEnum errorExpected = ErrorsEnum.getByCode(errorCodeResultExpected);
        ErrorDto errorReceived;
        String errorTypeResultExpected = errorExpected.getType();
        String errorDetailResultExpected = errorExpected.getDetail();
        String errorTypeResultReceived = "";
        String errorDetailResultReceived = "";

        Object o = scenarioContext.get(ScenarioContext.HTTP_API_EXCEPTION_CODE);
        Object oResp = scenarioContext.get(ScenarioContext.HTTP_STATUS_CODE);

        Integer errorCodeResultReceived = 0;
        String assertTrace = " Bad The API response Code - Received: " + errorCodeResultReceived + " & Expected: " + errorCodeResultExpected;
        if (o != null && o instanceof APIException) {
            ErrorDto[] errors = ((APIException) o).getErrors();
            Integer nunErrors = errors.length;
            for (int i = 0; i < nunErrors; i++) {
                errorCodeResultReceived = errors[i].getCode();
                if (errorCodeResultReceived.compareTo(errorCodeResultExpected) == 0) {
                    errorReceived = errors[i];
                    errorTypeResultReceived = errorReceived.getType();
                    errorDetailResultReceived = errorReceived.getDetail();
                    Assert.assertEquals(assertTrace, errorCodeResultExpected, errorCodeResultReceived);
                    scenario.write("Code   - Received: " + errorCodeResultReceived);
                    scenario.write("Code   - Expected: " + errorCodeResultExpected);
                    assertTrace = " Bad The API response Type - Received: " + errorTypeResultReceived + " & Expected: " + errorTypeResultExpected;
                    Assert.assertEquals(assertTrace, errorTypeResultExpected, errorTypeResultReceived);
                    scenario.write("Type   - Received: " + errorTypeResultReceived);
                    scenario.write("Type   - Expected: " + errorTypeResultExpected);
                    assertTrace = " Bad The API response Detail - Received: " + errorDetailResultReceived + " & Expected: " + errorDetailResultExpected;
                    Assert.assertTrue(assertTrace, errorDetailResultReceived.contains(errorDetailResultExpected));
                    scenario.write("Detail - Received: " + errorDetailResultReceived);
                    scenario.write("Detail - Expected: " + errorDetailResultExpected);
                    break;
                }
            }
        } else if (oResp != null && oResp instanceof Integer) {
            errorCodeResultReceived = (Integer) oResp;
        }
        Assert.assertEquals(assertTrace, errorCodeResultExpected, errorCodeResultReceived);
    }
}

