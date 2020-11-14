package com.dbdemo.demo.service.service.impl;

import com.dbdemo.demo.model.UserEntity;
import com.dbdemo.demo.service.UserService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    private final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserEntity> getUsers() {

        SimpleJdbcCall simpleJdbcCallRef = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("msauth")
                .withProcedureName("getUsers")
                .returningResultSet("o_c_user",
                        BeanPropertyRowMapper.newInstance(UserEntity.class));

        Map<String, Object> execute = simpleJdbcCallRef.execute();


        return (List<UserEntity>)execute.get("o_c_user");
    }

    @Override
    public List<UserEntity> getUsersViaStatus(String status) {

        SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getUserForStatus")
                .returningResultSet("o_c_user",
                BeanPropertyRowMapper.newInstance(UserEntity.class));

        SqlParameterSource in = new MapSqlParameterSource().addValue("userStatus", status);
        Map<String, Object> out = jdbcCall.execute(in);



        return (List<UserEntity>)out.get("o_c_user");
    }

    @Override
    public UserEntity getUserById(Long id) {
        SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_user_by_id")
                .returningResultSet("user_by_id",
                        BeanPropertyRowMapper.newInstance(UserEntity.class));

        SqlParameterSource in = new MapSqlParameterSource().addValue("userId", id);

        Map<String, Object> out = jdbcCall.execute(in);
        List user = (List) out.get("user_by_id");

        return (UserEntity) user.get(0);
    }
}
