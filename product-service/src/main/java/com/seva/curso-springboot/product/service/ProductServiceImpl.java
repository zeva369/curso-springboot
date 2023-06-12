package com.seva.serviceDemo1.service.service;

import com.seva.serviceDemo1.service.repository.entity.Category;
import com.seva.serviceDemo1.service.repository.entity.Product;
import com.seva.serviceDemo1.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements com.seva.serviceDemo1.service.service.ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreated(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product product1 = getProduct(product.getId());
        if (product1 == null) return null;
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setStatus(product.getStatus());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getStock());
        product1.setCreated(product.getCreated());
        product1.setCategory(product.getCategory());

        return productRepository.save(product1);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product1 = getProduct(id);
        if (product1 == null) return null;
        product1.setStatus("DELETED");
        return productRepository.save(product1);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product product1 = getProduct(id);
        if (product1 == null) return null;
        product1.setStock(product1.getStock() + quantity);
        return productRepository.save(product1);
    }
}
