package org.example;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceUtil {
    public static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
//        ds.setUrl("jdbc:h2:tcp://localhost:9092/~/test;USER=sa;PASSWORD=");
        ds.setUrl("jdbc:h2:mem:testdb;USER=sa;PASSWORD=");
        ds.setUsername("sa");
        ds.setMinIdle(1);
        ds.setMaxActive(5);
        ds.setMaxOpenPreparedStatements(10);

        return ds;
    }
}
