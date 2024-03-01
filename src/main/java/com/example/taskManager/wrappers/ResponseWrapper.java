package com.example.taskManager.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseWrapper {

    public ResponseWrapper() {
        this.body = new ArrayList<>();
        this.status = "";
    }
    public ResponseWrapper(boolean  status, String message, Object body) {
        this.status = status  ? "success" : "failed";
        this.message = message;
        this.body = body;
    }


    private String status;
    private String message;
    private Object body;
}
