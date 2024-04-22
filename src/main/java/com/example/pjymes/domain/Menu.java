package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    private Integer displayOrder;

    @Column(length = 50, nullable = false)
    private String menuName;

    @Column(length = 50, nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "menuId")
    private Menu parentMenu;

    //수정할때 db내용을 조회 후 그걸 바꿔서 저장하기 때문에 change기능이 필요하다
    public void change(Integer displayOrder, String menuName, String url) {
        this.displayOrder = displayOrder;
        this.menuName = menuName;
        this.url = url;
    }
}
