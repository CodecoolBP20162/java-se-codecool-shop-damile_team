package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;
import spark.utils.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by minh on 2017.05.10..
 */
class ProductDaoMemTest {

    @Test
    public void TestIsIDNotUnder0(){
        ProductDaoMem test = ProductDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()->{
            test.find(-1);
            test.remove(-1);
        });

    }

    @Test
    public void TestIsAddWorks(){
        ProductDaoMem test = ProductDaoMem.getInstance();
        Supplier Apple = new Supplier(2,"Apple","not so cheap");
        ProductCategory tablet = new ProductCategory(1,"table","something","something");
        Product AmazonFire=new Product(1,"Ipad",100,"USD","IPAD",tablet,Apple);
        test.add(AmazonFire);
        assertEquals(1,test.getAll().size());
        test.remove(1);
    }

    @Test
    public void TestIsRemoveWorks(){
        ProductDaoMem test = ProductDaoMem.getInstance();
        ProductCategory tablet = new ProductCategory(1,"table","something","something");
        ProductCategory desktop = new ProductCategory(1,"table","something","something");
        Supplier computer = new Supplier(2,"Apple","not so cheap");
        Supplier Apple = new Supplier(2,"Apple","not so cheap");
        Product AmazonFire=new Product(1,"Ipad",100,"USD","IPAD",tablet,Apple);
        Product Alienware=new Product(1,"Computer",180,"USD","Computer",desktop,computer);
        test.add(AmazonFire);
        test.add(Alienware);
        assertEquals(2,test.getAll().size());

    }

}