package com.ebang.frontend.gateway.service;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class ParseUrlService {
    public String parseUrl(String cmsUrl) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(cmsUrl);
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8));

                StringBuilder result = new StringBuilder();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    result.append(str);
                }
                return result.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "hello world";
        }
    }
}
