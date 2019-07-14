package com.gdt.unitTest.httpClientTest;

import com.gdt.baseClient.beans.RequestModelDTO;
import com.gdt.baseClient.beans.ResponseModelDTO;
import com.gdt.baseClient.client.BaseHttpClient;
import com.gdt.baseClient.constants.HttpHeadersEnum;
import org.apache.http.Header;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BasicFunctionalityTest {


    @Test
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

    @Test
    public void testPostHttpMultiFormData() {

        BaseHttpClient client = new BaseHttpClient(2,10000);
        HashMap<String,String> headers = new HashMap<String, String>();
        HashMap<String,String> formData = new HashMap<String, String>();
        HashMap<String,File> files = new HashMap<String, File>();
        File file = new File("src/test/resources/featureFilesTest00.feature");
        files.put("file",file);
        headers.put(HttpHeadersEnum.AUTHORIZATION_BASIC.headerKey,HttpHeadersEnum.AUTHORIZATION_BASIC.headerValue + " " + "bWNoZXJ1YmluaTpMdWRhMjAxNw");
        ResponseModelDTO response = new ResponseModelDTO();

        try {
            RequestModelDTO requestModelDTO = new RequestModelDTO();
            requestModelDTO.setUrl("http://35.205.254.105:8080/rest/raven/1.0/import/feature?projectKey=LudaTestManager");
            requestModelDTO.setHeaders(headers);
            requestModelDTO.setMultipartTextBody(formData);
            requestModelDTO.setFiles(files);
            response = client.postWithFiles(requestModelDTO);
            System.out.println("RESPONSE DATA:" + response.getBodyContent());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue("test fail:"+e.getMessage(),false);
        }

        Assert.assertEquals("http code response is 200",200,response.getRequestHttpCode());
    }
}
