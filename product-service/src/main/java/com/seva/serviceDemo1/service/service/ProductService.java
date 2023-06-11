package com.seva.serviceDemo1.service.service;
import com.seva.serviceDemo1.service.entity.Product;
import com.seva.serviceDemo1.service.entity.Category;

import java.util.List;

public interface ProductService {
    public List<Product> listAllProduct();
    public Product getProduct(Long id);

    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public  Product deleteProduct(Long id);
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id, Double quantity);
}
