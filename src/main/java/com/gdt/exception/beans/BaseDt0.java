package com.gdt.exception.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.gdt.baseClient.clients.RestLink;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseDt0 implements Serializable {

    private static final long serialVersionUID = -8285460244306774611L;

    @Getter
    @Setter
    protected List<RestLink> links;

    public BaseDt0 addLink(RestLink link) {
        if (this.links == null) {
            this.links = new ArrayList<RestLink>();
        }
        this.links.add(link);
        return this;
    }

}