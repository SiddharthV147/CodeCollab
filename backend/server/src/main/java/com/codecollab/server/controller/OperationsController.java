package com.codecollab.server.controller;

import com.otengine.OTEngine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OperationsController {
    OTEngine otEngine = new OTEngine();
    @GetMapping("/health")
    public String checkHealth() {
        return otEngine.checkHealth();
    }
}
