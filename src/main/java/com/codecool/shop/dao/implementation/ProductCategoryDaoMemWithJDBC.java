package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.ProductCategoryDaoWithJDBC;
import com.codecool.shop.dao.ProductDaoWithJDBC;
import com.codecool.shop.model.*;

import java.sql.*;
import java.util.*;

public class ProductCategoryDaoMemWithJDBC implements ProductCategoryDaoWithJDBC {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "szilarddavid";
    private static final String DB_PASSWORD = "szilarddavid";

    @Override
    public List<ProductCategory> getAllCategories() {
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}