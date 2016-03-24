package com.nataniel.api;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nataniel.neto on 21/03/2016.
 */
public class MailBoxLayerIntegration {
    private String email;
    private String validationResponse = "";

    public MailBoxLayerIntegration(String email) {
        this.email = email;
    }

    public String validate() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet("https://apilayer.net/api/check?access_key=80c63af7d2e4d00081c8aee30bcb5c65&email=" +
                    email);
            getRequest.addHeader("accept", "application/json");
            CloseableHttpResponse response = httpClient.execute(getRequest);
            try {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }
                BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));
                String output;
                while ((output = br.readLine()) != null) {
                    validationResponse += output;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return validationResponse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
