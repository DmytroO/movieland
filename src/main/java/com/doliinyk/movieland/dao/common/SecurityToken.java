package com.doliinyk.movieland.dao.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class SecurityToken {
    @JsonIgnore
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @JsonIgnore
    private String name;
    private String email;
    @JsonIgnore
    private Boolean deleted;
    @JsonIgnore
    private String role;
    private UUID uuid;
    @JsonIgnore
    private LocalDateTime expireDateTime;

    public SecurityToken(String name, String email, String role, LocalDateTime expireDateTime, UUID uuid) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.expireDateTime = expireDateTime;
        this.uuid = uuid;
    }

    public void setDeleted() { this.deleted = deleted; }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public Boolean getDeleted() { return deleted; }
    public String getRole() { return role; }
    public LocalDateTime getExpireDateTime() { return expireDateTime; }
    public UUID getUuid() { return uuid; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityToken that = (SecurityToken) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(role, that.role) &&
                Objects.equals(expireDateTime, that.expireDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, deleted, role, expireDateTime);
    }

    @Override
    public String toString() {
        return "SecurityToken{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", deleted=" + deleted +
                ", role='" + role + '\'' +
                ", expireDateTime=" + expireDateTime +
                '}';
    }
}
