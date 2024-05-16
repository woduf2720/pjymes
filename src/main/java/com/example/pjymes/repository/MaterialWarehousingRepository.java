package com.example.pjymes.repository;

import com.example.pjymes.domain.OrderSub;
import com.example.pjymes.domain.Warehousing;
import com.example.pjymes.repository.customRepository.CustomMaterialWarehousingRepository;
import com.example.pjymes.repository.customRepository.CustomOrderMasterRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialWarehousingRepository extends JpaRepository<Warehousing, Long>, CustomMaterialWarehousingRepository {

}
