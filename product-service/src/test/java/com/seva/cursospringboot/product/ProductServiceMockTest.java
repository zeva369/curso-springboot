package com.seva.cursospringboot.product;

import com.seva.cursospringboot.product.repository.ProductRepository;
import com.seva.cursospringboot.product.repository.entity.Category;
import com.seva.cursospringboot.product.repository.entity.Product;
import com.seva.cursospringboot.product.service.ProductService;
import com.seva.cursospringboot.product.service.ProductServiceImpl;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder()
                .id(1L)
                .name("Computadora portatil")
                .category(Category.builder().id(1L).build())
                .description("Una computadora portatil")
                .status("CREATED")
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .created(new Date())
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));

        Mockito.when(productRepository.save(computer)).thenReturn(computer);

    }

    @Test
    public void whenValidGetIdThenReturnProduct(){
        Product productFound = productService.getProduct(1L);
        Assertions.assertThat(productFound.getName()).isEqualTo("Computadora portatil");
    }

    @Test
    public void whenValidUpdatedStockThenReturnNewStock(){
        Product productWithNewStock = productService.updateStock(1L,Double.parseDouble("3"));
        Assertions.assertThat(productWithNewStock.getStock()).isEqualTo(Double.parseDouble("8"));
    }
}
