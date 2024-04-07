package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Column(length = 20, nullable = false)
    private String menuName;

    private String url;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Menu parent;

    private int displayOrder;

    //수정시 이용
    public void change(String menuName) {
        this.menuName = menuName;
    }
}
