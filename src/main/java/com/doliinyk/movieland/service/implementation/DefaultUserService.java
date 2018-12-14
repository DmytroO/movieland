package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.dao.UserDao;
import com.doliinyk.movieland.entity.User;
import com.doliinyk.movieland.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    UserDao userDao;

    public DefaultUserService(UserDao userDao) { this.userDao = userDao; }

    @Override
    public User getUserByNamePassword(String email, String shuffled_password) {
        return userDao.getUserByNamePassword(email, shuffled_password);
    }
}
