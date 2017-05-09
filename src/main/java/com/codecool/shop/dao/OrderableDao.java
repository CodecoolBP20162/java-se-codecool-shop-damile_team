package com.codecool.shop.dao;

import com.codecool.shop.model.Orderable;

import java.util.List;

public interface OrderableDao {

    void add(Orderable orderable);
    Orderable find(int id);
    void remove(int id);
    List<Orderable> getAll();
    boolean order();
}