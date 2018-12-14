package com.doliinyk.movieland.service;

import com.doliinyk.movieland.dao.common.SecurityToken;
import com.doliinyk.movieland.entity.User;

import java.util.UUID;

public interface SecurityTokenService {
    boolean isSecurityTokenValid(UUID uuid);
    SecurityToken getSecurityToken(String json);
    void deleteSecurityToken(UUID uuid);
    void cleanSecurityToken();
}
