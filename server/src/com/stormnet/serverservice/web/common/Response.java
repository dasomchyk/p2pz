package com.stormnet.serverservice.web.common;

import com.stormnet.serverservice.web.socket.ResponseCode;
import org.json.JSONWriter;

import java.util.List;
import java.util.Map;

public interface Response {

    void setResponseCode(ResponseCode code);

    int getStatusCode();

    String getStatusMessage();

    JSONWriter getJsonWriter();

    void setJsonWriter(JSONWriter writer);

    void addResponseData(Map<String, Object> data);

    List<Map<String, Object>> getResponseDataValue();
}

