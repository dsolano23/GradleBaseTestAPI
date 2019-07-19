package com.gdt.baseClient.beans;

import com.gdt.baseClient.constants.HttpHeadersEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * models class to doing the http request
 * @author David Solano
 * 2019 / 07 / 14
 */
@Getter
@Setter
public class RequestModelDtoOld {

    private String url;
    private HashMap<String,Object> headers;
    private Map<String, Object> pathParams;
    private Map<String, Object> queryParams;
    private Map<String, List<?>> multiValuedQueryParams;
    private String body;
    private HashMap<String,String> multipartTextBody;
    private HashMap<String,File> files;


    public final static String PAGE_NUMBER_QUERY_PARAM = "page";
    public final static String PAGE_RESULTS_QUERY_PARAM = "limit";

    public final static String SORT_FIELD_QUERY_PARAM = "sort";
    public final static String SORT_DIR_QUERY_PARAM = "dir";

    public RequestModelDtoOld() {
        url = new String();
        headers = new HashMap<String, Object>();
        pathParams = new HashMap<String, Object>();
        queryParams = new HashMap<String, Object>();
        multiValuedQueryParams = new HashMap<String, List<?>>();
        body = new String();
        multipartTextBody = new HashMap<String,String>();
        files = new HashMap<String,File>();
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
        HashMap<String, Integer> paginationQueryParams = getPaginationQueryParams(pageNumber, pageSize);
        this.queryParams.putAll(paginationQueryParams);
    }

    public void fillAuthorization(String token) {
        if (token != null)
            this.headers.put(HttpHeadersEnum.AUTHORIZATION.headerKey,"Bearer "+token);
    }

    public void fillHeaderAccept(String acceptType) {
        if (acceptType != null)
            this.headers.put(HttpHeadersEnum.ACCEPT.headerKey,acceptType);
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
            this.queryParams.put(PAGE_NUMBER_QUERY_PARAM, pageNumber);
    }

    @Override
    public String toString() {

        String headersFormatted="";
        String bodyFormatted="";
        String multipartTextBodyFormatted="";

        if ( headers != null ) {
            for(Map.Entry<String, Object> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();
                headersFormatted = headersFormatted + "\n\t\t\t" + key + ": " + value;
            }
        }
        if (body !=null){
            bodyFormatted = body.replace("\n","\n\t\t\t");
        }
        if (multipartTextBody != null){
            for(Map.Entry<String, String> entry : getMultipartTextBody().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                multipartTextBodyFormatted = multipartTextBodyFormatted + "\n\t\t\t" + key + ": " + value;
            }
        }

        return "Requested{\n" +
                "\t\t\turl='" + getUrl() +
                ",\n\t\theaders=" + headersFormatted +
                ",\n\t\tbody='" + bodyFormatted +
                ",\n\t\tmultipartTextBody=" + multipartTextBodyFormatted +
                ",\n\t\tfiles=" + files +
                "\n\t\t"+'}';
    }
}
