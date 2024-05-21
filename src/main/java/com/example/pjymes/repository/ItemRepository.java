package com.example.pjymes.repository;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.domain.Item;
import com.example.pjymes.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String>{
    List<Item> findByCategory(CommonCode category);

}
