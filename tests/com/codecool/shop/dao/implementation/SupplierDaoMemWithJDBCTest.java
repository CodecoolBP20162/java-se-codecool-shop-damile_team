package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by minh on 2017.05.11..
 */
class SupplierDaoMemWithJDBCTest {

    @Test
    public void TestIsGetAll(){
        SupplierDaoMemWithJDBC test = new SupplierDaoMemWithJDBC();
        assertEquals(5,test.getAllSupplier().size());
    }

    @Test
    public void TestIsFindSupplier(){
        SupplierDaoMemWithJDBC test = new SupplierDaoMemWithJDBC();
        Supplier a = test.findSupplier(4);
        assertEquals("Samsung",a.getName());
    }

    @Test
    public void TestIsAdd(){
        SupplierDaoMemWithJDBC test = new SupplierDaoMemWithJDBC();
        Supplier a = new Supplier(6,"Alienware","BestGamingPc");
        test.add(a);
        assertEquals(6,test.getAllSupplier().size());
    }

}