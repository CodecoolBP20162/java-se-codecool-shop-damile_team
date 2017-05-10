package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDaoWithJDBC;
import com.codecool.shop.model.*;

import java.sql.*;
import java.util.*;
import java.util.List;

public class SupplierDaoMemWithJDBC implements SupplierDaoWithJDBC {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "szilarddavid";
    private static final String DB_PASSWORD = "szilarddavid";

    @Override
    public List<Supplier> getAllSupplier() {
        String query = "SELECT * FROM suppliers;";

        List<Supplier> resultList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Supplier supp = new Supplier(
                        resultSet.getInt("supplierId"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                resultList.add(supp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Supplier findSupplier(int id) {
        String query = "SELECT * FROM suppliers WHERE supplierId ='" + id + "';";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                return supplier;
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