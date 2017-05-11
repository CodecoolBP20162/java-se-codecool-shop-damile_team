package com.codecool.shop.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCConnectionDao {

    Connection getConnection() throws SQLException;
    void executeQuery(String query);
    String getDbData(int textLine) throws IOException;
}