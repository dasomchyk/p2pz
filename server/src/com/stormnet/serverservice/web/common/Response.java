package com.stormnet.serverservice.web.common;

import com.stormnet.serverservice.web.socket.ResponseCode;
import org.json.JSONWriter;

public interface Response {

    void setResponseCode(ResponseCode code);

    int getStatusCode();

    String getStatusMessage();

    JSONWriter getJsonWriter();

    void setJsonWriter(JSONWriter writer);
}

