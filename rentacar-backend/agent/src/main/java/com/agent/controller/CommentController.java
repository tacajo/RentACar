package com.agent.controller;

import com.agent.dto.CommentDTO;
import com.agent.model.Ad;
import com.agent.model.Comment;
import com.agent.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "https://localhost:4201")
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public void addComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment().builder()
                .description(commentDTO.getDescription())
                .accepted(true)
                .ad(new Ad().builder().id(commentDTO.getAdID()).build())
                .date(new Date())
                .build();
        commentService.addComment(comment);
    }
}
