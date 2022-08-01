package com.kiu.library.model.libraryDocument;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiu.library.model.databaseModel.DatabaseDataInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AllDocumentInfo {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    private DocumentDataInfo data;


}
