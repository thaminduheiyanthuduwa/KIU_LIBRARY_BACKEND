package com.kiu.library.model.physicalBook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StudentInfo {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    private List<StudentInfoObj> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<StudentInfoObj> getData() {
        return data;
    }

    public void setData(List<StudentInfoObj> data) {
        this.data = data;
    }
}
