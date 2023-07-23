package com.example.InstagramBackend.repository;

import com.example.InstagramBackend.model.Admin;
import com.example.InstagramBackend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Integer> {

}
