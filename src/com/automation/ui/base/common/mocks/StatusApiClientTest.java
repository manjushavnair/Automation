package com.automation.ui.base.common.mocks;

import com.automation.ui.base.common.api.clientimpl.apacheimpl.StatusApiClient;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class StatusApiClientTest extends Mockito {

    @Test

    public void should_return_true_if_the_status_api_works_properly() throws ClientProtocolException, IOException {
        //given:
        HttpClient httpClient = mock(HttpClient.class);
        HttpGet httpGet = mock(HttpGet.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);


        //and:
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);

        //and:
        StatusApiClient client = new StatusApiClient(httpClient, httpGet);

        //when:
        boolean status = client.getStatus();

        //then:
        Assert.assertTrue(status);
    }

    @Test
    public void should_return_false_if_status_api_do_not_respond() throws ClientProtocolException, IOException {
        //given:
        HttpClient httpClient = mock(HttpClient.class);
        HttpGet httpGet = mock(HttpGet.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        StatusLine statusLine = mock(StatusLine.class);


        when(httpClient.execute(httpGet)).thenThrow(HttpHostConnectException.class);


        StatusApiClient client = new StatusApiClient(httpClient, httpGet);


        boolean status = client.getStatus();

        Assert.assertFalse(status);
    }

}