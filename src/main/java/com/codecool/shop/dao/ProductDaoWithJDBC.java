package com.codecool.shop.dao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface ProductDaoWithJDBC {
    List<Product> listAllProducts();
    List<Product> getProductBy(Supplier supplier);
    List<Product> getProductBy(ProductCategory productCategory);
}
