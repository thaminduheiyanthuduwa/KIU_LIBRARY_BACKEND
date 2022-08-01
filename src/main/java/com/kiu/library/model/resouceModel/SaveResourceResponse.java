package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveResourceResponse {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("date")
    private String data;

    public SaveResourceResponse(Integer code, String data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
