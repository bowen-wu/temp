package com.ebang.frontend.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Service
public class GatewayService {
    private final ParseUrlService parseUrlService;
    private final String baseUrl = "https://global-static.ebonex.io/front/stock-otc-test";

    @Inject
    public GatewayService(ParseUrlService parseUrlService) {
        this.parseUrlService = parseUrlService;
    }

    public ModelAndView gateway(String path) {
        String indexUrl = "/".equals(path) ? "https://global-static.ebonex.io/front/stock-otc-test/index_c081fa0b-4229-4684-be01-ef85b91c9d31.html" : "https://global-static.ebonex.io/front/stock-otc-test/index_bfcac096-1dca-4f5e-ac81-954d3881d4cc.ftlh";

        Map<String, Object> variableObj = new HashMap<>();
        variableObj.put("cssPath", "https://global-static.ebonex.io/front/stock-otc-test/index_8f873daa-433d-4013-9b72-f72a5c39a55f.css");
        variableObj.put("jsPath", "https://global-static.ebonex.io/front/stock-otc-test/index_93b8292d-5a27-404d-9d35-2ddd05637d63.js");

        return parseUrlService.parseUrl(indexUrl, variableObj);
    }
}
