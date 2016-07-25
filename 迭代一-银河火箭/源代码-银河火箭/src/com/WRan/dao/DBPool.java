package com.WRan.dao;



import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBPool {
    private static final String configFile = "com/WRan/dao/dbcp.properties";
    private static DataSource dataSource;

    static {
        Properties dbProperties = new Properties();
        try {
            File f = new File(configFile);
            //System.out.println(f.getAbsolutePath());
            dbProperties.load(DBPool.class.getClassLoader().getResourceAsStream(configFile));
            dataSource = BasicDataSourceFactory.createDataSource(dbProperties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DBPool() {
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(ResultSet rs) {
        try {
            Connection conn = rs.getStatement().getConnection();
            if (conn != null && !conn.isClosed()) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("SQL code: %d; SQL state: %s", e.getErrorCode(), e.getSQLState()), e);
        }
    }

    public static void executeUpdate(String sql) {
        try (Connection conn = DBPool.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("SQL code: %d; SQL state: %s", e.getErrorCode(), e.getSQLState()), e);
        }
    }
}
