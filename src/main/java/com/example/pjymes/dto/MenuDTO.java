package com.example.pjymes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private Long id;
    private Integer orderIndex;
    private String name;
    private String url;
    private Long parentId;

    @Builder.Default
    private List<MenuDTO> children = new ArrayList<>();
}
