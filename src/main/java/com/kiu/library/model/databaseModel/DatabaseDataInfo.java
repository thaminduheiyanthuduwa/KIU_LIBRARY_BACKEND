package com.kiu.library.model.databaseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiu.library.model.resouceModel.ResourceInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DatabaseDataInfo {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("items")
    private List<DatabaseInfo> items;

}
