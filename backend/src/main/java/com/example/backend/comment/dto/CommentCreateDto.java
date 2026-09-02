package com.example.backend.comment.dto;

import com.example.backend.appointment.entity.Appointment;
import com.example.backend.user.entity.User;

public class CommentCreateDto {
    private String content;
    private Appointment appointmentId;
    private User authorId;

    public CommentCreateDto() {}

    public CommentCreateDto(String content, Appointment appointmentId, User authorId) {
        this.content = content;
        this.appointmentId = appointmentId;
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Appointment getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointment appointmentId) {
        this.appointmentId = appointmentId;
    }

    public User getAuthorId() {
        return authorId;
    }

    public void setAuthorId(User authorId) {
        this.authorId = authorId;
    }
}
