package com.example.InstagramBackend.repository;

import com.example.InstagramBackend.model.Admin;
import com.example.InstagramBackend.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowRepo extends JpaRepository<Follow,Integer> {

}
