package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDaoWithJDBC;
import com.codecool.shop.dao.ProductDaoWithJDBC;
import com.codecool.shop.dao.SupplierDaoWithJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoMemWithJDBC implements ProductDaoWithJDBC {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";

    private static final String DB_USER = "arinyu";
    private static final String DB_PASSWORD = "Faszfej1";

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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Product> getProductBy(Supplier supplier) {
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
    public  List<Product> getProductBy(ProductCategory productCategory){
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