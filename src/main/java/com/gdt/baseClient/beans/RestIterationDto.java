package com.gdt.baseClient.beans;


import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter()
public class RestIterationDto {

    private RequestSpecification requestSpecification;
    private Response response;
    private FilterableRequestSpecification filterableRequestSpecification;

    public RestIterationDto(RequestSpecification requestSpecification, Response response) {
        this.response = response;
        this.requestSpecification = requestSpecification;
    }

    public Map<String, String> getQueryParams() {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequestSpecification();
        return reqFilterSpecification.getQueryParams();
    }

    public String getQueryParam(final String key) {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequestSpecification();
        Map<String, String> queryParams = reqFilterSpecification.getQueryParams();
        if (queryParams != null) {
            return queryParams.get(key);
        }
        return null;
    }

    public Map<String, String> getPathParams() {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequestSpecification();
        return reqFilterSpecification.getPathParams();
    }

    public String getPathParam(final String key) {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequestSpecification();
        Map<String, String> pathParams = reqFilterSpecification.getPathParams();
        if (pathParams != null) {
            return pathParams.get(key);
        }
        return null;
    }

    public String getRequestUrl() {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequestSpecification();
        return reqFilterSpecification.getURI();
    }
}
