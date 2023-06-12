package com.seva.serviceDemo1.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seva.serviceDemo1.service.repository.entity.Category;
import com.seva.serviceDemo1.service.repository.entity.Product;
import com.seva.serviceDemo1.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(name="category", required=false) Long categoryId){
        List<Product>  products = new ArrayList<Product>();
        if (categoryId == null) {
            products = productService.listAllProduct();
            if (products.isEmpty()) return ResponseEntity.noContent().build();
        } else {
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()) return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,formatErrorMessage(result));
        Product newProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product modifiedProduct){
        Product previousProduct = productService.getProduct(id);
        if (previousProduct == null) return ResponseEntity.notFound().build();
        previousProduct.setName(modifiedProduct.getName());
        Product updatedProduct = productService.updateProduct(previousProduct);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product previousProduct = productService.getProduct(id);
        if (previousProduct == null) return ResponseEntity.notFound().build();
        Product deletedProduct = productService.deleteProduct(id);
        return ResponseEntity.ok(deletedProduct);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Product> updateProductStock(@PathVariable("id")Long id, @RequestParam(name="quantity", required=true) Double quantity){
        Product previousProduct = productService.getProduct(id);
        if (previousProduct == null) return ResponseEntity.notFound().build();
        Product updatedProduct = productService.updateStock(id, quantity);
        return ResponseEntity.ok(updatedProduct);
    }

    private String formatErrorMessage(BindingResult result) {
        List<Map<String,String>> errors = result.getFieldErrors()
                .stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toList());
        com.seva.serviceDemo1.service.controller.ProductControllerErrorMessage errMessage = com.seva.serviceDemo1.service.controller.ProductControllerErrorMessage
                .builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
