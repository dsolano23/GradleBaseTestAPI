package com.gdt.bdd.stepDefinition.FAQmanagement;

import com.gdt.baseClient.beans.RestIterationDto;
import com.gdt.enviroment.ScenarioContext;
import com.gdt.models.beans.FAQsDto;
import com.gdt.models.controllers.FAQsController;
import com.gdt.models.faqs.CreateFAQsRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.CucumberDataManagement;

public class FAQsSteps {

    FAQsController client = new FAQsController();
    /**
     * The Logger
     */
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(FAQsSteps.class);

    private static final Integer stingLimitLengthDefault = 255;
    private static final Integer stingLimitLengthCustomized = 300;

    private ScenarioContext scenarioContext;

    public FAQsSteps(ScenarioContext scenarioContext) {
        this.scenarioContext=scenarioContext;
    }

    @Given("^The FAQ from with the data: code: (.+), answer: (.+), question: (.+), link: (.+)$")
    public void faqFromData(String code, String answer, String question, String link) throws Throwable {
        FAQsDto virtualFAQ = new FAQsDto();
        if ((CucumberDataManagement.setValue(code))) {
            virtualFAQ.setCode(CucumberDataManagement.getValidString(code,stingLimitLengthCustomized));
        }
        if ((CucumberDataManagement.setValue(answer))) {
            virtualFAQ.setAnswer(CucumberDataManagement.getValidString(answer,stingLimitLengthDefault));
        }
        if ((CucumberDataManagement.setValue(question))) {
            virtualFAQ.setQuestion(CucumberDataManagement.getValidString(question,stingLimitLengthDefault));
        }
        if ((CucumberDataManagement.setValue(link))) {
            virtualFAQ.setLink(CucumberDataManagement.getValidString(link,stingLimitLengthCustomized));
        }
        this.scenarioContext.put(FAQsController.CREATE_FAQs_FROM, virtualFAQ);
    }

