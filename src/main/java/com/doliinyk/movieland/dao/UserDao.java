package com.doliinyk.movieland.dao;

import com.doliinyk.movieland.entity.User;

public interface UserDao {
    User getUserByNamePassword(String email, String password);
}
