package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRightsDTO {

    private String commonCodeId;
    private Long menuId;
    private String menuName;
    private Boolean useStatus;
}