    @When("^Try to create the new FAQ (.+)$")
    public void try_to_create_the_new_faq(String requestId) throws Throwable {

        if( scenarioContext.get(FAQsController.CREATE_FAQs_FROM) != null && scenarioContext.get(FAQsController.CREATE_FAQs_FROM) instanceof FAQsDto ){
            FAQsDto virtualFAQ = (FAQsDto) scenarioContext.get(FAQsController.CREATE_FAQs_FROM);
            CreateFAQsRequest createFAQsRequest = new CreateFAQsRequest(virtualFAQ);
            RestIterationDto restIterationDto = client.createFAQs(scenarioContext,createFAQsRequest);
            scenarioContext.put(requestId,restIterationDto);
            /*tRequestHttpCode());
            environment.put(ScenarioContext.HTTP_ELAPSED_TIME_RESPOND, restIterationDto.getRawResponse().getElapsedTime());
            environment.put(FAQsController.FAQ_RESPONSE, restIterationDto);*/
        }
    }
/*
    @When("^Try to updated the FAQ with the values: id: anyone, code: (.+), answer: (.+), question: (.+), link: (.+)$")
    public void try_to_update_the_a_faq(String code, String answer, String question, String link) throws Throwable {
        FAQsDto virtualFAQ = new FAQsDto();
        if (((StandardResponse) environment.get(FAQsController.FAQ_RESPONSE)).getFilterResponse() instanceof FAQsDto){
            virtualFAQ = (FAQsDto) ((StandardResponse) environment.get(FAQsController.FAQ_RESPONSE)).getFilterResponse();
        }

        if ((CucumberDataManagement.setValue(code))) {
            virtualFAQ.setCode(CucumberDataManagement.getValidString(code,stingLimitLengthCustomized));
        }
        if ((CucumberDataManagement.setValue(answer))) {
            virtualFAQ.setAnswer(CucumberDataManagement.getValidString(answer,stingLimitLengthDefault));
        }
        if ((CucumberDataManagement.setValue(question))) {
            virtualFAQ.setQuestion(CucumberDataManagement.getValidString(question,stingLimitLengthDefault));
        }
        if ((CucumberDataManagement.setValue(link))) {
            virtualFAQ.setLink(CucumberDataManagement.getValidString(link,stingLimitLengthCustomized));
        }

        UpdateFAQsRequest updateFAQsRequest = new UpdateFAQsRequest(virtualFAQ);
        StandardResponse standardResponse = client.updateFAQ(environment,updateFAQsRequest);
        environment.put(ScenarioContext.HTTP_STATUS_CODE, standardResponse.getRawResponse().getRequestHttpCode());
        environment.put(ScenarioContext.HTTP_ELAPSED_TIME_RESPOND, standardResponse.getRawResponse().getElapsedTime());
        environment.put(FAQsController.FAQ_RESPONSE, standardResponse);
    }

    @And("^The response of FAQ object will come with the values: id: (.+), code: (.+), answer: (.+), question: (.+), link: (.+)$")
    public void faqObjectRespondValidation(String id, String code, String answer, String question, String link) throws Throwable {
        Object responseObject = null;
        FAQsDto faQsDtoRecied = new FAQsDto();
        responseObject = environment.get(FAQsController.FAQ_RESPONSE);
        if (responseObject != null && responseObject instanceof StandardResponse && ((StandardResponse) responseObject).getFilterResponse() instanceof FAQsDto) {
            faQsDtoRecied = (FAQsDto) ((StandardResponse) responseObject).getFilterResponse();
        }

        String resultExpected = id;
        String resultReceived = faQsDtoRecied.getId();
        String assertTrace = "The response of FAQ object will come with the values ERROR  Received: id: " + resultReceived +" & Expected id: " + resultExpected;
        if(resultExpected.equalsIgnoreCase(CucumberConstants.ANY_VALUE) && resultReceived.length() == 0){
            Assert.fail(assertTrace);
        }

        resultExpected = code.trim();
        resultReceived = faQsDtoRecied.getCode().trim();
        if(resultExpected.equalsIgnoreCase(CucumberConstants.EQUAL_UPPER_LIMIT) && resultReceived.length() != stingLimitLengthCustomized){
            assertTrace = "The response of FAQ object will come with the values ERROR  Received: code: " + resultReceived.length() +" & Expected code ==: " + stingLimitLengthCustomized;
            Assert.fail(assertTrace);
        }else{
            assertTrace = "The response of FAQ object will come with the values ERROR  Received: code: " + resultReceived +" & Expected code: " + resultExpected;
            Assert.assertEquals(assertTrace, resultExpected, resultReceived);
        }

        resultExpected = answer.trim();
        resultReceived = faQsDtoRecied.getAnswer().trim();
        if(resultExpected.equalsIgnoreCase(CucumberConstants.EQUAL_UPPER_LIMIT) && resultReceived.length() != stingLimitLengthDefault){
            assertTrace = "The response of FAQ object will come with the values ERROR  Received: answer: " + resultReceived.length() +" & Expected answer ==: " + stingLimitLengthDefault;
            Assert.fail(assertTrace);
        }else{
            assertTrace = "The response of FAQ object will come with the values ERROR  Received: answer: " + resultReceived +" & Expected answer: " + resultExpected;
            Assert.assertEquals(assertTrace, resultExpected, resultReceived);
        }

        resultExpected = question.trim();
        resultReceived = faQsDtoRecied.getQuestion().trim();
        if(resultExpected.equalsIgnoreCase(CucumberConstants.EQUAL_UPPER_LIMIT) && resultReceived.length() != stingLimitLengthDefault){
            assertTrace = "The response of FAQ object will come with the values ERROR  Received: question: " + resultReceived.length() +" & Expected question ==: " + stingLimitLengthDefault;
            Assert.fail(assertTrace);
        }else{
            assertTrace = "The response of FAQ object will come with the values ERROR  Received: question: " + resultReceived +" & Expected question: " + resultExpected;
            Assert.assertEquals(assertTrace, resultExpected, resultReceived);
        }

        resultExpected = link.trim();
        resultReceived = faQsDtoRecied.getLink().trim();
        if(resultExpected.equalsIgnoreCase(CucumberConstants.EQUAL_UPPER_LIMIT) && resultReceived.length() != stingLimitLengthCustomized){
            assertTrace = "The response of FAQ object will come with the values ERROR  Received: link: " + resultReceived.length() +" & Expected link ==: " + stingLimitLengthCustomized;
            Assert.fail(assertTrace);
        }else{
            assertTrace = "The response of FAQ object will come with the values ERROR  Received: link: " + resultReceived +" & Expected link: " + resultExpected;
            Assert.assertEquals(assertTrace, resultExpected, resultReceived);
        }
    }*/

}
