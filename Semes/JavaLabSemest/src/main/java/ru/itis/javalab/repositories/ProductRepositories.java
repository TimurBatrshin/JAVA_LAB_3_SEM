package ru.itis.javalab.repositories;

import org.springframework.security.core.parameters.P;
import ru.itis.javalab.models.Product;

import java.util.List;

public interface ProductRepositories extends CrudRepository<Product, Long> {
    List<Product> getAllProduct();
    List<Product> findAllProductsFromCartUser();
}
