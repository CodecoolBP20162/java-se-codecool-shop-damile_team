package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoWithJDBC;
import com.codecool.shop.dao.ProductDaoWithJDBC;
import com.codecool.shop.dao.SupplierDaoWithJDBC;
import com.codecool.shop.model.*;

import java.sql.*;
import java.util.*;
import java.util.List;

public class ProductDaoMemWithJDBC implements ProductDaoWithJDBC {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "szilarddavid";
    private static final String DB_PASSWORD = "szilarddavid";

    @Override
    public List<Product> listAllProducts() {
        ProductCategoryDaoWithJDBC productCategoryDao = new ProductCategoryDaoMemWithJDBC();
        SupplierDaoWithJDBC supplierDaoWithJDBC = new SupplierDaoMemWithJDBC();
        String query = "SELECT * FROM products;";

        List<Product> resultList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int prodCatId = resultSet.getInt("productCategoryId");
                int supplierId = resultSet.getInt("supplierId");
                ProductCategory prodCat = productCategoryDao.findCategory(prodCatId);
                Supplier supplier = supplierDaoWithJDBC.findSupplier(supplierId);
                Product prod = new Product(
                        resultSet.getInt("productId"),
                        resultSet.getString("name"),
                        resultSet.getInt("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        prodCat,
                        supplier
                );
                resultList.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Product> getProductBy(Supplier supplier) {
        List<Product> resultList = new ArrayList<>();
        return resultList;
    }

    @Override
    public  List<Product> getProductBy(ProductCategory productCategory){
        List<Product> resultList = new ArrayList<>();
        return resultList;
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