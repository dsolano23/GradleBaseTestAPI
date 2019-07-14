package com.gdt.baseClient.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@ToString
public class RestLink implements Serializable {

    private static final long serialVersionUID = -8259060045992517490L;

    /**
     * If this link represent a entity
     */
    private Object id;
    /**
     * Type of relation link
     */
    private String rel;
    /**
     * URI to the related resource
     */
    private String link;
    /**
     *
     */
    private String inputParams;
    /**
     * Verbs
     */
    private List<String> options;
    /**
     * Non args constructor, needed for serialization json libraries
     */
    public RestLink(){
        super();
    }

    public RestLink(Object id, String link) {
        this.id = id;
        this.link = link;
    }
    public RestLink(Object id, String link, String rel, List<String> options) {
        this.id = id;
        this.link = link;
        this.rel=rel;
        this.options=options;

    }

    public Object getId() {
        return id;
    }
    public void setId(Object id) {
        this.id = id;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getInputParams() {
        return inputParams;
    }

    public void setInputParams(String inputParams) {
        this.inputParams = inputParams;
    }

    public String getRel() {
        return rel;
    }
    public void setRel(String rel) {
        this.rel = rel;
    }
    public List<String> getOptions() {
        return options;
    }
    public void setOptions(List<String> options) {
        this.options = options;
    }

}

