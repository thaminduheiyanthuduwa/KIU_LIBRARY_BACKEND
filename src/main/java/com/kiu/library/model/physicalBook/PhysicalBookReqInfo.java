package com.kiu.library.model.physicalBook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiu.library.model.resouceModel.ResourceDataBookReqInfo;

public class PhysicalBookReqInfo {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    private PhysicalDataBookReqInfo data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PhysicalDataBookReqInfo getData() {
        return data;
    }

    public void setData(PhysicalDataBookReqInfo data) {
        this.data = data;
    }
}
