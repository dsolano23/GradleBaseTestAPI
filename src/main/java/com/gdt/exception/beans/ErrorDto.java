package com.gdt.exception.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDto {

    private String type;
    private Integer code;
    private String detail;

    public ErrorDto(String type, Integer code, String detail) {
        super();
        this.type = type;
        this.code = code;
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "type='" + type + '\'' +
                ", code=" + code +
                ", detail='" + detail + '\'' +
                '}';
    }
}