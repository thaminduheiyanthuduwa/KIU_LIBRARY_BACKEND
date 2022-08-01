package com.kiu.library.model.databaseModel;

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
public class DatabaseInfo implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("timestamp")
    private Double timestamp;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("description")
    private String description;

    @JsonProperty("cover_photo")
    private String cover_photo;

    @JsonProperty("added_user")
    private Integer added_user;

    @JsonProperty("last_updated_time")
    private Double lastUpdatedTime;

    @JsonProperty("last_updated_user")
    private Integer lastUpdatedUser;

    @JsonProperty("display_time")
    private String displayTime;

    @JsonProperty("status")
    private String status;

    @JsonProperty("is_active")
    private Integer isActive;

}
