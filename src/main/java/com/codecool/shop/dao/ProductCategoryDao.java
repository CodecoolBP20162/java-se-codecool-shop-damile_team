package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    ProductCategory find(int id);
    ProductCategory find(String name);
    void remove(int id);

    List<ProductCategory> getAll();

}
