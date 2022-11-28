package com.example.cargodelivery.db;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class DBUtil {
    private static final String DB_USERNAME="username";
    private static final String DB_PASSWORD="password";
    private static final String DB_URL ="url";
    private static final String DB_DRIVER_CLASS="driver";
    private static ResourceBundle rb = null;
    private static HikariDataSource dataSource;
    static{
        try {
            rb = ResourceBundle.getBundle("database");

            dataSource = new HikariDataSource();
            dataSource.setDriverClassName(rb.getString(DB_DRIVER_CLASS));

            dataSource.setJdbcUrl(rb.getString(DB_URL));
            dataSource.setUsername(rb.getString(DB_USERNAME));
            dataSource.setPassword(rb.getString(DB_PASSWORD));

            dataSource.setMinimumIdle(100);
            dataSource.setMaximumPoolSize(2000);
            dataSource.setAutoCommit(false);
            dataSource.setLoginTimeout(3);

        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

}
