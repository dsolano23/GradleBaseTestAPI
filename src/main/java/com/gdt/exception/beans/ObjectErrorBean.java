package com.gdt.exception.beans;

import java.util.Arrays;

public class ObjectErrorBean {

    ErrorDTO[] objErrors;

    public ErrorDTO[] getObjErrors() {
        return objErrors;
    }

    public void setObjErrors(ErrorDTO[] objErrors) {
        this.objErrors = objErrors;
    }

    @Override
    public String toString() {
        return "ObjectErrorBean{" +
                "objErrors=" + Arrays.toString(objErrors) +
                '}';
    }
}
