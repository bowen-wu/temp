package com.ebang.frontend.gateway.controller;

import com.ebang.frontend.gateway.service.GatewayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class GatewayController {
    private final GatewayService gatewayService;

    @Inject
    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping("/*")
    @ResponseBody
    // 当接收到请求后，将 index.html 返回即可
    public ModelAndView gateway(HttpServletRequest request) {
        String path = request.getServletPath();
        return gatewayService.gateway(path);
    }
}
