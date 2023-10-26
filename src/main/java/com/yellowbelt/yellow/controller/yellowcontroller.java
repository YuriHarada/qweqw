package com.yellowbelt.yellow.controller;

import com.google.gson.*;
import com.yellowbelt.yellow.service.JsonService;
import com.yellowbelt.yellow.service.OneDev;
import kong.unirest.Empty;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class yellowcontroller {

    @Autowired
    private JsonService jsonService;

    @Autowired
    private OneDev oneDev;

    @GetMapping("/onedev/stats")
    public String getStats(String projeto, String count, String offset){
        return jsonService.JsonOnedev(projeto, count, offset).toString();
    }

    @GetMapping("/onedev/all")
    public HttpResponse<JsonNode> getStatsAll(){
        return null;
    }

    @GetMapping("/projectName")
    public List<String> getProjectAll(){
        return oneDev.ProjectList();
    }
}
