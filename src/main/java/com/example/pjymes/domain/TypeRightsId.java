package com.example.pjymes.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TypeRightsId implements Serializable {
    private String typeId;
    private Long menuId;
}
