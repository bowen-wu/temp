package com.ebang.frontend.salt.service;

import com.ebang.frontend.salt.entity.CMSRes;
import com.ebang.frontend.salt.entity.Chunk;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProxyService {
    private final Logger logger = LoggerFactory.getLogger(ProxyService.class);
    private final ParseUrlService parseUrlService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${cms.url}")
    private String cmsUrl;
    @Value("${cms.chunkApi}")
    private String cmsChunkApi;
    @Value("${cms.templateUrl}")
    private String cmsTemplateUrl;

    @Inject
    public ProxyService(ParseUrlService parseUrlService) {
        this.parseUrlService = parseUrlService;
    }

    public String getTemplateUrlFromCMS() {
        if (cmsTemplateUrl != null) {
            logger.info("Read template url of config!");
            return cmsTemplateUrl;
        }
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            logger.info("The request CMS url is: " + cmsUrl + cmsChunkApi);
            HttpGet httpGet = new HttpGet(cmsUrl + cmsChunkApi);
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                CMSRes<Chunk> chunkCMSRes = objectMapper.readValue(EntityUtils.toString(response.getEntity()), new TypeReference<CMSRes<Chunk>>() {
                });
                return chunkCMSRes.getData().getFilePath();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ModelAndView proxy() {
        logger.info("Start accessing the service!");
        String templateUrl = getTemplateUrlFromCMS();
        logger.info("The templateUrl is: " + templateUrl);
        Map<String, Object> variableObj = new HashMap<>();
        return parseUrlService.parseUrl(templateUrl, variableObj);
    }
}
