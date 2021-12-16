package com.spring.boot.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.mc.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
