package com.adService.model.soap;

import com.adService.model.Ad;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@XmlType(name = "Comment", propOrder = {
        "id",
        "date",
        "username",
        "description",
        "ad",
        "accepted"
}, namespace = "http://adService.com/comment")
public class Comment {

    private Long id;

    private Date date;

    private String username;

    private String description;

    private Long ad;

    private boolean accepted;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public Long getAd() {
        return ad;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAd(Long ad) {
        this.ad = ad;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
