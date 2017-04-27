package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.OrderableDao;
import com.codecool.shop.model.Orderable;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.dao.ProductCategoryDao;

import java.util.ArrayList;
import java.util.List;

public class OrderableDaoMem implements OrderableDao {

    private List<Orderable> DATA = new ArrayList<>();
    private static OrderableDaoMem instance = null;

    private OrderableDaoMem() {
    }

    public static OrderableDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderableDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Orderable category) {
        category.setId(DATA.size() + 1);
        DATA.add(category);
    }

    @Override
    public Orderable find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Orderable> getAll() {
        return DATA;
    }

    public boolean order(){
        System.out.println("ordered");
    }
}