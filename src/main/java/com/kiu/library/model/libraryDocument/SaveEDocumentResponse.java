package com.kiu.library.model.libraryDocument;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveEDocumentResponse {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("date")
    private String data;

    @JsonProperty("id")
    private int id;

    public SaveEDocumentResponse(Integer code, String data, int id) {
        this.code = code;
        this.data = data;
        this.id = id;
    }
}
