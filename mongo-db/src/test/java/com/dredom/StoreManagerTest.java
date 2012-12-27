package com.dredom;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

/**
 * CRUD for "store" collection.
 * @author auntiedt
 *
 */
public class StoreManagerTest {

	static final String CONNECTION_STRING = "mongodb://localhost/testdb";

	MongoManager mongoManager;

	@Before
	public void before() throws Exception {
		mongoManager = new MongoManager();
		mongoManager.setConnectionString(CONNECTION_STRING);
		mongoManager.init();
	}

	@Test
	public void get() throws Exception {
		final String id = "AA";

		StoreManager manager = new StoreManager();
		manager.setMongoManager(mongoManager);

		StoreCollection result = manager.get(id);

		assertNull(result);

	}

	@Test
	public void add() throws Exception {
		final String id = "BB";
		final String name = "Geronimo";

		StoreManager manager = new StoreManager();
		manager.setMongoManager(mongoManager);

		// cleanup
		manager.delete(id);

		StoreCollection in = new StoreCollection();
		in.setId(id);
		in.setName(name);

		manager.add(in);

		StoreCollection result = manager.get(id);
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals(name, result.getName());

	}

	@Test
	public void delete() throws Exception {
		final String id = "CC";
		final String name = "Sitting Bull";

		StoreManager manager = new StoreManager();
		manager.setMongoManager(mongoManager);

		// cleanup
		manager.delete(id);

		StoreCollection in = new StoreCollection();
		in.setId(id);
		in.setName(name);

		manager.add(in);

		StoreCollection result = manager.get(id);
		assertNotNull(result);

		// Delete
		manager.delete(id);

		result = manager.get(id);
		assertNull(result);
	}

}
