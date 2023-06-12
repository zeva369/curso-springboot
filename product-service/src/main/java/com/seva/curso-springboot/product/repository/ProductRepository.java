package com.seva.serviceDemo1.service.repository;

import com.seva.serviceDemo1.service.repository.entity.Category;
import com.seva.serviceDemo1.service.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findByCategory(Category category);
}
