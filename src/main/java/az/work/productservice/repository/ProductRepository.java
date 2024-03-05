package az.work.productservice.repository;

import az.work.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Override
    <S extends Product> S save(S entity);

    @Override
    List<Product> findAll();
}
