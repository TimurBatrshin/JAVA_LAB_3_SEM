package ru.itis.javalab.services;

import ru.itis.javalab.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getAllProductsFromCartUser();
}
