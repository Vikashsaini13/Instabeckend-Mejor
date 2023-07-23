package com.example.InstagramBackend.repository;

import com.example.InstagramBackend.model.Admin;
import com.example.InstagramBackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post,Integer> {

}
