package com.kiu.library.model.concernModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ConcernInfo implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("student_id")
    private Integer studentId;

    @JsonProperty("concern")
    private String concern;

    @JsonProperty("concern_id")
    private String concernId;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("file")
    private String file;

    @JsonProperty("head_approval")
    private String headApproval;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("is_active")
    private Integer isActive;

    @JsonProperty("final_doc")
    private String displayTime;

    @JsonProperty("is_public")
    private Integer isPublic;

}
