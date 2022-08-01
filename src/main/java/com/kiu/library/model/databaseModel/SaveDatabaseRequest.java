package com.kiu.library.model.databaseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDatabaseRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("url")
    private String url;

    @JsonProperty("added_user")
    private Integer addedUser;

    @JsonProperty("status")
    private String status;

    @JsonProperty("is_active")
    private Integer isActive;

}
