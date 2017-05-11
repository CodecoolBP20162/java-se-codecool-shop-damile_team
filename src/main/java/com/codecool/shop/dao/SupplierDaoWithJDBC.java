package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDaoWithJDBC {

    List<Supplier> getAllSupplier();
    Supplier findSupplier(int id);
    void add(Supplier supplier);
}