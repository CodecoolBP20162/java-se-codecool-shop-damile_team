package com.codecool.shop.dao;
import com.codecool.shop.model.ProductCategory;
import java.util.List;

public interface ProductCategoryDaoWithJDBC {

    List<ProductCategory> getAllCategories();
    ProductCategory findCategory(int id);
}
