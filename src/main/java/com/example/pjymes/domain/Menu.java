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

    @Column(length = 20, nullable = false)
    private String menuName;

    private String url;

    private int displayOrder;

    private Long parentId;

    //수정할때 db내용을 조회 후 그걸 바꿔서 저장하기 때문에 change기능이 필요하다
    public void change(String menuName) {
        this.menuName = menuName;
    }
}
