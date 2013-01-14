package com.dredom;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

/**
 * CRUD for "store" collection.
 * @author  Andre Untiedt
 *
 */
public class StoreManagerTest {

	static final String CONNECTION_STRING = "mongodb://localhost/test";

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
		final double latitude = -33.857639;
		final double longitude = 151.214706;

		StoreManager manager = new StoreManager();
		manager.setMongoManager(mongoManager);

		// cleanup
		manager.delete(id);

		StoreCollection in = new StoreCollection();
		in.setId(id);
		in.setName(name);
		Geo geo = new Geo();
		geo.setLatitude(latitude);
		geo.setLongitude(longitude);
		in.setGeo(geo);

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

    @Test
    public void findByName() throws Exception {
        final String id = "DD";
        final String name = "Cochise";
        final double latitude = -33.857644;
        final double longitude = 151.214777;

        StoreManager manager = new StoreManager();
        manager.setMongoManager(mongoManager);

        // cleanup
        manager.delete(id);

        StoreCollection in = new StoreCollection();
        in.setId(id);
        in.setName(name);
        Geo geo = new Geo();
        geo.setLatitude(latitude);
        geo.setLongitude(longitude);
        in.setGeo(geo);
        in.setName_lc(name.toLowerCase());
        in.setTag(new String[] { "buy", "sell" });

        manager.add(in);

        StoreCollection result = manager.findByName(name);
        assertNotNull(result);
//        assertEquals(null, result.getId());
        assertEquals(name, result.getName());
        assertNotNull(result.getGeo());
        assertNotNull(result.getTag());

        StoreCollection result2 = manager.findByName(name.toLowerCase());
        assertNotNull(result2);

    }

}
