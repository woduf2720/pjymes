package com.example.pjymes.repository;

import com.example.pjymes.domain.Item;
import com.example.pjymes.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String>{

}
