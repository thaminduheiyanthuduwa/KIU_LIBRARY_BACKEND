package com.kiu.library.model.databaseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiu.library.model.resouceModel.ResourceDataInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AllDatabaseInfo {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    private DatabaseDataInfo data;


}
