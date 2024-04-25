package com.example.pjymes.dto;

import lombok.Data;

@Data
public class MemberDTO{

    private String mid;
    private String mpw;
    private String mname;
    private Long userTypeId;
    private String userTypeName;
    private Boolean active;
}
