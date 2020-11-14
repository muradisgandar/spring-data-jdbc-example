package com.dbdemo.demo.service.service.impl;

import com.dbdemo.demo.model.UserEntity;
import com.dbdemo.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private UserService userService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private SimpleJdbcCall simpleJdbcCallRef;

    private UserEntity userEntity;

    private HashMap<String,Object> stringObjectHashMap;

    private ArrayList<UserEntity> userEntityArrayList;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(jdbcTemplate);

        simpleJdbcCallRef = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("msauth")
                .withProcedureName("getUsers")
                .returningResultSet("o_c_user",
                        BeanPropertyRowMapper.newInstance(UserEntity.class));

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("Murad");
        userEntity.setLastName("Isgandarli");
        userEntity.setMail("ads@fgmi.CNI");
        userEntity.setPopularity(100);

        userEntityArrayList = new ArrayList<>();
        userEntityArrayList.add(userEntity);

        stringObjectHashMap = new HashMap<>();

        stringObjectHashMap.put("o_c_user",userEntityArrayList);
    }

    @Test
    public void get_users(){
        when(simpleJdbcCallRef.execute()).thenReturn(stringObjectHashMap);

        List<UserEntity> users = userService.getUsers();

        assertEquals(userEntityArrayList,users);
    }


}