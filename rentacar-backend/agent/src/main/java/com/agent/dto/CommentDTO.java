package com.agent.dto;

import java.util.Date;

public class CommentDTO {
    private Long id;

    private Long adID;

    private String description;

    private Date date;

    private String username;

    public CommentDTO() {
    }

    public CommentDTO(Long adID, String description) {
        this.adID = adID;
        this.description = description;
    }

    public Long getAdID() {
        return adID;
    }

    public void setAdID(Long adID) {
        this.adID = adID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
