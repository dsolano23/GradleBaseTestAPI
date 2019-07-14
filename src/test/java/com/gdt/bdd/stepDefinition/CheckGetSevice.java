package com.gdt.bdd.stepDefinition;

import com.gdt.baseClient.beans.RequestModelDTO;
import com.gdt.baseClient.beans.ResponseModelDTO;
import com.gdt.baseClient.client.BaseHttpClient;
import io.cucumber.java.en.Given;
import org.apache.http.Header;
import org.junit.Assert;

import java.util.HashMap;

public class CheckGetSevice {
    @Given("^i try to do a get$")
    public void testGetHttpClientConnection(){
        BaseHttpClient client = new BaseHttpClient(2,10000);
        HashMap<String,String> headers = new HashMap<String, String>();
        try {
            RequestModelDTO requestModelDTO = new RequestModelDTO();
            requestModelDTO.setUrl("http://dummy.restapiexample.com/api/v1/employees");
            requestModelDTO.setHeaders(headers);
            ResponseModelDTO response = client.get(requestModelDTO);
            System.out.println("Response body: " + response.getBodyContent());
            System.out.println("Response code: " + response.getRequestHttpCode());
            System.out.println("Response headers: ");
            for(Header header: response.getHeaders()) {
                System.out.println("\n" +header.toString());
            }
            Assert.assertEquals(response.getRequestHttpCode(),200);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue("test fail:"+e.getMessage(),false);
        }
    }
}
