package com.example.pjymes.repository;

import com.example.pjymes.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {
    List<CommonCode> findAllByParentId(Long id);


}
