package com.mas.util;

import org.json.JSONObject;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class Util {
    /**
     * Generate a well-formed response when request succeeded
     * @param data information requested by user
     * @param statusCode http code of response
     * @return request response
     */
    public static ResponseEntity<Object> generateSuccessResponseAPI( Object data, HttpStatusCode statusCode){
        var jsonResponse = new JSONObject();
        jsonResponse.append("error", false);
        jsonResponse.append("message", null);
        jsonResponse.append("data", data);
        return new ResponseEntity<>(jsonResponse.toMap(), statusCode);
    }

    /**
     * Generated a well-formed response when request failed
     * @param message cause of request failure
     * @param statusCode http code of response
     * @return reponse of request
     */
    public static ResponseEntity<Object> generateErrorResponseAPI( String message, HttpStatusCode statusCode){
        var jsonResponse = new JSONObject();
        jsonResponse.append("error", true);
        jsonResponse.append("message", message);
        jsonResponse.append("data", null);
        return new ResponseEntity<>(jsonResponse.toMap(), statusCode);
    }
}
