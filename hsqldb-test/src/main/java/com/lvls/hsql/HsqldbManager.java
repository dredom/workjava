package com.lvls.hsql;

import java.sql.Connection;

public interface HsqldbManager {

    Connection getConnection() throws Exception;

    void newDatabase() throws Exception;

}
