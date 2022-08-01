package com.kiu.library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "kiu_edatabase")
public class DatabaseEntity {

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

    @Column(name = "url")
    private String url;

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

    public DatabaseEntity() {}
}
