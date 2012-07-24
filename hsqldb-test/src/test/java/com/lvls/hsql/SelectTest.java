package com.lvls.hsql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class SelectTest {

    final String SELECT = "select value from keyvalue" +
    		" where key = 'key.A.B'";

    @Test
    public void select() throws Exception {
        HsqldbManager manager = new HsqldbManagerFile();

        Connection conn = manager.getConnection();

        Statement stmt = conn.createStatement();

        ResultSet results = stmt.executeQuery(SELECT);
        boolean rows = results.next();
        assertTrue(rows);
        String value = results.getString(1);
        assertNotNull(value);
        assertEquals("<xml>stuff</xml>", value);

        conn.close();

    }
}
