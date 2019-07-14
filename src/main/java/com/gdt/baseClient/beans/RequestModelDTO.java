package com.gdt.baseClient.beans;

import java.io.File;
import java.util.HashMap;

/**
 * model class to doing the http request
 * @author David Solano
 * 2019 / 07 / 14
 */
public class RequestModelDTO {

    private String url;
    private HashMap<String,String> headers;
    private String body;
    private HashMap<String,String> multipartTextBody;
    private HashMap<String,File> files;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HashMap<String, String> getMultipartTextBody() {
        return multipartTextBody;
    }

    public void setMultipartTextBody(HashMap<String, String> multipartTextBody) {
        this.multipartTextBody = multipartTextBody;
    }

    public HashMap<String, File> getFiles() {
        return files;
    }

    public void setFiles(HashMap<String, File> files) {
        this.files = files;
    }


}
