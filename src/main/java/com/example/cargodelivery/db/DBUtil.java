package com.example.cargodelivery.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.io.*;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBUtil {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;
    static{
        ResourceBundle rb = ResourceBundle.getBundle("database");
        config.setDriverClassName(rb.getString("driver"));
        config.setJdbcUrl(rb.getString("url"));
        config.setUsername(rb.getString("username"));
        config.setPassword(rb.getString("password"));
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        dataSource = new HikariDataSource(config);
    }
    private DBUtil()  {}
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

}
