package com.gdt.exception.beans;

import java.util.Arrays;

public class ObjectErrorBean {

    ErrorDto[] objErrors;

    public ErrorDto[] getObjErrors() {
        return objErrors;
    }

    public void setObjErrors(ErrorDto[] objErrors) {
        this.objErrors = objErrors;
    }

    @Override
    public String toString() {
        return "ObjectErrorBean{" +
                "objErrors=" + Arrays.toString(objErrors) +
                '}';
    }
}
