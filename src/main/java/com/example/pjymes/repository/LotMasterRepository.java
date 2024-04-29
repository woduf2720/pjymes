package com.example.pjymes.repository;

import com.example.pjymes.domain.LotMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotMasterRepository extends JpaRepository<LotMaster, String>, CustomLotMasterRepository {
    List<LotMaster> findByItemCodeAndQuantityIsNot(String item_code, Long quantity);
}
