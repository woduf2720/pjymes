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

    @EmbeddedId
    private MenuRightsId menuRightsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId", insertable = false, updatable = false)
    private Menu menu;

    private Boolean useStatus = true;

    public void change(MenuRightsDTO menuRightsDTO) {
        this.useStatus = menuRightsDTO.getUseStatus();
    }
}

