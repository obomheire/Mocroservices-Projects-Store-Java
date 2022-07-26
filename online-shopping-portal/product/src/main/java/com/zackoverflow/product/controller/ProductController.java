package com.zackoverflow.product.controller;

import com.zackoverflow.product.dto.Product;
import com.zackoverflow.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/v1")
public class ProductController {

//    private static  final Logger logger = LoggerFactory.getLogger(ProductController.class); // BEFORE LOMBOK
//    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) { // This could be Autowired as shown above
        this.productService = productService;
    }

    @PostMapping("/addProduct") // Post request endpoint
    ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {

        String status = productService.addProduct(product);

//        logger.info("Product added status - {}", status ); // BEFORE LOMBOK
        log.info("Product added status - {}", status ); // WITH LOMBOK

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/productList") // Get all product request endpoint
    List<Product> productList() {
        return  productService.listAllproducts();
    }

    @GetMapping("/productList/{category}") // Get product by category request endpoint
    List<Product> productCategoryList(@PathVariable String category) {
        return productService.productCategoryList(category);
    }

    @GetMapping("/product/{id}") // Get product by id request endpoint
    Product productById(@PathVariable String id) {
        return productService.productById(id);
    }

    @PutMapping("/productUpdate") // Update product request endpoint
    String updateProduct(@RequestBody Product product) {
       return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}") // Delete product request endpoint
    String deleteProductById(@PathVariable String id) { return productService.deleteProductById(id); }
}