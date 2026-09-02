package com.example.backend.comment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.appointment.dto.AppointmentCreateDto;
import com.example.backend.appointment.entity.Appointment;
import com.example.backend.comment.dto.CommentCreateDto;
import com.example.backend.comment.entity.Comment;
import com.example.backend.comment.service.CommentService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment")
    public ResponseEntity<List<Comment>> getAllComment() {
        List<Comment> comments = commentService.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Long id) {
        return commentService.findById(id)
            .map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/comment")
    public ResponseEntity<Void> createComment(@RequestBody CommentCreateDto commentCreateDto) {
        try {
            commentService.createComment(commentCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/comment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        try {
            return commentService.update(id, comment)
                .map(updatedComment -> new ResponseEntity<>(updatedComment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/comment/appointment/{appointmentId}")
    public ResponseEntity<List<Comment>> getCommentsByAppointmentId(@PathVariable Long appointmentId) {
        List<Comment> comments = commentService.findByAppointmentId(appointmentId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    
    @GetMapping("/comment/author/{authorId}")
    public ResponseEntity<List<Comment>> getCommentsByAuthorId(@PathVariable Long authorId) {
        List<Comment> comments = commentService.findByAuthorId(authorId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") Long id) {
        try {
            commentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/comment")
    public ResponseEntity<HttpStatus> deleteAllComments() {
        try {
            commentService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
