package com.example.pjymes.domain;

import com.example.pjymes.dto.CommonCodeDTO;
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
public class CommonCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @Setter
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CommonCode parent;

    @Column(nullable = false)
    private Boolean active;

    public void change(String name, String description, Boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }
}
