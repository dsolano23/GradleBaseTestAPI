package com.gdt.models.controllers;


import com.gdt.baseClient.beans.ObjectErrorsDto;
import com.gdt.baseClient.beans.RequestModelDtoOld;
import com.gdt.baseClient.constants.HttpHeadersEnum;
import com.gdt.baseClient.models.StandardResponse;
import com.gdt.enviroment.EnvironmentConstantsNames;
import com.gdt.enviroment.ScenarioContext;
import com.gdt.models.beans.FAQsDto;
import com.gdt.models.faqs.CreateFAQsRequest;
import com.gdt.models.faqs.UpdateFAQsRequest;
import com.gdt.utilsType.EnvPropertiesManagement;
import io.cucumber.core.api.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class FAQsController extends AbstractBasicController {

    private static Logger logger= LoggerFactory.getLogger(FAQsController.class);

    private String mainUrl = (String) EnvPropertiesManagement.getProperty(EnvironmentConstantsNames.MAIN_URL,String.class);


    private static final String CREATE_FAQs_URL ="/v1/faqs";

    private static final String UPDATE_FAQ_URL ="/v1/faqs/";

    public static final String CREATE_FAQs_FROM = "createFAQsFrom";

    public static final String FAQ_RESPONSE = "FaqResponse";


    public FAQsController(int maxRetry, int timeToLive) {
        super(maxRetry, timeToLive);
    }

    public StandardResponse createFAQs(ScenarioContext environment, CreateFAQsRequest createFAQsRequest) throws IOException {

        StandardResponse standardResponse = new StandardResponse();

        HashMap<String, Object> headers = new HashMap<>();
        headers.put(HttpHeadersEnum.CONTENT_TYPE_JSON.headerKey, HttpHeadersEnum.CONTENT_TYPE_JSON.headerValue);
        headers.put(HttpHeadersEnum.ACCEPT_CHARSET_UTF8.headerKey, HttpHeadersEnum.ACCEPT_CHARSET_UTF8.headerValue);

        RequestModelDtoOld request = new RequestModelDtoOld();
        request.setUrl( mainUrl + CREATE_FAQs_URL );
        request.setHeaders(headers);
        request.setBody(gson.toJson(createFAQsRequest));

        Scenario scenario = environment.getScenario();
        scenario.write("POST ----- Executed: - \n" + request.toString());

        resp = post(request);
        standardResponse.setRawResponse(resp);
        if(resp.getRequestHttpCode() >= 400 && resp.getBodyContent()!= null ) {
            standardResponse.setObjErrors(gson.fromJson(resp.getBodyContent(), ObjectErrorsDto.class));
        } else {
            standardResponse.setFilterResponse(gson.fromJson(resp.getBodyContent(), FAQsDto.class));
        }
        scenario.write("POST -----  Respond: - \n" + resp.toString());
        scenario.write("POST -- ElapsedTime: -" + standardResponse.getRawResponse().getElapsedTime().toString());
        return  standardResponse;
    }

    public StandardResponse updateFAQ(ScenarioContext environment, UpdateFAQsRequest updateFAQsRequest) throws IOException {

        StandardResponse standardResponse = new StandardResponse();

        HashMap<String, Object> headers = new HashMap<>();
        headers.put(HttpHeadersEnum.CONTENT_TYPE_JSON.headerKey, HttpHeadersEnum.CONTENT_TYPE_JSON.headerValue);
        headers.put(HttpHeadersEnum.ACCEPT_CHARSET_UTF8.headerKey, HttpHeadersEnum.ACCEPT_CHARSET_UTF8.headerValue);

        RequestModelDtoOld request = new RequestModelDtoOld();
        request.setUrl( mainUrl + UPDATE_FAQ_URL + updateFAQsRequest.getId() );
        request.setHeaders(headers);
        request.setBody(gson.toJson(updateFAQsRequest));

        Scenario scenario = environment.getScenario();
        scenario.write("PUT ----- Executed: - \n" + request.toString());

        resp = put(request);
        standardResponse.setRawResponse(resp);
        if(resp.getRequestHttpCode() >= 400 && resp.getBodyContent()!= null ) {
            standardResponse.setObjErrors(gson.fromJson(resp.getBodyContent(), ObjectErrorsDto.class));
        } else {
            standardResponse.setFilterResponse(gson.fromJson(resp.getBodyContent(), FAQsDto.class));
        }
        scenario.write("PUT -----  Respond: - \n" + resp.toString());
        scenario.write("PUT -- ElapsedTime: -" + standardResponse.getRawResponse().getElapsedTime().toString());
        return  standardResponse;
    }

}
