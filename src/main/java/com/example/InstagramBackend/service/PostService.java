package com.example.InstagramBackend.service;

import com.example.InstagramBackend.model.Post;
import com.example.InstagramBackend.model.User;
import com.example.InstagramBackend.repository.IAdminRepo;
import com.example.InstagramBackend.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    public String createInstaPost(Post post) {
        post.setPostCreatedTimeStamp(LocalDateTime.now());
        postRepo.save(post);
        return "post uploaded!!!!";
    }

    public String removeInstaPost(Integer postId, User user) {
        Post post=postRepo.findById(postId).orElse(null);

        if(post!=null && post.getPostOwner().equals(user)) {

            postRepo.deleteById(postId);
            return "Removed successfully!!!";
        }
        else if(post==null){
            return "post to be delete does not exist!!!";
        }

        else{
            return "Un-Authorized deletion....Not Allowed!!!";
        }

    }

    public boolean validatePost(Post instaPost) {
        return (instaPost!=null && postRepo.existsById(instaPost.getPostId()));
    }
}
