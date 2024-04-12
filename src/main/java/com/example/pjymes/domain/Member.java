package com.example.pjymes.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member{

    @Id
    private String mid;
    private String mpw;
    private String email;

    public void changePassword(String mpw) {
        this.mpw = mpw;
    }

    public void changeEmail(String email){
        this.email = email;
    }
}
