package com.kiu.library.model.libraryDocument;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiu.library.model.databaseModel.DatabaseInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DocumentDataInfo {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("items")
    private List<DocumentInfo> items;

}
