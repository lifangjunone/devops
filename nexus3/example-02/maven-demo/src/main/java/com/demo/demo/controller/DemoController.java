package com.demo.demo.controller;

import com.demo.demo.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class DemoController {
    @GetMapping
    public Result Hello() {
        Map<String, Object> data = new HashMap<>();
        data.put("serviceName", "springboot");
        data.put("serviceVersion", "1.2.0");
        data.put("serviceFeature", "jenkins CI/CD test");
        data.put("serviceEnv", "VM");
        return Result.success(data);
    }
}
