package ru.itis.javalab.services;

import ru.itis.javalab.models.Product;
import ru.itis.javalab.repositories.ProductRepositories;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private ProductRepositories productRepositories;


    public ProductServiceImpl(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositories.getAllProduct();
    }

    @Override
    public List<Product> getAllProductsFromCartUser() {
        return productRepositories.findAllProductsFromCartUser();
    }
}
