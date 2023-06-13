package com.seva.cursospringboot.product;

import com.seva.cursospringboot.product.repository.entity.Category;
import com.seva.cursospringboot.product.repository.entity.Product;
import com.seva.cursospringboot.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategoryThenReturnProductList(){
        Product product1 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("Una computadora")
                .price(Double.parseDouble("120"))
                .stock(Double.parseDouble("3"))
                .status("created")
                .created(new Date()).build();
        productRepository.save(product1);

        List<Product> productsFound = productRepository.findByCategory(product1.getCategory());

        Assertions.assertThat(productsFound.size()).isEqualTo(3);
    }
}
