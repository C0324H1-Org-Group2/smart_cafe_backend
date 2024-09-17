package com.group2.smart_cafe_backend.config.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;
    private final Collection<? extends GrantedAuthority> authorities;
    private String nameEmpployee;

    public JwtResponse(Long id, String token, String username, String name, Collection<? extends GrantedAuthority> authorities, String nameEmpployee) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.name = name;
        this.authorities = authorities;
        this.nameEmpployee = nameEmpployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getNameEmpployee() {
        return nameEmpployee;
    }

    public void setNameEmpployee(String nameEmpployee) {
        this.nameEmpployee = nameEmpployee;
    }
}
