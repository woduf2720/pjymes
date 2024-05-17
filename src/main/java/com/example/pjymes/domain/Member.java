package com.example.pjymes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends BaseEntity{

    @Id
    private String mid;
    private String mpw;
    private String mname;
    private Boolean active;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "id")
    private Role role;

    public void changePassword(String mpw) {
        this.mpw = mpw;
    }

    public void change(String mname, boolean active){
        this.mname = mname;
        this.active = active;
    }
}
