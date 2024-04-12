package com.example.pjymes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TypeRights {

    @EmbeddedId
    private TypeRightsId typeRightsId;

    @Column(length = 20)
    private String typeName;

    public void change(String typeName) {
        this.typeName = typeName;
    }
}

