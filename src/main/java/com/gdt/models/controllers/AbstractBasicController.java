package com.gdt.models.controllers;

import com.gdt.baseClient.beans.ResponseModelDto;
import com.gdt.baseClient.clients.ApacheHttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public abstract class AbstractBasicController extends ApacheHttpClient {

    protected ResponseModelDto resp;
    protected Gson gson;

    public AbstractBasicController(int maxRetry, int timeToLive) {
        super(maxRetry, timeToLive);

        if(gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        }
    }
}
