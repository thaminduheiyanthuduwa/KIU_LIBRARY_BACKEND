package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveResourceRequest {

    @JsonProperty("author")
    private String author;

    @JsonProperty("date")
    private String date;

    @JsonProperty("department")
    private String department;

    @JsonProperty("title")
    private String title;

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("added_user")
    private int addedUser;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String type;

}
