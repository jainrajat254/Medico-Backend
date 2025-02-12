package com.example.Medico.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "records")
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    @JsonIgnore
    private Users users;

    @Column(name = "record_name",nullable = false,updatable = false)
    private String recordName;

    @Column(name = "reviewed_by",nullable = false,updatable = false)
    private String reviewedBy;

    @Column(name = "attention_level",nullable = false,updatable = false)
    private String attentionLevel;

    public Records() {

    }

    public Records(String id, Users users, String recordName, String reviewedBy, String attentionLevel) {
        this.id = id;
        this.users = users;
        this.recordName = recordName;
        this.reviewedBy = reviewedBy;
        this.attentionLevel = attentionLevel;
    }

    public Records(Users users, String recordName, String reviewedBy, String attentionLevel) {
        this.users = users;
        this.recordName = recordName;
        this.reviewedBy = reviewedBy;
        this.attentionLevel = attentionLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getAttentionLevel() {
        return attentionLevel;
    }

    public void setAttentionLevel(String attentionLevel) {
        this.attentionLevel = attentionLevel;
    }
}
