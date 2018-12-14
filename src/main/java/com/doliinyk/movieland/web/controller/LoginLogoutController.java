package com.doliinyk.movieland.web.controller;

import com.doliinyk.movieland.dao.common.SecurityToken;
import com.doliinyk.movieland.service.SecurityTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@RestController
public class LoginLogoutController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private SecurityTokenService securityTokenService;

    @Autowired
    public void setSecurityTokenService(SecurityTokenService securityTokenService) {
        this.securityTokenService = securityTokenService;
    }

    @PostMapping(
            path = "/v1/login",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public SecurityToken login(
            @RequestBody String requestJson,
            HttpServletResponse response
    ) {
        log.info("requestJson {}", requestJson);
        SecurityToken securityToken = securityTokenService.getSecurityToken(requestJson);
        if (securityToken == null) {
            log.info("set BAD_REQUEST");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @DeleteMapping(path = "/v1/logout", headers = "{uuid=*}")
    public void logout(
            @RequestHeader("uuid") String uuid,
            HttpServletResponse response
    ) throws IOException {
        log.info("logout {}", uuid);
        securityTokenService.deleteSecurityToken(UUID.fromString(uuid));
        Cookie uuidCookie = new Cookie("UUID", "");
        uuidCookie.setMaxAge(0);
        response.addCookie(uuidCookie);
        response.sendRedirect("v1/login");
    }
}
