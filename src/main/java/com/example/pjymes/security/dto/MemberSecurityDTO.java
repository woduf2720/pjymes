package com.example.pjymes.security.dto;

import com.example.pjymes.domain.Member;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {

    private String mid;
    private String mpw;
    private String mname;
    private Long userTypeId;
    private String userTypeName;
    private boolean active;

    public MemberSecurityDTO(Member member) {

        super(member.getMid(),
                member.getMpw(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + member.getRole().getName()))
        );

        this.mid = member.getMid();
        this.mpw = member.getMpw();
        this.mname = member.getMname();
        this.userTypeId = member.getRole().getId();
        this.userTypeName = member.getRole().getName();
        this.active = member.getActive();
    }

}
