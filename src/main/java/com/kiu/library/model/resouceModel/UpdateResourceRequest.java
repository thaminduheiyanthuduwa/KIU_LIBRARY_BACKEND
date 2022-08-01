package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResourceRequest {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("author")
    private String author;

    @JsonProperty("display_time")
    private String date;

    @JsonProperty("department")
    private String department;

    @JsonProperty("title")
    private String title;

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("importance")
    private Integer importance;

    @JsonProperty("type")
    private String type;

    @JsonProperty("comment_disable")
    private String commentDisable;

    @JsonProperty("pageviews")
    private Integer pageviews;

    @JsonProperty("status")
    private String status;

    @JsonProperty("updated_user")
    private int updatedUser;

    @JsonProperty("description")
    private String description;
}
