package com.adService.service;

import com.adService.model.Ad;
import com.adService.model.Comment;
import com.adService.model.User;
import com.adService.repository.CommentRepository;
import com.adService.repository.UserRepository;
import com.adService.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AdService adService;

    public void addComment(Comment comment) {
        CustomUserDetails loginUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Ad ad = adService.getAd(comment.getAd().getId());
        comment.setAd(ad);
        comment.setUsername(loginUser.getUsername());
        commentRepository.save(comment);
    }

    public void acceptComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        comment.setAccepted(true);
        commentRepository.save(comment);
    }

    public void blockComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        Ad ad = adService.getAd(comment.getAd().getId());
        ad.getComments().remove(comment);
        adService.save(ad);
        commentRepository.delete(comment);
    }

    public List<Comment> getComments() {
        List<Comment> comments = commentRepository.findAll();
        List<Comment> ret = new ArrayList<>();
        System.out.println(comments.size());
        if(comments.size() > 0) {
            for (Comment comment : comments) {
                if (!comment.isAccepted())
                    ret.add(comment);
            }
        }
        return ret;
    }

    public List<Comment> getAdComments(Long id) {
        List<Comment> comments = commentRepository.findAll();
        List<Comment> ret = new ArrayList<>();
        System.out.println(comments.size());
        if(comments.size() > 0) {
            for (Comment comment : comments) {
                if (comment.getAd().getId() == id && comment.isAccepted())
                    ret.add(comment);
            }
        }
        return ret;
    }
}
