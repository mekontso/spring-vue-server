package com.mas.util;

import org.json.JSONObject;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class Util {
    /**
     * Generate a well-formed response for all api calls in the app
     * @param error true if there was an error while executing the request
     * @param message information about the request
     * @param data database information
     * @param statusCode http code of response
     * @return response of user request
     */
    public static ResponseEntity<Object> generateResponseAPI(boolean error, String message, Object data, HttpStatusCode statusCode){
        var jsonResponse = new JSONObject();
        jsonResponse.append("error", error);
        jsonResponse.append("message", message);
        jsonResponse.append("data", data);
        return new ResponseEntity<>(jsonResponse.toMap(), statusCode);
    }
}
