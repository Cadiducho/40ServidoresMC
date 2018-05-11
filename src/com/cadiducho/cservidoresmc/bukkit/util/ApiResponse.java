package com.cadiducho.cservidoresmc.bukkit.util;

import java.util.Optional;
import org.json.simple.JSONObject;

/**
 *
 * @author Cadiducho
 */
public class ApiResponse {
    
    private Optional<Exception> exception;
    private JSONObject result;

    public ApiResponse() {
        this.exception = Optional.empty();
    }
    
    public Optional<Exception> getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = Optional.ofNullable(exception);
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject t) {
        this.result = t;
    }    
}
