package com.example.pjymes.repository;

import com.example.pjymes.domain.Bom;
import com.example.pjymes.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BomRepository extends JpaRepository<Bom, Long>{

}
