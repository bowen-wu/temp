package com.ebang.frontend.gateway.service;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class GatewayService {
    private final ParseUrlService parseUrlService;
    private final String baseUrl = "https://global-static.ebonex.io/front/stock-otc-test";
    private final String indexUrl = baseUrl + "/index_a6144aba-d0ba-4412-94b0-6379994381f9.html";

    @Inject
    public GatewayService(ParseUrlService parseUrlService) {
        this.parseUrlService = parseUrlService;
    }

    public String gateway(String path) {
        String cmsUrl = "/".equals(path) ? indexUrl : (baseUrl + path);
        return parseUrlService.parseUrl(cmsUrl);

    }
}
