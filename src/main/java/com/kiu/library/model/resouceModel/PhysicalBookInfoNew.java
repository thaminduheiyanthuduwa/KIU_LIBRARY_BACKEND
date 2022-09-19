package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class PhysicalBookInfoNew {

    @JsonProperty("id")
    private int id;

    @JsonProperty("student_id")
    private String studentId;

    @JsonProperty("book_id")
    private String bookId;

    @JsonProperty("return_date")
    private String returnDate;

    @JsonProperty("status")
    private int status;

    @JsonProperty("fine")
    private double fine;

    @JsonProperty("date_time")
    private String dateTime;

}
