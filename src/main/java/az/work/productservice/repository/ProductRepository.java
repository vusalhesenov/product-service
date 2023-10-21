package az.work.productservice.repository;

import az.work.productservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Override
    <S extends Product> S save(S entity);

    @Override
    List<Product> findAll();
}
