package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by arinyu on 2017.05.11..
 */
class ProductDaoMemWithJDBCTest {
    @Test
    public void TestIsListAllProducts(){

        ProductDaoMemWithJDBC test = new ProductDaoMemWithJDBC();
        List<Product> myList = test.listAllProducts();
        assertEquals(9, myList.size());
    }

    @Test
    public void TestIsGetProductBySupplier(){
        Supplier sup = new Supplier(4, "NewSup", "Deasdas");
        ProductDaoMemWithJDBC test = new ProductDaoMemWithJDBC();
        System.out.println(test.getProductBy(sup));
        SupplierDaoMemWithJDBC supplierDaoWithJDBC = new SupplierDaoMemWithJDBC();
        for (Supplier supplier : supplierDaoWithJDBC.getAllSupplier()) {
            if (supplier.getId() == 1) {
                assertEquals(supplier.getName(), "Amazon");
         } else if (supplier.getId() == 2) {
                assertEquals(supplier.getName(), "Lenovo");
            }
        }
    }
    @Test
    public void TestIsGetProductByProductCategory(){
        ProductCategory pc = new ProductCategory(3, "ProCat", "asda", "lkAJKs");
        ProductDaoMemWithJDBC test = new ProductDaoMemWithJDBC();
        ProductCategoryDaoMemWithJDBC productCategoryDaoMemWithJDBC = new ProductCategoryDaoMemWithJDBC();
        for (ProductCategory productCategory: productCategoryDaoMemWithJDBC.getAllCategories()) {
            System.out.println(productCategory);
            if (productCategory.getId() == 1) {
                assertEquals(productCategory.getName(), "Tablet");
            }
        }
    }
    @Test
    public void TestIsAddProduct(){
        ProductCategory pc = new ProductCategory(3, "ProCat", "asda", "lkAJKs");
        Supplier sup = new Supplier(4, "NewSup", "Deasdas");
        ProductDaoMemWithJDBC test = new ProductDaoMemWithJDBC();
        Product product = new Product(10, "LenaBanana", 90, "USD", "Our HTC Desire is a great value for media consumption.", pc, sup);
        test.add(product);
        assertEquals(10, test.listAllProducts().size());
    }
}