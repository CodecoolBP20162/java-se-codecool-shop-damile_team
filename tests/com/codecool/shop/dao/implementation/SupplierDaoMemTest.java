package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by minh on 2017.05.10..
 */
class SupplierDaoMemTest {

    @Test
    public void TestIsFindIDNotUnder0(){
        SupplierDaoMem test = SupplierDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()->{
            test.find(-1);
        });

    }

    @Test
    public void TestIsRemoveIDNotUnder0(){
        SupplierDaoMem test = SupplierDaoMem.getInstance();
        assertThrows(IllegalArgumentException.class, ()->{
            test.remove(-1);
        });

    }


    @Test
    public void TestIsCategorySizeAfterAdd(){
        SupplierDao test=SupplierDaoMem.getInstance();
        Supplier Lenovo = new Supplier(2,"Lenovo","Computer");
        Supplier Apple = new Supplier(3,"Apple","Mobile phones");
        test.add(Lenovo);
        test.add(Apple);
        int size = test.getAll().size();
        assertEquals(2,size);
        test.remove(1);
        test.remove(2);
    }

    @Test
    public void TestIsSupplierId(){
        SupplierDaoMem test=SupplierDaoMem.getInstance();
        Supplier Lenovo = new Supplier(2,"Lenovo","Computer");
        test.add(Lenovo);
        assertEquals(Lenovo.getSupplierId(),test.find(1).getSupplierId());
        test.remove(1);
    }

    @Test
    public void TestIsRemoveWorks(){
        SupplierDaoMem test = SupplierDaoMem.getInstance();
        Supplier Lenovo = new Supplier(2,"Lenovo","Computer");
        test.add(Lenovo);
        test.remove(1);
        assertEquals(0,test.getAll().size());

    }

}