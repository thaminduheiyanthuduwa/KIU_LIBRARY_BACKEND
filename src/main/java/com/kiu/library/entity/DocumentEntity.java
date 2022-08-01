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
@Table(name = "kiu_edocument")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "timestamp")
    private Double timestamp;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "document")
    private String document;

    @Column(name = "cover_photo")
    private String coverPhoto;

    @Column(name = "added_user")
    private Integer addedUser;

    @Column(name = "last_updated_time")
    private Double lastUpdatedTime;

    @Column(name = "last_updated_user")
    private Integer lastUpdatedUser;

    @Column(name = "display_time")
    private String displayTime;

    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private Integer isActive;

    public DocumentEntity() {}
}
