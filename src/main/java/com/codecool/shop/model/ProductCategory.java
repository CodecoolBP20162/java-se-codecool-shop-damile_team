package com.codecool.shop.model;

import java.util.ArrayList;

public class ProductCategory extends BaseModel {
    private String department;
    private ArrayList<Product> products;
    private Integer productCategoryId;
    private String description;

    public ProductCategory(Integer productCategoryId, String name, String department, String description) {
        super(name);
        this.setProductCategoryId(productCategoryId);
        this.department = department;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public Integer getProductCategoryId() {return productCategoryId;}

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.productCategoryId,
                this.name,
                this.department,
                this.description);
    }
}