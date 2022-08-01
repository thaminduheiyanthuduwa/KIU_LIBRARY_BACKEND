package com.kiu.library.model.libraryDocument;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDocumentRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("document")
    private String document;

    @JsonProperty("added_user")
    private Integer addedUser;

    @JsonProperty("status")
    private String status;

    @JsonProperty("is_active")
    private Integer isActive;

}
