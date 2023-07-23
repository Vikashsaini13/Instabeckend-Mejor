package com.example.InstagramBackend.controller;

import com.example.InstagramBackend.model.Comment;
import com.example.InstagramBackend.model.Post;
import com.example.InstagramBackend.model.User;
import com.example.InstagramBackend.model.dto.SignInInput;
import com.example.InstagramBackend.model.dto.SignUpOutput;
import com.example.InstagramBackend.service.AuthenticationService;
import com.example.InstagramBackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;


    //sign up, sign in , sign out a particular instagram user
    @PostMapping("user/signup")
    public SignUpOutput signUpInstaUser(@RequestBody User user)
    {

        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String sigInInstaUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String sigOutInstaUser(String email, String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.sigOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }
    }


    //post on insta

    @PostMapping("post")
    public String createInstaPost(@RequestBody Post post, @RequestParam String email, @RequestParam String token){
        if(authenticationService.authenticate(email,token)) {
            return userService.createInstaPost(post,email);
        }
        else {
            return "not a authenticate user activity";
        }
    }

    //remove post
    @DeleteMapping("post")
    public String removeInstaPost(@RequestParam Integer postId, @RequestParam String email, @RequestParam String token){
        if(authenticationService.authenticate(email,token)) {
            return userService.removeInstaPost(postId,email);
        }
        else {
            return "not a authenticate user activity";
        }
    }


    //commenting functionality on a post on insta

    @PostMapping("comment")
    public String addComment(@RequestBody Comment comment, @RequestParam String commenterEmail, @RequestParam String commenterToken){
        if(authenticationService.authenticate(commenterEmail,commenterToken)) {
            return userService.addComment(comment,commenterEmail);
        }
        else {
            return "not a authenticate user activity";
        }

    }
    // delete comment
    @DeleteMapping("comment")
    public String removeInstaComment(@RequestParam Integer commentId, @RequestParam String email, @RequestParam String token){
        if(authenticationService.authenticate(email,token)) {
            return userService.removeInstaComment(commentId,email);
        }
        else {
            return "not a authenticate user activity";
        }
    }

}
