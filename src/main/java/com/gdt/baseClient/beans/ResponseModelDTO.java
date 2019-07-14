package com.gdt.baseClient.beans;

import org.apache.http.Header;

import java.util.Arrays;

/**
 * model class to save the http response request
 * @author David Solano
 * 2019 / 07 / 14
 */
public class ResponseModelDTO {

    private int requestHttpCode;
    private String bodyContent;
    private Header headers[];
    private String MethodType;
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_DELETE = "DELETE";

    public ResponseModelDTO() {}

    public ResponseModelDTO(int requestHttpCode, String bodyContent, Header[] headers) {
        this.requestHttpCode = requestHttpCode;
        this.bodyContent = bodyContent;
        this.headers = headers;
    }

    /**
     *
     * @return the http code response from request
     */
    public int getRequestHttpCode() {
        return requestHttpCode;
    }

    public void setRequestHttpCode(int requestHttpCode) {
        this.requestHttpCode = requestHttpCode;
    }

    /**
     *
     * @return the http body response from request
     */
    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    /**
     *
     * @return the http headers response from request
     */
    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public String getMethodType() {
        return MethodType;
    }

    public void setMethodType(String methodType) {
        MethodType = methodType;
    }

    @Override
    public String toString() {
        return "com.gdt.baseClient.beans.ResponseModelDTO{" +
                "requestHttpCode=" + requestHttpCode +
                ", bodyContent='" + bodyContent + '\'' +
                ", headers=" + Arrays.toString(headers) +
                '}';
    }
}
