package com.automation.ui.base.common.api.clientimpl.http;

import com.automation.ui.base.common.api.factory.HttpClientFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestGet {
    public static void main(String[] args) throws ClientProtocolException, Exception {


        HttpClient client = HttpClientFactory.getHttpsClient();
        // client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://lifecharger.org/3-tips-for-a-better-life/");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
}