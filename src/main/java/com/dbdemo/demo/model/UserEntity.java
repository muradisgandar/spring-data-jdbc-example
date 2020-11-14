package com.dbdemo.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    private Long id;

    private String username;

    private String password;

    private String mail;

    private String imageUrl;

    private String firstName;

    private String lastName;

    private Role role;

    private Status status;

    private Integer popularity;

    private Integer remainingQuackCount;

    private Integer remainingHateCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

}
