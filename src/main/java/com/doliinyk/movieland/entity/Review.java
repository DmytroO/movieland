package com.doliinyk.movieland.entity;

import java.util.Objects;

public class Review {
    private int id;
    private User user;
    private String text;

    public Review(int id, int userId, String nickname, String text) {
        this.id = id;
        this.user = new User(userId, nickname);
        this.text = text;
    }

    public int getId() { return id; }
    public User getUser() { return user; }
    public String getText() { return text; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return id == review1.id &&
                user.getId() == review1.user.getId() &&
                user.getNickname() == review1.user.getNickname() &&
                Objects.equals(text, review1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, text);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + user +
                ", review='" + text + '\'' +
                '}';
    }
}
