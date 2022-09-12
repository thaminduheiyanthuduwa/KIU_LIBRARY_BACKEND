package com.kiu.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "physical_book")
public class PhysicalBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "status")
    private int status;

    @Column(name = "fine")
    private double fine;

    @Column(name = "date_time")
    private String dateTime;

    public PhysicalBookEntity() {}


}
