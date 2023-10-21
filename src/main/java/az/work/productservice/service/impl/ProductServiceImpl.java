package az.work.productservice.service.impl;

import az.work.productservice.dto.ProductRequest;
import az.work.productservice.dto.ProductResponse;
import az.work.productservice.entity.Product;
import az.work.productservice.repository.ProductRepository;
import az.work.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("Product {} is saved",product.getId());

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> all = productRepository.findAll();
        return all.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
        return productResponse;
    }
}
