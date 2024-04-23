package com.example.pjymes.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class MemberDTO{

    private String mid;
    private String mpw;
    private String mname;
    private String commonCodeId;
    private String commonCodeName;
    private Boolean useStatus;
}
