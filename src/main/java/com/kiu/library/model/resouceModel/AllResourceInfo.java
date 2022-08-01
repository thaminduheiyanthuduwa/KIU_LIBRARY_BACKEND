package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllResourceInfo {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    private ResourceDataInfo data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResourceDataInfo getData() {
        return data;
    }

    public void setData(ResourceDataInfo data) {
        this.data = data;
    }
}
