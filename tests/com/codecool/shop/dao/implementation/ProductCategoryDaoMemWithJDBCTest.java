package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by minh on 2017.05.11..
 */
class ProductCategoryDaoMemWithJDBCTest {

    @Test
    public void TestIsGetAllCategories(){
        ProductCategoryDaoMemWithJDBC test = new ProductCategoryDaoMemWithJDBC();
        List<ProductCategory> a= test.getAllCategories();
        assertEquals(3,a.size());
    }

    @Test
    public void TestIsFindCategory(){
        ProductCategoryDaoMemWithJDBC test = new ProductCategoryDaoMemWithJDBC();
        ProductCategory a = test.findCategory(2);
        assertEquals("Mobile",a.getName());
    }

    @Test
    public void TestIsFindCategoryByString(){
        Integer number = 1;
        ProductCategoryDaoMemWithJDBC test = new ProductCategoryDaoMemWithJDBC();
        ProductCategory a = test.findCategory("Tablet");
        assertEquals(number,a.getProductCategoryId());
    }

    @Test
    public void TestIsAdd(){
        Integer number=4;
        ProductCategoryDaoMemWithJDBC test = new ProductCategoryDaoMemWithJDBC();
        ProductCategory a =new ProductCategory(4,"Dildo","adult+18","Good orgasm,good life");
        test.add(a);
        assertEquals(number,a.getProductCategoryId());

    }

}