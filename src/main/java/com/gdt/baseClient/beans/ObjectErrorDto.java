package com.gdt.baseClient.beans;

public class ObjectErrorDto {
    private int code;
    private String detail;
    private String type;

    public ObjectErrorDto() {
    }

    public ObjectErrorDto(int code, String detail, String type) {
        this.code = code;
        this.detail = detail;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
