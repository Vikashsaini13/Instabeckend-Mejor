package com.example.InstagramBackend.service;

import com.example.InstagramBackend.model.AuthenticationToken;
import com.example.InstagramBackend.model.Comment;
import com.example.InstagramBackend.model.Post;
import com.example.InstagramBackend.model.User;
import com.example.InstagramBackend.model.dto.SignInInput;
import com.example.InstagramBackend.model.dto.SignUpOutput;
import com.example.InstagramBackend.repository.IUserRepo;
import com.example.InstagramBackend.service.emailUtility.EmailHandler;
import com.example.InstagramBackend.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;
    @Autowired
    AuthenticationService authenticationService;

    public SignUpOutput signUpUser(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

            //saveAppointment the user with the new encrypted password

            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }


    public String signInUser(SignInInput signInInput) {


        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if(existingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail("vs2215727@gmail.com","email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }


    public String sigOutUser(String email) {

        User user = userRepo.findFirstByUserEmail(email);
        AuthenticationToken token = authenticationService.findFirstByUser(user);
        authenticationService.removeToken(token);
        return "User Signed out successfully";
    }


    public String createInstaPost(Post post ,String email) {
        User postOwner=userRepo.findFirstByUserEmail(email);
        post.setPostOwner(postOwner);
        return postService.createInstaPost(post);
    }

    public String removeInstaPost(Integer postId,String email) {
        User user=userRepo.findFirstByUserEmail(email);
        return postService.removeInstaPost(postId,user);
    }

    public String addComment(Comment comment,String commenterEmail) {
        User commenter=userRepo.findFirstByUserEmail(commenterEmail);
        comment.setCommenter(commenter);

        boolean postValid= postService.validatePost(comment.getInstaPost());
        if(postValid){
            return commentService.addComment(comment);
        }
        else{
            return "can not comment on invalid post!!";
        }

    }

    boolean authorizeCommentRemover(String email,Comment comment){
        String commentOwnerEmail=comment.getCommenter().getUserEmail();
        String postOwnerEmail=comment.getInstaPost().getPostOwner().getUserEmail();

        return commentOwnerEmail.equals(email) || postOwnerEmail.equals(email);
    }
    public String removeInstaComment(Integer commentId, String email) {
       Comment comment= commentService.findComment(commentId);

       if(comment!=null){
           if(authorizeCommentRemover(email,comment)){
               commentService.removeComment(comment);
               return "Comment deleted successfully!!";
           }
           else{
               return "unauthorized delete detected...not allowed!!!";
           }
       }
       else{
           return "invalid comment!!";
       }
    }
}
