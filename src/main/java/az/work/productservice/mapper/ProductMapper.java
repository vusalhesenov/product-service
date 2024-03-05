package az.work.productservice.mapper;

import az.work.productservice.dto.ProductRequest;
import az.work.productservice.dto.ProductResponse;
import az.work.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
    
    Product mapToEntity(ProductRequest productRequest);

    ProductResponse mapToProductResponse(Product product);
}
