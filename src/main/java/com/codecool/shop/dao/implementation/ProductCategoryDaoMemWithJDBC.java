package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.ProductCategoryDaoWithJDBC;
import com.codecool.shop.dao.ProductDaoWithJDBC;
import com.codecool.shop.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ProductCategoryDaoMemWithJDBC extends JDBCConnection implements ProductCategoryDaoWithJDBC {

    public ProductCategoryDaoMemWithJDBC() throws IOException {
    }

    @Override
    public List<ProductCategory> getAllCategories() throws IOException {
        String query = "SELECT * FROM productcategories;";
        List<ProductCategory> resultList = new ArrayList<>();
        ProductDaoWithJDBC productDaoWithJDBC = new ProductDaoMemWithJDBC();
        List<Product> products = productDaoWithJDBC.listAllProducts();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ProductCategory prodCat = new ProductCategory(
                        resultSet.getInt("productCategoryId"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                for(Product prod : products) {
                    if(prod.getProductCategory().getProductCategoryId().equals(prodCat.getProductCategoryId())) {
                        prodCat.addProduct(prod);
                    }
                }
                resultList.add(prodCat);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public ProductCategory findCategory(int id) {
        String query = "SELECT * FROM productcategories WHERE productCategoryId ='" + id + "';";

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                ProductCategory prodCat = new ProductCategory(
                        resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                connection.close();
                return prodCat;
            } else {return null;}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductCategory findCategory(String name) {
        String query = "SELECT * FROM productcategories WHERE name ='" + name + "';";

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                ProductCategory prodCat = new ProductCategory(
                        resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                connection.close();
                return prodCat;
            } else {return null;}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(ProductCategory prodCat) {
        String query = "INSERT INTO productcategories (productCategoryId, name, department, description)" +
                "VALUES ('" + prodCat.getProductCategoryId() + "', '" + prodCat.getName() + "', '" + prodCat.getDepartment() + "', '" + prodCat.getDescription() + "');";
        executeQuery(query);
    }

}