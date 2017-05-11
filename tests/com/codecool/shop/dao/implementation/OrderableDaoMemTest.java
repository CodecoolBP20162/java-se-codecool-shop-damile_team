package com.codecool.shop.dao.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by minh on 2017.05.10..
 */
class OrderableDaoMemTest {

    @Test
    public void TestIsIDNotUnder0(){
        OrderableDaoMem test = OrderableDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()->{
            test.find(-1);
            test.remove(-1);
        });

    }
}