package com.codecool.shop.dao.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by minh on 2017.05.10..
 */
class ProductCategoryDaoMemTest {

    @Test
    public void TestIsFindIDNotUnder0(){
        ProductCategoryDaoMem test = ProductCategoryDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class,()->{
            test.find(-1);
        });
    }

    @Test
    public void TestIsRemoveIDNotUnder0(){
        ProductCategoryDaoMem test = ProductCategoryDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class,()->{
            test.remove(-1);
        });
    }


}