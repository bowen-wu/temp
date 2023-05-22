package com.ebang.frontend.salt.controller;

import com.ebang.frontend.salt.service.ProxyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@RestController
@RequestMapping("")
public class SaltController {
    private final ProxyService proxyService;

    @Inject
    public SaltController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping("/*")
    @ResponseBody
    public ModelAndView proxy() {
        return proxyService.proxy();
    }
}
