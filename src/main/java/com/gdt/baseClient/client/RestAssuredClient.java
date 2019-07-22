package com.gdt.baseClient.client;

import com.gdt.baseClient.beans.AbstractBaseJson;
import com.gdt.baseClient.beans.BasePageableJson;
import com.gdt.baseClient.beans.RequestDto;
import com.gdt.baseClient.beans.RestIterationDto;
import com.gdt.baseClient.constants.EnumStatusFamily;
import com.gdt.baseClient.utils.DeserializationContext;
import com.gdt.baseClient.utils.PaginationContext;
import com.gdt.enviroment.EnvironmentConstantsNames;
import com.gdt.utilsType.EnvPropertiesManagement;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestAssuredClient {

    private String mainUrl = (String) EnvPropertiesManagement.getProperty(EnvironmentConstantsNames.MAIN_URL,String.class);

    protected DeserializationContext deserializationContext;
    protected PaginationContext paginationContext;

    private static Logger logger= LoggerFactory.getLogger(RestAssuredClient.class);


    protected boolean conditionToBreakPaginationLoop(final Response response, final int i) {

        if (EnumStatusFamily.isAnErrorCode(response.getStatusCode())) {
            return true;
        }

        String responseBody = response.getBody().asString();
        if (responseBody == null || responseBody.isEmpty()) {
            return true;
        }


        BasePageableJson pageJson = response.as(BasePageableJson.class);
        BasePageableJson.EnumPageStatus pageStatus = pageJson.isLastPage(this.paginationContext);

        if ((pageStatus == BasePageableJson.EnumPageStatus.LAST_PAGE) || (pageStatus == BasePageableJson.EnumPageStatus.NOT_PAGINATED)) {
            return true;
        }

        int times = i - this.paginationContext.getFirstPageIndex() + 1;
        if (times > pageJson.getTotal()) {
            return true;
        }
        List<AbstractBaseJson> collection = this.deserializationContext.deserializeCollection(response);

        return collection.isEmpty();
    }

    public RequestSpecification getRequestSpecificationFromDTO(RequestDto requestDto) {
        RequestSpecification requestSpecification = RestAssured.given();

        Map<String, Object> headers = requestDto.getHeaders();
        if (headers != null && !headers.isEmpty()) {
            requestSpecification.headers(headers);
        }

        Map<String, Object> pathParams = requestDto.getPathParams();
        if (pathParams != null && !pathParams.isEmpty()) {
            requestSpecification.pathParams(pathParams);
        }

        Map<String, Object> queryParams = requestDto.getQueryParams();
        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpecification.queryParams(queryParams);
        }
        String body = requestDto.getBody();
        if (body != null && !body.isEmpty()) {
            requestSpecification.body(body);
        }

        Map<String, List<?>> multiValuedQueryParams = requestDto.getMultiValuedQueryParams();
        if (multiValuedQueryParams != null && !multiValuedQueryParams.isEmpty()) {
            for (Map.Entry<String, List<?>> multiValueParam : multiValuedQueryParams.entrySet()) {
                requestSpecification.queryParam(multiValueParam.getKey(), multiValueParam.getValue());
            }
        }

        return requestSpecification;
    }


    public RestIterationDto launchGetRequest(RequestDto requestDto, final String url) {
        RequestSpecification reqSpec = this.getRequestSpecificationFromDTO(requestDto);

        logger.info(String.format("Endpoint: %s", url));
        Response response = reqSpec.when().get(url);

        // Needed to force to log the body response
        logger.info(String.format("<< Response body: \"%s\"", response.getBody().prettyPrint()).replace("\n","\n\t\t\t"));

        return new RestIterationDto(reqSpec,response, response.getTime());
    }

    public RestIterationDto launchGetRequest(RequestDto requestDto) {
        String finalUrl = this.mainUrl;
        if (requestDto.getApiPath() != null){
            finalUrl = finalUrl + requestDto.getApiPath();
        }
        return this.launchGetRequest(requestDto, finalUrl);
    }

    public RestIterationDto launchPostRequest(RequestDto requestDto, final String url) {

        RequestSpecification reqSpec = this.getRequestSpecificationFromDTO(requestDto);

        logger.info(String.format("Endpoint: %s", url));
        Response response = reqSpec.when().post(url);

        // Needed to force to log the body response
        logger.info(String.format("<< Response body: \"%s\"", response.getBody().prettyPrint()).replace("\n","\n\t\t\t"));

        return new RestIterationDto(reqSpec,response, response.getTime());
    }

    public RestIterationDto launchPostRequest(RequestDto requestDto) {
        String finalUrl = this.mainUrl;
        if (requestDto.getApiPath() != null){
            finalUrl = finalUrl + requestDto.getApiPath();
        }
        return this.launchPostRequest(requestDto, finalUrl);
    }

    public RestIterationDto launchPutRequest(RequestDto requestDto, final String url) {
        RequestSpecification reqSpec = this.getRequestSpecificationFromDTO(requestDto);

        logger.info(String.format("Endpoint: %s", url));
        Response response = reqSpec.when().put(url);

        // Needed to force to log the body response
        logger.info(String.format("<< Response body: \"%s\"", response.getBody().prettyPrint()).replace("\n","\n\t\t\t"));

        return new RestIterationDto(reqSpec,response, response.getTime());
    }

    public RestIterationDto launchPutRequest(RequestDto requestDto) {
        String finalUrl = this.mainUrl;
        if (requestDto.getApiPath() != null){
            finalUrl = finalUrl + requestDto.getApiPath();
        }
        return this.launchPutRequest(requestDto, finalUrl);
    }

    public RestIterationDto launchDeleteRequest(RequestDto requestDto, final String url) {
        RequestSpecification reqSpec = this.getRequestSpecificationFromDTO(requestDto);

        logger.info(String.format("Endpoint: %s", url));
        Response response = reqSpec.when().delete(url);

        // Needed to force to log the body response
        logger.info(String.format("<< Response body: \"%s\"", response.getBody().prettyPrint()).replace("\n","\n\t\t\t"));

        return new RestIterationDto(reqSpec,response, response.getTime());
    }

    public RestIterationDto launchDeleteRequest(RequestDto requestDto) {
        String finalUrl = this.mainUrl;
        if (requestDto.getApiPath() != null){
            finalUrl = finalUrl + requestDto.getApiPath();
        }
        return this.launchDeleteRequest(requestDto, finalUrl);
    }

    public List<RestIterationDto> launchGetRequestsForAllPagesUsingDTO(RequestDto requestDto) {
        List<RestIterationDto> restInteractions = new ArrayList<RestIterationDto>();
        for (int i = this.paginationContext.getFirstPageIndex(); ; i++) {
            requestDto.setPageNumber(i);
            RestIterationDto restInteraction = this.launchGetRequest(requestDto);
            restInteractions.add(restInteraction);

            Response response = restInteraction.getResponse();
            if (this.conditionToBreakPaginationLoop(response, i)) {
                break;
            }
        }
        logger.info(String.format("Número de páginas: %d", restInteractions.size()));
        return restInteractions;
    }


}
