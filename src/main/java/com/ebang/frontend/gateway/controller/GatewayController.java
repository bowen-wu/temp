package com.ebang.frontend.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class GatewayController {
    @GetMapping("/hello")
    @ResponseBody
    public String register() {
        return "Hello world";
    }
}
