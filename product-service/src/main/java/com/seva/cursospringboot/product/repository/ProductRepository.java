package com.seva.cursospringboot.product.repository;

import com.seva.cursospringboot.product.repository.entity.Category;
import com.seva.cursospringboot.product.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findByCategory(Category category);
}
