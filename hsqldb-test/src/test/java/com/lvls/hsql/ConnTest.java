package com.lvls.hsql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class ConnTest {

    String DDL = "create table keyvalue (" +
    		" key  varchar not null  primary key," +
    		" value varchar not null" +
    		")";
    final String INSERT = "insert into keyvalue values (" +
    		" 'key.A.B', '<xml>stuff</xml>'" +
    		")";
    final String SELECT = "select value from keyvalue" +
    		" where key = 'key.A.B'";

    @Test
    public void mem() throws Exception {
        HsqldbManager manager = new HsqldbManagerMem();

        Connection conn = manager.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(DDL);
        stmt.execute(INSERT);
        conn.commit();

        ResultSet results = stmt.executeQuery(SELECT);
        boolean rows = results.next();
        assertTrue(rows);
        String value = results.getString(1);
        assertNotNull(value);
        assertEquals("<xml>stuff</xml>", value);

        conn.close();

    }

    @Test
    public void file() throws Exception {
        HsqldbManager manager = new HsqldbManagerFile();
        manager.newDatabase();
        Connection conn = manager.getConnection();

        Statement stmt = conn.createStatement();
        stmt.execute(DDL);
        stmt.execute(INSERT);
        conn.commit();

        ResultSet results = stmt.executeQuery(SELECT);
        boolean rows = results.next();
        assertTrue(rows);
        String value = results.getString(1);
        assertNotNull(value);
        assertEquals("<xml>stuff</xml>", value);

        conn.close();

    }
}
