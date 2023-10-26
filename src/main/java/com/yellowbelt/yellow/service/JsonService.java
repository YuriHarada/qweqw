package com.yellowbelt.yellow.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;

@Service
@EnableAsync
public class JsonService {

    @Autowired
    private OneDev oneDev;

    public List<JsonObject> JsonOnedev(String projeto, String offset, String count){
        List<JsonObject> ListResult = new LinkedList<>();
        String a = oneDev.OnedevAll(projeto, offset, count);
        JsonArray jsonArrayTicket = JsonParser.parseString(a).getAsJsonArray();
        int i = 0;
        for (JsonElement element : jsonArrayTicket) {
            JsonObject Element =  addUser((JsonObject) element);
            Element.remove("voteCount");
            Element.remove("commentCount");
            Element.remove("confidential");
            Element.remove("stateOrdinal");
            Element.remove("threadingReference");
            ListResult.add(Element);
            i++;
        }
        System.out.println(i);
        return ListResult;
    }
    public JsonObject addUser(JsonObject ObjectJson){
            String a = ObjectJson.getAsJsonObject().get("lastActivity").getAsJsonObject().get("userId").getAsString();
            ObjectJson.addProperty("IssueUser", oneDev.UserInfo(a));
            return ObjectJson;

    }
}
