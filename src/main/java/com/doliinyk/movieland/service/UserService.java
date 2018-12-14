package com.doliinyk.movieland.service;

import com.doliinyk.movieland.entity.User;

public interface UserService {
    User getUserByNamePassword(String name, String shuffled_password);
}
