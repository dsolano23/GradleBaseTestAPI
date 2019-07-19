package com.gdt.unitTest.httpClientTest;

import com.gdt.baseClient.beans.RequestModelDtoOld;
import com.gdt.baseClient.beans.ResponseModelDto;
import com.gdt.baseClient.clients.ApacheHttpClient;
import com.gdt.baseClient.constants.HttpHeadersEnum;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

;

public class BasicFunctionalityTest {

    private static final Integer HTTP_OK =200;

    @Test
    public void testGetHttpClientConnection(){
        ApacheHttpClient client = new ApacheHttpClient(2,10000);
        HashMap<String,Object> headers = new HashMap<String, Object>();
        try {
            RequestModelDtoOld requestModelDTO = new RequestModelDtoOld();
            requestModelDTO.setUrl("http://dummy.restapiexample.com/api/v1/employees");
            requestModelDTO.setHeaders(headers);
            ResponseModelDto response = client.get(requestModelDTO);
            System.out.println("Response body: " + response.getBodyContent());
            System.out.println("Response code: " + response.getRequestHttpCode());
            System.out.println("Response headers: " +response.getHeaders());

            Assert.assertEquals(response.getRequestHttpCode(),HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue("test fail:"+e.getMessage(),false);
        }
    }

    @Test
    public void testPostHttpMultiFormData() {

        ApacheHttpClient client = new ApacheHttpClient(2,10000);
        HashMap<String,Object> headers = new HashMap<String, Object>();
        HashMap<String,String> formData = new HashMap<String, String>();
        HashMap<String,File> files = new HashMap<String, File>();
        File file = new File("src/test/resources/featureFilesTest00.feature");
        files.put("file",file);
        headers.put(HttpHeadersEnum.AUTHORIZATION_BASIC.headerKey,HttpHeadersEnum.AUTHORIZATION_BASIC.headerValue + " " + "bWNoZXJ1YmluaTpMdWRhMjAxNw");
        ResponseModelDto response = new ResponseModelDto();

        try {
            RequestModelDtoOld requestModelDTO = new RequestModelDtoOld();
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

        Assert.assertEquals("http code response is 200",HTTP_OK,response.getRequestHttpCode());
    }
}
