package com.example.InstagramBackend.service;

import com.example.InstagramBackend.repository.IAdminRepo;
import com.example.InstagramBackend.repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    ILikeRepo likeRepo;
}
