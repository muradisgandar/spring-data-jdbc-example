package com.dbdemo.demo.controller;

import com.dbdemo.demo.TestHelper;
import com.dbdemo.demo.model.UserEntity;
import com.dbdemo.demo.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static UserEntity userEntity;

    private static HashMap<String,Object> stringObjectHashMap;

    private static ArrayList<UserEntity> userEntityArrayList;

    @BeforeAll
    public static void init(){
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
    public void getAll() throws Exception {
        when(userService.getUsers()).thenReturn(userEntityArrayList);

        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(TestHelper.getJsonString(userEntityArrayList)));
    }
}