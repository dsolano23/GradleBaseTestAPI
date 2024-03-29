package com.gdt.baseClient.beans;

import com.beust.jcommander.internal.Maps;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdt.baseClient.constants.HttpHeadersEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RequestDto {

    private String apiPath;
    private Map<String, Object> headers;
    private Map<String, Object> pathParams;
    private Map<String, Object> queryParams;
    private Map<String, List<?>> multiValuedQueryParams;
    private String body;

    public final static String PAGE_NUMBER_QUERY_PARAM = "page";
    public final static String PAGE_RESULTS_QUERY_PARAM = "limit";

    public final static String SORT_FIELD_QUERY_PARAM = "sort";
    public final static String SORT_DIR_QUERY_PARAM = "dir";

    public RequestDto() {
        Map<String, Object> header = Maps.newHashMap();
        header.put(HttpHeadersEnum.CONTENT_TYPE_JSON.headerKey, HttpHeadersEnum.CONTENT_TYPE_JSON.headerValue );
        headers = new HashMap<String, Object>();
        this.setHeaders(header);
        pathParams = new HashMap<String, Object>();
        queryParams = new HashMap<String, Object>();
        multiValuedQueryParams = new HashMap<String, List<?>>();
        body = new String();
    }


    static public HashMap<String, Integer> getPaginationQueryParams(Integer pageNumber, Integer pageSize) {
        HashMap<String, Integer> queryPaginationParams = new HashMap<String, Integer>();
        if (pageNumber != null) {
            queryPaginationParams.put(PAGE_NUMBER_QUERY_PARAM, pageNumber);
        }

        if (pageSize != null) {
            queryPaginationParams.put(PAGE_RESULTS_QUERY_PARAM, pageSize);
        }

        return queryPaginationParams;

    }

    public void addBody(String value) {
        this.body = value;
    }

    public void addBody(Object jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            if (jsonObject != null){
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                json = objectMapper.writeValueAsString(jsonObject);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.body = json;
    }

    public void addHeader(String key, Object value) {
        this.headers.put(key, value);
    }

    public void addQueryParam(String key, Object value) {
        this.queryParams.put(key, value);
    }

    public void addPathParam(String key, Object value) {
        this.pathParams.put(key, value);
    }

    public void addMultiValuedQueryParam(String key, List<?> values) {
        this.multiValuedQueryParams.put(key, values);
    }

    public void fillPagination(Integer pageNumber, Integer pageSize) {
        HashMap<String, Integer> paginationQueryParams = RequestDto.getPaginationQueryParams(pageNumber, pageSize);
        this.queryParams.putAll(paginationQueryParams);
    }

    public void fillAuthorization(String token) {
        if (token != null)
            this.headers.put(HttpHeadersEnum.AUTHORIZATION.headerKey,"Bearer "+token);
    }

    public void fillHeaderAccept(String acceptType) {
        if (acceptType != null)
            this.headers.put(HttpHeadersEnum.ACCEPT.headerKey, acceptType);
    }

    public void fillHeaderContentType(String contentType) {
        if (contentType != null)
            this.headers.put(HttpHeadersEnum.CONTENT_TYPE.headerKey, contentType);
    }

    public void fillHeaderLanguage(String language) {
        if (language != null)
            this.headers.put(HttpHeadersEnum.ACCEPT_LANGUAGE.headerKey, language);
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber != null)
            this.queryParams.put(RequestDto.PAGE_NUMBER_QUERY_PARAM, pageNumber);
    }

    @Override
    public String toString() {


        String headersFormatted="";
        String queryParamsFormatted="";
        String bodyFormatted="";
        String multipartTextBodyFormatted="";

        if ( headers != null ) {
            for(Map.Entry<String, Object> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                headersFormatted = headersFormatted + "\n\t\t\t" + key + ": " + value;
            }
        }

        if ( queryParams != null ) {
            queryParamsFormatted = "?";
            for(Map.Entry<String, Object> entry : queryParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                queryParamsFormatted = queryParamsFormatted + key + "=" + value + "&";
            }
            queryParamsFormatted = queryParamsFormatted.substring(0,queryParamsFormatted.length() - 1);
        }

        if (body !=null){
            bodyFormatted =body.replace("{","{\n\t\t\t\t");
            bodyFormatted = bodyFormatted.replace(",",",\n\t\t\t\t");
            bodyFormatted = bodyFormatted.replace("}","\n\t\t\t\t}");
        }
        /*if (multiValuedQueryParams != null){
            for(Map.Entry<String, String> entry : multiValuedQueryParams().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                multipartTextBodyFormatted = multipartTextBodyFormatted + "\n\t\t\t" + key + ": " + value;
            }
        }*/

        return "\t\tRequested{\n" +
                "\t\t\turl='" + apiPath +
                ",\n\t\t\theaders=" + headersFormatted +
                ",\n\t\t\tqueryParams=" + queryParamsFormatted +
                "\n\t\t\tbody='" + bodyFormatted +
                ",\n\t\t\tmultipartTextBody=" + multipartTextBodyFormatted +
                "\n\t\t"+'}';
    }
}
