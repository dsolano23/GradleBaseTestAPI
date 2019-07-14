package com.gdt.baseClient.constants;

/**
 * enum to get the most use headers values
 * @author David Solano
 * 2019 / 07 / 14
 */
public enum HttpHeadersEnum {

    ACCESS_CONTROLL_REQUEST_XAUTH("x-auth-token",null),
    AUTHORIZATION_BASIC("Authorization","Basic"),
    CONTENT_TYPE_HTML("Content-Type","text/html"),
    CONTENT_TYPE_JSON("Content-Type","application/json"),
    CONTENT_TYPE_MULTIPART_FORM("Content-Type","multipart/form-data"),
    ACCEPT_CHARSET_UTF8("Accept-Charset","utf-8"),
    ACCEPT_CHARSET_ISO("Accept-Charset","iso-8859-1"),
    ACCEPT_HTML("Accept","text/html"),
    ACCEPT_JSON("Accept","application/json");

    /**
     * the header key to put in header request
     */
    public String headerKey;
    /**
     * the header value to put in header request
     */
    public String headerValue;

    HttpHeadersEnum(String headerKey, String headerValue) {
        this.headerKey = headerKey;
        this.headerValue = headerValue;
    }
}
