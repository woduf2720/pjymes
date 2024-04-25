package com.example.pjymes.dto;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.domain.Menu;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRightsDTO {

    private Long id;
    private Long menuId;
    private String menuName;
    private Boolean hasAccess;
}
