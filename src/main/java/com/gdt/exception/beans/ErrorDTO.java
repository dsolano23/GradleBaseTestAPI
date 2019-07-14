package com.gdt.exception.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ErrorDTO {

    private static final long serialVersionUID = 3313327362257240747L;

    private String type;
    private Integer code;
    private String detail;

    public ErrorDTO(String type,Integer code, String detail) {
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
        return "ErrorDTO{" +
                "type='" + type + '\'' +
                ", code=" + code +
                ", detail='" + detail + '\'' +
                '}';
    }
}