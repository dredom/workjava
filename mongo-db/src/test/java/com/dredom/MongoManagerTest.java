package com.dredom;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoManagerTest {

	static final String CONNECTION_STRING = "mongodb://localhost/testdb";


	@Test
	public void basic() throws Exception {
		MongoManager instance = new MongoManager();
		instance.setConnectionString(CONNECTION_STRING);
		instance.init();

		MongoClient client = instance.getClient();
		assertNotNull(client);
		assertTrue("localhost", client.getConnectPoint().startsWith("localhost"));

		DB db = instance.getDb();
		assertNotNull(db);
		assertEquals("testdb", db.getName());
	}

}
