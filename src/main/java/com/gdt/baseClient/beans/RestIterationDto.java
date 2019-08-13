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
    private Long elapsedTime;
    private FilterableRequestSpecification filterableRequestSpecification;

    public RestIterationDto(RequestSpecification requestSpecification, Response response, Long elapsedTime) {
        this.response = response;
        this.requestSpecification = requestSpecification;
        this.elapsedTime = elapsedTime;
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

    @Override
    public String toString() {

        String headersFormatted="";
        String bodyFormatted="";

        if ( this.getResponse().getHeaders() != null ) {
            headersFormatted = "\n\t\t\t\t" + this.getResponse().getHeaders().toString().replace("\n","\n\t\t\t\t");
        }
        if (this.getResponse().getBody() !=null){
            bodyFormatted = this.getResponse().getBody().prettyPrint().replace("\n","\n\t\t\t");
        }

        return "\tRespond{\n" +

                "\t\t\tmethodType=" + this.response.getContentType() +
                "\n\t\t\trequestHttpCode=" + this.response.getStatusCode() +
                "\n\t\t\telapsedTime=" + elapsedTime +
                "\n\t\t\theaders=" + headersFormatted +
                "\n\t\t\tbodyContent='" + bodyFormatted+
                "\n\t\t"+'}';
    }
}
