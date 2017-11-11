package com.jgos.hotelbooker.service.data;

import javax.persistence.Entity;

/**
 * Created by Bos on 2017-11-10.
 */
public class EmailToSend {

    String to;
    String subject;
    String content;

    public EmailToSend(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public EmailToSend() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EmailToSend{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
