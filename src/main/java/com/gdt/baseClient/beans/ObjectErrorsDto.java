package com.gdt.baseClient.beans;

import java.util.ArrayList;

public class ObjectErrorsDto {

    ArrayList<ObjectErrorDto> objErrors;

    public ObjectErrorsDto(){}

    public ObjectErrorsDto(ArrayList<ObjectErrorDto> objErrors) {
        this.objErrors = objErrors;
    }

    public ArrayList<ObjectErrorDto> getObjErrors() {
        return objErrors;
    }

    public void setObjErrors(ArrayList<ObjectErrorDto> objErrors) {
        this.objErrors = objErrors;
    }

}
