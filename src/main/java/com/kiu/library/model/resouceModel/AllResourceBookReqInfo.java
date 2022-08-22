package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllResourceBookReqInfo {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    private ResourceDataBookReqInfo data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResourceDataBookReqInfo getData() {
        return data;
    }

    public void setData(ResourceDataBookReqInfo data) {
        this.data = data;
    }
}
