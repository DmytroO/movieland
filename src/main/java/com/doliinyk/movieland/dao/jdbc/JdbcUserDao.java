package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.UserDao;
import com.doliinyk.movieland.dao.common.HashGenerator;
import com.doliinyk.movieland.dao.jdbc.mapper.UserRowMapper;
import com.doliinyk.movieland.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcUserDao implements UserDao {
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private static final String GET_USER_BY_NAME_PASSWORD = "select u.id, u.name as nickname, u.email, u.role\n" +
            "from     movieland_user u\n" +
            "where  u.email = :email\n" +
            "and    u.shuffled_password = :shuffled_password";

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public User getUserByNamePassword(String email, String password) {
        String shuffled_password = HashGenerator.getHash(password, email);
        Map<String, String> namedParameters = new HashMap<>();

        namedParameters.put("email", email);
        namedParameters.put("shuffled_password", shuffled_password);
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
        return npjt.queryForObject(GET_USER_BY_NAME_PASSWORD, namedParameters, USER_ROW_MAPPER);
    }
}
