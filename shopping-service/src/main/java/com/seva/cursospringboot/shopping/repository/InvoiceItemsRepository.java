package com.seva.cursospringboot.shopping.repository;


import com.seva.cursospringboot.shopping.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
