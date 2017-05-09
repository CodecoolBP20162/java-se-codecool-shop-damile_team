package com.codecool.shop.model;

import java.util.ArrayList;


public class Supplier extends BaseModel {
    private ArrayList<Product> products;
    private Integer supplierId;
    private String description;

    public Supplier(Integer supplierId, String name, String description) {
        super(name);
        this.setSupplierId(supplierId);
        this.products = new ArrayList<>();
        this.description = description;
    }

    public Integer getSupplierId() {return supplierId;}

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.supplierId,
                this.name,
                this.description
        );
    }
}