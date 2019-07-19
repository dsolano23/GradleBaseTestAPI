package com.gdt.baseClient.models;

import com.gdt.baseClient.beans.ObjectErrorsDto;
import com.gdt.baseClient.beans.ResponseModelDto;


public class StandardResponse {

    ResponseModelDto rawResponse;
    ObjectErrorsDto objErrors;
    Object filterResponse;

    public ResponseModelDto getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(ResponseModelDto rawResponse) {
        this.rawResponse = rawResponse;
    }

    public ObjectErrorsDto getObjErrors() {
        return objErrors;
    }

    public void setObjErrors(ObjectErrorsDto objErrors) {
        this.objErrors = objErrors;
    }

    public Object getFilterResponse() {
        return filterResponse;
    }

    public void setFilterResponse(Object filterResponse) {
        this.filterResponse = filterResponse;
    }
}
