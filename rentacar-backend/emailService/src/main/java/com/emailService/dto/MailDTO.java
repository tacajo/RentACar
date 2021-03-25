package com.emailService.dto;

public class MailDTO {

    private String userEmail;

    private String content;

    public MailDTO() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getContent() {
        return content;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
