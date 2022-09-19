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
@Table(name = "students_login_details")
public class StudentDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id", unique = true)
    private int id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "email")
    private String email;

    public StudentDetailEntity() {}


}
