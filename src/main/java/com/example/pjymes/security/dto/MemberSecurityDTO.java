package com.example.pjymes.security.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {

    private String mid;
    private String mpw;
    private String mname;
    private String commonCodeId;
    private String commonCodeName;
    private boolean useStatus;

    public MemberSecurityDTO(String username, String password, String mname, Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);

        this.mid = username;
        this.mpw = password;
        this.mname = mname;
    }
}
