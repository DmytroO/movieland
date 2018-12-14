package com.doliinyk.movieland.service.implementation;


import com.doliinyk.movieland.dao.common.SecurityToken;
import com.doliinyk.movieland.entity.User;
import com.doliinyk.movieland.service.SecurityTokenService;
import com.doliinyk.movieland.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultSecurityTokenService implements SecurityTokenService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static Map<UUID, SecurityToken> tokens = new ConcurrentHashMap<>();
    private static long EXPIRATION_PERIOD_SECONDS;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private UserService userService;

    public DefaultSecurityTokenService(
            UserService userService,
            @Value("${securityToken.expirationPeriodSeconds}") long expirationPeriodSeconds
    ) {
        this.userService = userService;
        EXPIRATION_PERIOD_SECONDS = expirationPeriodSeconds;
    }

    @Override
    public boolean isSecurityTokenValid(UUID uuid) {
        SecurityToken securityToken = tokens.get(uuid);
        if (securityToken == null || securityToken.getDeleted())
            return false;
        else if (securityToken.getExpireDateTime().isBefore(LocalDateTime.now())) {
            securityToken.setDeleted();
            return false;
        }
        return true;
    }

    @Override
    public SecurityToken getSecurityToken(String json) {
        JsonNode parent;
        try {
            parent = mapper.readTree(json);
            Iterator<JsonNode> iterator = parent.elements();
            if (iterator.hasNext()) {
                JsonNode node = iterator.next();
                String email = node.get("email").asText();
                String password = node.get("password").asText();
                return getSecurityToken(email, password);
            }
        } catch (Exception e) {
            log.error("getSecurityToken(String json) \n{}", e);
        }
        return null;
    }

    private SecurityToken getSecurityToken(String email, String password) {
        User user = userService.getUserByNamePassword(email, password);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDateTime = now.plusSeconds(EXPIRATION_PERIOD_SECONDS);
        if (user != null) {
            UUID uuid = UUID.randomUUID();
            SecurityToken securityToken = new SecurityToken(
                    user.getNickname(),
                    user.getEmail(),
                    user.getRole(),
                    expirationDateTime,
                    uuid
            );
            tokens.put(uuid, securityToken);
            log.info("token added {}", securityToken);
            return securityToken;
        }
        return null;
    }

    @Override
    public void deleteSecurityToken(UUID uuid) {
        SecurityToken securityToken = tokens.get(uuid);
        if (securityToken != null) {
            log.info("token near marked as deleted {}", securityToken);
            securityToken.setDeleted();
        } else {
            log.info("token to delete not found; uuid {}", uuid);
        }
    }

    @Override
    @Scheduled(cron = "${securityToken.cleanCron}")
    public void cleanSecurityToken() {
        LocalDateTime now = LocalDateTime.now();
        for (UUID uuid : tokens.keySet()) {
            if (tokens.get(uuid).getDeleted() || tokens.get(uuid).getExpireDateTime().isBefore(now)) {
                log.info("token near to be removed {}", tokens.get(uuid));
                tokens.remove(uuid);
            }
        }
    }
}
