package com.kiu.library.model.physicalBook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiu.library.model.resouceModel.PhysicalBookInfo;

import java.util.List;

public class StudentInfoObj {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
