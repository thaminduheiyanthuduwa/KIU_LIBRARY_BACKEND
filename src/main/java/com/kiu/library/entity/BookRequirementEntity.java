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
@Table(name = "kiu_book_requirement")
public class BookRequirementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "timestamp")
    private double timestamp;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "department")
    private String department;

    @Column(name = "resource")
    private String resource;

    @Column(name = "importance")
    private int importance;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "display_time")
    private String displayTime;

    @Column(name = "comment_disabled")
    private boolean commentDisabled;

    @Column(name = "page_views")
    private int pageViews;

    @Column(name = "added_user")
    private int addedUser;

    @Column(name = "last_updated_time")
    private double lastUpdatedTime;

    @Column(name = "last_updated_user")
    private int lastUpdatedUser;

    @Column(name = "is_active")
    private int isActive;

    @Column(name = "description")
    private String description;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "document_image")
    private String documentImage;

    @Column(name = "head_approval")
    private Integer headApproval;

    @Column(name = "final_approval")
    private Integer finalApproval;

    @Column(name = "reason_if_rejected")
    private String reasonIfRejected;

    public BookRequirementEntity() {}
}
