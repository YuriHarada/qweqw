package com.yellowbelt.yellow.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import kong.unirest.Empty;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OneDev {


    public String OnedevAll(String Projeto, String offset, String count){
        HttpResponse<String> response = Unirest.get("http://10.128.222.201:6610/~api/issues?query=%22Project%22+is+%22" + Projeto +"%22&offset=" + offset + "&count=" + count)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .basicAuth("40416632", "Ni76615@#1312")
                .asString();
        return response.getBody();
    }
    public String UserInfo(String UserId){
        HttpResponse<String> response = Unirest.get("http://10.128.222.201:6610/~api/users/" + UserId)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .basicAuth("40416632", "Ni76615@#1312")
                .asString();

        return JsonParser.parseString(response.getBody()).getAsJsonObject().get("fullName").getAsString();
    }

    public List<String> ProjectList(){
        HttpResponse<String> response = Unirest.get("http://10.128.222.201:6610/~api/projects?offset=0&count=100")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .basicAuth("40416632", "Ni76615@#1312")
                .asString();

        List<String> list = new LinkedList<>();
        JsonArray jsonArrayProject = JsonParser.parseString(response.getBody()).getAsJsonArray();
        for(JsonElement element: jsonArrayProject) {
            String name_project  = element.getAsJsonObject().get("name").getAsString();
            list.add(name_project);
        }
        return list;
    }

}
