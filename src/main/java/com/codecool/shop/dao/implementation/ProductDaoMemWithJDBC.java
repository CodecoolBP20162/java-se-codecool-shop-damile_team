package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoWithJDBC;
import com.codecool.shop.dao.ProductDaoWithJDBC;
import com.codecool.shop.dao.SupplierDaoWithJDBC;
import com.codecool.shop.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;

public class ProductDaoMemWithJDBC extends JDBCConnection implements ProductDaoWithJDBC {

    public ProductDaoMemWithJDBC() throws IOException {
    }

    @Override
    public List<Product> listAllProducts() throws IOException {
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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Product> getProductBy(Supplier supplier) throws IOException {
        String query = "SELECT * FROM products WHERE supplierId = " + supplier.getSupplierId() + ";";
        List<Product> resultList = new ArrayList<>();
        ProductCategoryDaoWithJDBC productCategoryDao = new ProductCategoryDaoMemWithJDBC();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int prodCatId = resultSet.getInt("productCategoryId");
                ProductCategory prodCat = productCategoryDao.findCategory(prodCatId);
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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public  List<Product> getProductBy(ProductCategory productCategory) throws IOException {
        String query = "SELECT * FROM products WHERE productCategoryId = " + productCategory.getProductCategoryId() + ";";
        List<Product> resultList = new ArrayList<>();
        SupplierDaoWithJDBC supplierDaoWithJDBC = new SupplierDaoMemWithJDBC();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int supplierId = resultSet.getInt("supplierId");
                Supplier supplier = supplierDaoWithJDBC.findSupplier(supplierId);
                Product prod = new Product(
                        resultSet.getInt("productId"),
                        resultSet.getString("name"),
                        resultSet.getInt("defaultPrice"),
                        resultSet.getString("currencyString"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier
                );
                resultList.add(prod);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public void add(Product product){
        String query = "INSERT INTO products (productId, name, defaultPrice, currencyString," +
                "description, productCategoryId, supplierId)" +
                "VALUES ('" + product.getProductId() + "', '" + product.getName() + "', '" + product.getDefaultPrice() +
                "', '" + product.getDefaultCurrency() + "', '" + product.getDescription() + "', '" + product.getProductCategory().getProductCategoryId() +
                "', '" + product.getSupplier().getSupplierId() + "');";
        executeQuery(query);
    }

}