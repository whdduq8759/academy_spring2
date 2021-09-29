package com.spring.mvc.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@ToString
public class User {

    private String userId;
    private String userPw;
    private String userName;
    private int userAge;
    private List<String> habbys;
}
