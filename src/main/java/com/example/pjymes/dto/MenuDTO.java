package com.example.pjymes.dto;

import com.example.pjymes.domain.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private Long menuId;
    private String menuName;
    private String url;
    private Integer displayOrder;
    private Long parentId;

    @Builder.Default
    private List<MenuDTO> children = new ArrayList<>();
}
