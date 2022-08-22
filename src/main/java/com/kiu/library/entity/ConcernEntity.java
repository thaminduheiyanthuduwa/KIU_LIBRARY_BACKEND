package com.kiu.library.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "concern")
public class ConcernEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "concern")
    private String concern;

    @Column(name = "concern_id")
    private String concernId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "file")
    private String file;

    @Column(name = "head_approval")
    private String headApproval;

    @Column(name = "date")
    private Date date;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "final_doc")
    private String finalDoc;

    @Column(name = "is_public")
    private Integer isPublic;

    public ConcernEntity() {}
}
