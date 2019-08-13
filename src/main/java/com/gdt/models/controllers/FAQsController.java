package com.gdt.models.controllers;


import com.gdt.baseClient.beans.RequestDto;
import com.gdt.baseClient.beans.RestIterationDto;
import com.gdt.baseClient.client.RestAssuredClient;
import com.gdt.enviroment.EnvironmentConstantsNames;
import com.gdt.enviroment.ScenarioContext;
import com.gdt.models.faqs.CreateFAQsRequest;
import com.gdt.models.faqs.UpdateFAQsRequest;
import com.gdt.utilsType.EnvPropertiesManagement;
import io.cucumber.core.api.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FAQsController extends RestAssuredClient {

    private static Logger logger= LoggerFactory.getLogger(FAQsController.class);

    private String mainUrl = (String) EnvPropertiesManagement.getProperty(EnvironmentConstantsNames.MAIN_URL,String.class);


    private static final String CREATE_FAQs_URL ="/v1/faqs";

    private static final String UPDATE_FAQ_URL ="/v1/faqs/";

    public static final String CREATE_FAQs_FROM = "createFAQsFrom";

    public static final String FAQ_RESPONSE = "FaqResponse";

    public RestIterationDto createFAQs(ScenarioContext scenarioContext, CreateFAQsRequest createFAQsRequest) throws IOException {
        Scenario scenario = scenarioContext.getScenario();
        RequestDto requestDto = new RequestDto();

        requestDto.addBody(createFAQsRequest);
        requestDto.setApiPath(CREATE_FAQs_URL);

        scenario.write("POST ----- Executed: - \n" + requestDto.toString());

        RestIterationDto restIterationDto = this.launchPostRequest(requestDto);

        scenario.write("POST -----  Respond: - \n" + restIterationDto.toString());

        scenario.write("POST -- ElapsedTime: " + restIterationDto.getElapsedTime());

        return restIterationDto;

    }

    public RestIterationDto updateFAQ(ScenarioContext scenarioContext, UpdateFAQsRequest updateFAQsRequest) throws IOException {
        Scenario scenario = scenarioContext.getScenario();
        RequestDto requestDto = new RequestDto();

        requestDto.addBody(updateFAQsRequest);
        requestDto.setApiPath(UPDATE_FAQ_URL);

        scenario.write("PUT ----- Executed: - \n" + requestDto.toString());

        RestIterationDto restIterationDto = this.launchPutRequest(requestDto);

        scenario.write("PUT -----  Respond: - \n" + restIterationDto.toString());
        scenario.write("PUT -- ElapsedTime: " + restIterationDto.getElapsedTime());

        return restIterationDto;

    }

}
