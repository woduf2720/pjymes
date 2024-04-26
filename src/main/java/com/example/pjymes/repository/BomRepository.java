package com.example.pjymes.repository;

import com.example.pjymes.domain.Bom;
import com.example.pjymes.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BomRepository extends JpaRepository<Bom, Long>{
    List<Bom> findByParentItemCode(String parentItemCode);
}
