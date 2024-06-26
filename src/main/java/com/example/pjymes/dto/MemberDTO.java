package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO{

    private String mid;
    private String mpw;
    private String mname;
    private Long roleId;
    private String roleName;
    private Boolean active;
}
