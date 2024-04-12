package com.example.pjymes.repository;

import com.example.pjymes.domain.TypeRights;
import com.example.pjymes.domain.TypeRightsId;
import com.example.pjymes.repository.TypeRights.CustomTypeRightsRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRightsRepository extends JpaRepository<TypeRights, TypeRightsId>, CustomTypeRightsRepository {

}
