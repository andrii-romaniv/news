package com.site.news.demo.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    ROLE_ADMIN("Administrator"),
    ROLE_USER("User");

    private String authorityName;

    Authority(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String getAuthority() {
        return this.toString();
    }

    public String getAuthoritySimpleName() {
        return this.authorityName;
    }

}