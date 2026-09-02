package com.example.backend.comment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.appointment.dto.AppointmentCreateDto;
import com.example.backend.appointment.entity.Appointment;
import com.example.backend.comment.dto.CommentCreateDto;
import com.example.backend.comment.entity.Comment;
import com.example.backend.comment.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public void createComment(CommentCreateDto commentCreateDto) {
        Comment comment = toEntity(commentCreateDto);
        commentRepository.save(comment);
    }

    public Optional<Comment> update(Long id, Comment comment) {
        return commentRepository.findById(id)
            .map(existingComment -> {
                comment.setId(id);
                return commentRepository.save(comment);
            });
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    public void deleteAll() {
        commentRepository.deleteAll();
    }

    public List<Comment> findByAppointmentId(Long appointmentId) {
        return commentRepository.findByAppointmentId(appointmentId);
    }

    public List<Comment> findByAuthorId(Long authorId) {
        return commentRepository.findByAuthorId(authorId);
    }

    private Comment toEntity(CommentCreateDto commentCreateDto) {
        Comment comment = new Comment();
        comment.setContent(commentCreateDto.getContent());
        comment.setAppointment(commentCreateDto.getAppointmentId());
        comment.setAuthor(commentCreateDto.getAuthorId());
        return comment;
    }
}
