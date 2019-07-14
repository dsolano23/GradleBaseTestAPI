package com.gdt.baseClient.client;

import com.gdt.baseClient.beans.RequestModelDTO;
import com.gdt.baseClient.beans.ResponseModelDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Utility class use http client to make http request with diferents standard methods
 * @author David Solano
 * 2019 / 07 / 14
 * @see ResponseModelDTO
 */
public class BaseHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(BaseHttpClient.class);
    private HttpClient client;
    private static final String LINE_SEP = System.lineSeparator();
    //TODO put parameter configuration in config file?
    /**
     *
     * @param maxRetry max retry attempt if request connection fail
     * @param timeout maximum time to get timeout if request not get response
     */
    public BaseHttpClient(int maxRetry,int timeout){
        RequestConfig.Builder requestBuilder = RequestConfig.custom();
        requestBuilder.setConnectTimeout(timeout);
        requestBuilder.setConnectionRequestTimeout(timeout);

        HttpRequestRetryHandler requestHandler = new DefaultHttpRequestRetryHandler(maxRetry,true);
        HttpClientBuilder builder = HttpClientBuilder.create().setRetryHandler(requestHandler);
        builder.setDefaultRequestConfig(requestBuilder.build());
        client = builder.build();

    }

    /**
     *
     * @param requestModelDTO with:
     *                        - url: the complete endpoint to send request
     *                        - headers: http request to send in the request
     *                        - Body: the information put in the body request
     * @return the response model with request result
     * @throws IOException when error happend at read the response
     */
    //public ResponseModelDTO get(String url, HashMap<String,String> headers) throws IOException {
    public ResponseModelDTO get(RequestModelDTO requestModelDTO) throws IOException {
        HttpGet getRequest = new HttpGet(requestModelDTO.getUrl());

        if(requestModelDTO.getHeaders() != null) {
            for(String key: requestModelDTO.getHeaders().keySet()) {
                getRequest.addHeader(key,requestModelDTO.getHeaders().get(key));
            }
        }
        logger.debug("SEND GET REQUEST: " + getRequest);
        HttpResponse response = client.execute(getRequest);
        logger.debug("RESPONSE OF GET REQUEST: " + response);
        ResponseModelDTO responseModel = setResponseModel(response, ResponseModelDTO.METHOD_GET);
        return responseModel;
    }

    /**
     * @param requestModelDTO with:
     *                        - url: the complete endpoint to send request
     *                        - headers: http request to send in the request
     *                        - Body: the information put in the body request
     * @return the response model with request result
     * @throws IOException when error happend at read the response
     */
    public ResponseModelDTO put(RequestModelDTO requestModelDTO) throws IOException {

        HttpPut putRequest = new HttpPut(requestModelDTO.getUrl());

        if(requestModelDTO.getHeaders() != null) {
            for(String key: requestModelDTO.getHeaders().keySet()) {
                putRequest.addHeader(key,requestModelDTO.getHeaders().get(key));
            }
        }

        if(requestModelDTO.getBody() != null) {
            putRequest.setEntity(new ByteArrayEntity(requestModelDTO.getBody().getBytes(StandardCharsets.UTF_8)));
        }

        logger.debug("SEND PUT REQUEST: " + putRequest + LINE_SEP + "with body:" + LINE_SEP + requestModelDTO.getBody());
        HttpResponse response = client.execute(putRequest);
        logger.debug("RESPONSE OF PUT REQUEST: " + response);
        ResponseModelDTO responseModel = setResponseModel(response, ResponseModelDTO.METHOD_PUT);
        logger.debug("RESPONSE OF PUT REQUEST WITH BODY: " +LINE_SEP + responseModel.getBodyContent());
        return responseModel;
    }

    /**
     *
     * @param requestModelDTO with:
     *                        - url: the complete endpoint to send request
     *                        - headers: http request to send in the request
     *                        - Body: the information put in the body request
     * @return the response model with request result
     * @throws IOException when error happend at read the response
     */
    public ResponseModelDTO post(RequestModelDTO requestModelDTO) throws IOException {

        HttpPost postRequest = new HttpPost(requestModelDTO.getUrl());

        if(requestModelDTO.getHeaders() != null) {
            for(String key: requestModelDTO.getHeaders().keySet()) {
                postRequest.addHeader(key,requestModelDTO.getHeaders().get(key));
            }
        }

        if(requestModelDTO.getBody() != null) {
            postRequest.setEntity(new ByteArrayEntity(requestModelDTO.getBody().getBytes(StandardCharsets.UTF_8)));
        }

        logger.debug("SEND POST REQUEST: " + postRequest + LINE_SEP + "with body:" + LINE_SEP + requestModelDTO.getBody());
        HttpResponse response = client.execute(postRequest);
        ResponseModelDTO responseModel = setResponseModel(response, ResponseModelDTO.METHOD_POST);
        logger.debug("RESPONSE OF POST REQUEST: " + LINE_SEP + responseModel.getBodyContent());
        return responseModel;
    }

    /**
     *
     * @param requestModelDTO with:
     *                        - url: the complete endpoint to send request
     *                        - headers: http request to send in the request
     *                        - multipartTextBody: map contains the multipart/form data to send as text
     *                        - files: map contains the files data to send, keys are a file alias
     * @return the response model with request result
     * @throws IOException when error happend at read the response
     */
    public ResponseModelDTO postWithFiles(RequestModelDTO requestModelDTO) throws IOException {

        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpPost postRequest = new HttpPost(requestModelDTO.getUrl());

        if(requestModelDTO.getMultipartTextBody() != null && requestModelDTO.getMultipartTextBody().size() > 0) {
            for(String key :requestModelDTO.getMultipartTextBody().keySet()){
                builder.addTextBody(key, requestModelDTO.getMultipartTextBody().get(key), ContentType.DEFAULT_BINARY);
            }
        }

        if(requestModelDTO.getFiles() != null && requestModelDTO.getFiles().size() > 0) {
            for(String key : requestModelDTO.getFiles().keySet()) {
                //String a = files.get(key).getName();
                //builder.addBinaryBody(key,files.get(key),ContentType.DEFAULT_BINARY,filePrefix + files.get(key).getName());
                //String filePath = files.get(key).getAbsolutePath();
                builder.addBinaryBody(key,requestModelDTO.getFiles().get(key),ContentType.APPLICATION_OCTET_STREAM,requestModelDTO.getFiles().get(key).getName());
            }
        }

        postRequest.setEntity(builder.build());

        if(requestModelDTO.getHeaders() != null) {
            for(String key: requestModelDTO.getHeaders().keySet()) {
                postRequest.addHeader(key,requestModelDTO.getHeaders().get(key));
            }
        }

        logger.debug("SEND POST REQUEST: " + postRequest + LINE_SEP + "with body:" + LINE_SEP + postRequest.getEntity());
        HttpResponse response = client.execute(postRequest);
        ResponseModelDTO responseModel = setResponseModel(response, ResponseModelDTO.METHOD_POST);
        logger.debug("RESPONSE OF POST REQUEST: " + LINE_SEP + responseModel.getBodyContent());
        return responseModel;
    }

    /**
     *
     * @param requestModelDTO with:
     *                        - url: the complete endpoint to send request
     *                        - headers: http request to send in the request
     * @return the response model with request result
     * @return the response model with request result
     * @throws IOException when error happend at read the response
     */
    public ResponseModelDTO delete(RequestModelDTO requestModelDTO) throws IOException {

        HttpDelete deleteRequest = new HttpDelete(requestModelDTO.getUrl());

        if(requestModelDTO.getHeaders() != null) {
            for(String key: requestModelDTO.getHeaders().keySet()) {
                deleteRequest.addHeader(key,requestModelDTO.getHeaders().get(key));
            }
        }

        logger.debug("SEND DELETE REQUEST: " + deleteRequest);
        HttpResponse response = client.execute(deleteRequest);
        logger.debug("RESPONSE OF DELETE REQUEST: " + response);
        ResponseModelDTO responseModel = setResponseModel(response, ResponseModelDTO.METHOD_DELETE);

        return responseModel;
    }

    private ResponseModelDTO setResponseModel ( HttpResponse response, String methodType ) throws IOException {
        ResponseModelDTO responseModel = new ResponseModelDTO();
        responseModel.setHeaders(response.getAllHeaders());
        responseModel.setRequestHttpCode(response.getStatusLine().getStatusCode());
        responseModel.setBodyContent(getBodyContent(response.getEntity().getContent()));
        responseModel.setMethodType(methodType);
        return responseModel;
    }

    private String getBodyContent(InputStream streamData) {
        StringBuilder builder = new StringBuilder();

        try {
            Reader reader = new BufferedReader(new InputStreamReader(streamData,StandardCharsets.UTF_8));
            int characterCount = 0;
            while((characterCount = reader.read()) != -1){
                builder.append((char) characterCount);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(""+e);
        } catch (IOException e) {
            logger.error(""+e);
        } finally {
            return builder.toString();
        }
    }
}
