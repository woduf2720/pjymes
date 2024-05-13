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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"code", "parent_code"}))
public class Bom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "code")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "parent_code", referencedColumnName = "code")
    private Item parentItem;

    public void change(Long quantity) {
        this.quantity = quantity;
    }
}
