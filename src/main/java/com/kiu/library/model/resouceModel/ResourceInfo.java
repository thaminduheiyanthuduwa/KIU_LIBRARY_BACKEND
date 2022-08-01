package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ResourceInfo implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("timestamp")
    private Double timestamp;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("department")
    private String department;

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("importance")
    private Integer importance;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("added_date")
    private String displayTime;

    @JsonProperty("comment_disabled")
    private Boolean commentDisabled;

    @JsonProperty("pageviews")
    private Integer pageViews;

    @JsonProperty("platforms")
    private String[] platforms;

    @JsonProperty("resource_title")
    private String resourceTitle;

    @JsonProperty("description")
    private String description;

    @JsonProperty("document_image")
    private String documentImage;



}
