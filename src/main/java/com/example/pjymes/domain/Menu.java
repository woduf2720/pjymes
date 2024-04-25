package com.example.pjymes.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderIndex;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String url;

    @Setter
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Menu parent;

    public void change(Integer orderIndex, String name, String url) {
        this.orderIndex = orderIndex;
        this.name = name;
        this.url = url;
    }
}
