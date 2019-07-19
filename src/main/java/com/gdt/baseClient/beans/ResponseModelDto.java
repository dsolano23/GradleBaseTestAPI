package com.gdt.baseClient.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * models class to save the http response request
 * @author David Solano
 * 2019 / 07 / 14
 */
@Getter
@Setter
public class ResponseModelDto {


    private String methodType;
    private Integer requestHttpCode;
    private Long elapsedTime;
    private String headers;
    private String bodyContent;

    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_DELETE = "DELETE";



    public ResponseModelDto() {}

    public ResponseModelDto(String methodType, int requestHttpCode, long elapsedTime, String headers, String bodyContent) {
        this.methodType = methodType;
        this.requestHttpCode = requestHttpCode;
        this.elapsedTime = elapsedTime;
        this.headers = headers;
        this.bodyContent = bodyContent;
    }

    @Override
    public String toString() {


        return "Respond{\n" +
                "\t\t\tmethodType=" + methodType +
                "\n\t\t\trequestHttpCode=" + requestHttpCode +
                "\n\t\t\telapsedTime=" + elapsedTime +
                "\n\t\t\theaders=" + headers.replace(",",",\n\t\t\t\t") +
                "\n\t\t\tbodyContent='" + bodyContent.replace("\n","\n\t\t\t\t") +
                "\n\t\t\t"+'}';
    }
}
