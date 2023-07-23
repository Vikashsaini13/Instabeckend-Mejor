package com.example.InstagramBackend.service;

import com.example.InstagramBackend.model.Comment;
import com.example.InstagramBackend.repository.IAdminRepo;
import com.example.InstagramBackend.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    public String addComment(Comment comment) {
        comment.setCommentCreationTimeStamp(LocalDateTime.now());
        commentRepo.save(comment);
        return "Comment added!!!";
    }

    public Comment findComment(Integer commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    public void removeComment(Comment comment) {
        commentRepo.delete(comment);
    }
}
