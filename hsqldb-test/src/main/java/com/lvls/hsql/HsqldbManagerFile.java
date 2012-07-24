package com.lvls.hsql;

import java.sql.Connection;
import java.sql.DriverManager;

public class HsqldbManagerFile implements HsqldbManager {

//    private static final String DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String DRIVER = "org.hsqldb.jdbcDriver";
    private static final String CONNECTION_NEW = "jdbc:hsqldb:file:target/dbfiles/testfiledb;hsqldb.default_table_type=cached;shutdown=true";
    private static final String CONNECTION = "jdbc:hsqldb:file:target/dbfiles/testfiledb;ifexists=true";

    private static boolean loaded;
    private Connection connection;

    @Override
    public Connection getConnection() throws Exception {
        if (connection == null) {
            try {
                this.connection = createConnection();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        return this.connection;
    }

    private Connection createConnection() throws Exception {
        loadDb();
        Connection c = DriverManager.getConnection(CONNECTION, "SA", "");
        return c;
    }

    private void loadDb() throws ClassNotFoundException {
        if (loaded) {
            return;
        }
        Class.forName(DRIVER);
        loaded = true;
    }

    @Override
    public void newDatabase() throws Exception {
        loadDb();
        Connection c = DriverManager.getConnection(CONNECTION_NEW, "SA", "");
        this.connection = c;
    }
}
