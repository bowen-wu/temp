package com.ebang.frontend.gateway.service;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class GatewayService {
    private final ParseUrlService parseUrlService;

    @Inject
    public GatewayService(ParseUrlService parseUrlService) {
        this.parseUrlService = parseUrlService;
    }

    public String gateway() {
        String cmsUrl = "https://global-static.ebonex.io/front/stock-otc-test/stock-template-day-zh_fa905dbf-8b84-4fb5-9a19-bed95f114670.html";
        return parseUrlService.parseUrl(cmsUrl);
    }
}
