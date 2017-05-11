package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.JDBCConnectionDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public abstract class JDBCConnection implements JDBCConnectionDao {

    private final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private final String DB_USER = getDbData(1);
    private final String DB_PASSWORD = getDbData(2);

    protected JDBCConnection() throws IOException {
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }
    @Override
    public void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDbData(int textLine) throws IOException {
        String UserOrPassword = null;
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/connection/connection.txt"));
        String line = null;
        int counter = 0;
        while ((line = in.readLine()) != null) {
            counter++;
            if(counter == textLine) {
                UserOrPassword = line;
            }


        }
        return UserOrPassword;
    }
}