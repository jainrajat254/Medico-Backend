package com.example.Medico.responses;

import java.util.UUID;

public class RecordsResponse {
    private UUID id;
    private String recordName;
    private String reviewedBy;
    private String attentionLevel;

    public RecordsResponse() {

    }

    public RecordsResponse(UUID id, String recordName, String reviewedBy, String attentionLevel) {
        this.id = id;
        this.recordName = recordName;
        this.reviewedBy = reviewedBy;
        this.attentionLevel = attentionLevel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
