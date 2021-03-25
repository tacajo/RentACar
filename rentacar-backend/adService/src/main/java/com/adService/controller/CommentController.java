package com.adService.controller;


import com.adService.dto.CommentDTO;
import com.adService.model.Ad;
import com.adService.model.Comment;
import com.adService.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public void addComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment().builder()
                .description(commentDTO.getDescription())
                .accepted(false)
                .ad(new Ad().builder().id(commentDTO.getAdID()).build())
                .date(new Date())
                .build();
        commentService.addComment(comment);
    }

    @GetMapping(value = "/accept/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void acceptComment(@PathVariable Long id) {
        commentService.acceptComment(id);
    }

    @GetMapping(value = "/block/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void blockComment(@PathVariable Long id) {
        commentService.blockComment(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CommentDTO> getComments() {
        List<Comment> comments = commentService.getComments();
        List<CommentDTO> ret = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setAdID(comment.getAd().getId());
            commentDTO.setDescription(comment.getDescription());
            commentDTO.setDate(comment.getDate());
            commentDTO.setUsername(comment.getUsername());
            ret.add(commentDTO);
        }

        return ret;
    }

    @GetMapping(value = "/{id}")
    public List<CommentDTO> getAdComments(@PathVariable Long id) {
        List<Comment> comments = commentService.getAdComments(id);
        List<CommentDTO> ret = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setAdID(comment.getAd().getId());
            commentDTO.setDescription(comment.getDescription());
            commentDTO.setDate(comment.getDate());
            commentDTO.setUsername(comment.getUsername());
            ret.add(commentDTO);
        }

        return ret;
    }
}
