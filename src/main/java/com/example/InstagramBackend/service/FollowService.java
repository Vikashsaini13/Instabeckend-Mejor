package com.example.InstagramBackend.service;

import com.example.InstagramBackend.repository.IAdminRepo;
import com.example.InstagramBackend.repository.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    IFollowRepo followRepo;
}
