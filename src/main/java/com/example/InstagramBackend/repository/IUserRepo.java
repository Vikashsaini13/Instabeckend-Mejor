package com.example.InstagramBackend.repository;

import com.example.InstagramBackend.model.Admin;
import com.example.InstagramBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {

    User findFirstByUserEmail(String newEmail);
}
