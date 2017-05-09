package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DaoWithJDBC;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class DaoMemWithJDBC implements DaoWithJDBC {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/todolist";
    private static final String DB_USER = "szilarddavid";
    private static final String DB_PASSWORD = "szilarddavid";

    @Override
    public List<Object> listAllProducts() {
        String query = "SELECT * FROM products;";

        List<Object> resultList = new ArrayList<>();
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