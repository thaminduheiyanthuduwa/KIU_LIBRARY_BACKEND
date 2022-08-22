package com.kiu.library.model.concernModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiu.library.model.databaseModel.DatabaseDataInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AllConcernInfo {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("data")
    private ConcernDataInfo data;


}
