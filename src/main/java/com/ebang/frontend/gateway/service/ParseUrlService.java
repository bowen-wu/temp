package com.ebang.frontend.gateway.service;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ParseUrlService {
    private final Logger logger = LoggerFactory.getLogger(ParseUrlService.class);
    private final String TEMPLATE_FILE_PATH = "src/main/resources/templates/index.ftlh";

    public ModelAndView parseUrl(String cmsUrl, Map<String, Object> variableObj) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(cmsUrl);
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                logger.info("Get template file content!");
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                List<String> list = new ArrayList<>();
                while (true) {
                    String string = bufferedReader.readLine();
                    if (string == null) {
                        break;
                    }
                    list.add(string);
                }
                bufferedReader.close();
                write(list);
                logger.info("Finished writing the template file(index.ftlh)!");
                return new ModelAndView("index", variableObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(List<String> lines) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("templates/index.ftlh");
        File templateFile = new File(url == null ? TEMPLATE_FILE_PATH : url.getFile());

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(templateFile));

            for (String string : lines) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
