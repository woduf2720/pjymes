package com.example.pjymes.repository;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.repository.commonCode.CustomCommonCodeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonCodeRepository extends JpaRepository<CommonCode, String>, CustomCommonCodeRepository {

}
