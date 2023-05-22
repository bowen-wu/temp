package com.ebang.frontend.gateway.service;

import com.ebang.frontend.gateway.entity.CMSRes;
import com.ebang.frontend.gateway.entity.Chunk;
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
public class GatewayService {
    private final Logger logger = LoggerFactory.getLogger(GatewayService.class);
    private final ParseUrlService parseUrlService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${cms.url}")
    private String cmsUrl;
    @Value("${cms.chunkApi}")
    private String cmsChunkApi;

    @Inject
    public GatewayService(ParseUrlService parseUrlService) {
        this.parseUrlService = parseUrlService;
    }

    public String getTemplateUrlFromCMS() {
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

    public ModelAndView gateway() {
        logger.info("Start accessing the service!");
        String templateUrl = getTemplateUrlFromCMS();
        logger.info("The templateUrl is: " + templateUrl);
        Map<String, Object> variableObj = new HashMap<>();
        return parseUrlService.parseUrl(templateUrl, variableObj);
    }
}
