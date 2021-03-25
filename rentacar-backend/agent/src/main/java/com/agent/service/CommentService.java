package com.agent.service;

import com.agent.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void addComment(Comment comment);
}
