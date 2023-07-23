package com.example.InstagramBackend.repository;

import com.example.InstagramBackend.model.AuthenticationToken;
import com.example.InstagramBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
