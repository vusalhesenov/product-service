package az.work.productservice.service;

import az.work.productservice.dto.ProductRequest;
import az.work.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
