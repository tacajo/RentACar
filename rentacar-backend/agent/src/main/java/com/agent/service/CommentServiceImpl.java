package com.agent.service;
import com.agent.model.Ad;
import com.agent.model.Comment;
import com.agent.model.User;
import com.agent.repository.CommentRepository;
import com.agent.soap.CommentClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdService adService;

    @Autowired
    private CommentClient commentClient;

    public void addComment(Comment comment) {
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Ad ad = adService.getAd(comment.getAd().getId());
        comment.setAd(ad);
        comment.setUsername(loginUser.getUsername());

        com.agent.wsdl.Comment comment1 = new com.agent.wsdl.Comment();
        comment1.setId(comment.getId());
        comment1.setUsername(comment.getUsername());
        comment1.setDescription(comment.getDescription());
        comment1.setAccepted(comment.isAccepted());
        comment1.setAd(comment.getAd().getId());

        Long prosao = commentClient.addComment(comment1);

        if(prosao.equals(1L)){
            commentRepository.save(comment);
        } else {
            System.out.println("komentar nije sacuvan u glavnom beku");
        }

    }
}
