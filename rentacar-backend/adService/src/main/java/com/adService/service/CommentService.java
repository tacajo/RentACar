package com.adService.service;

import com.adService.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void addComment(Comment comment);

    void acceptComment(Long id);

    void blockComment(Long id);

    List<Comment> getComments();

    List<Comment> getAdComments(Long id);
}
