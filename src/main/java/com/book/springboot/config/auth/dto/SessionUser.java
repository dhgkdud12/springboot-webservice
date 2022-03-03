package com.book.springboot.config.auth.dto;

import com.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
//인증된 사용자 정보만 필요함
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}