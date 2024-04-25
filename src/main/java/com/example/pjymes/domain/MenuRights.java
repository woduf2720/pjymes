package com.example.pjymes.domain;

import com.example.pjymes.dto.MenuRightsDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuRights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "id")
    private CommonCode userType;

    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    private Boolean hasAccess;

    public void change(MenuRightsDTO menuRightsDTO) {
        this.hasAccess = menuRightsDTO.getHasAccess();
    }
}

