package com.dredom;

import static org.junit.Assert.*;

import java.util.Arrays;


import org.junit.Before;
import org.junit.Test;

import com.mongodb.DBObject;

/**
 * CRUD for "geospat" collection.
 * @author auntiedt
 *
 */
public class GeospatManagerTest {

	static final String CONNECTION_STRING = "mongodb://localhost/testdb";

	MongoManager mongoManager;


	// ----------------------

	static final double KM_IN_DEGREE = 111.12;
	static String[][] geospats = { // id, name, long, lat
	    { "gA", "Paris", "2.341200", "48.856930" },
	    { "gB", "London", "-0.127140", "51.506321" },
	    { "gC", "Cape Town", "18.421961", "-33.919060" },
	    { "gD", "New York", "-74.007118", "40.714550" },
	};

	static double[][] distances = { // lat, long, distance in Km, count of rows expected
	    { 2.341200, 48.856930, 100, 1 },
	    { 2.341200, 48.856930, 400, 2 },
	    { 2.341200, 48.856930, 6000, 3 },
	    { 2.341200, 48.856930, 9000, 4 },
	};

	@Before
	public void before() throws Exception {
		mongoManager = new MongoManager();
		mongoManager.setConnectionString(CONNECTION_STRING);
		mongoManager.init();
	}

	/**
	 * FAILS on the locations included in the distances. ????
	 * @throws Exception
	 */
    @Test
    public void geoFind() throws Exception {
        GeospatManager manager = new GeospatManager();
        manager.setMongoManager(mongoManager);

        // Setup
        setupGeoTestsData(manager, geospats);
        // Geo index
        manager.ensure2dIndex();


        for (int i = 0; i < distances.length; i++) {
            double distance[] = distances[i];
            GeospatialCollection[] result = manager.geoFind(distance[0], distance[1], distance[2]);
            System.out.format(" %d %s \n", i, Arrays.deepToString(result));
            assertNotNull(result);
            assertEquals(i + " size", distance[3], result.length, 0.0001);
        }
    }

    private void setupGeoTestsData(GeospatManager manager, String[][] testdata) throws Exception {
        for (int i = 0; i < testdata.length; i++) {
            String id = testdata[i][0];
            manager.delete(id);
            manager.add(toGeospat(testdata[i]));
        }
    }

    private GeospatialCollection toGeospat(String[] fields) {
        GeospatialCollection out = new GeospatialCollection();
        out.setId(fields[0]);
        out.setName(fields[1]);
        out.setLongLat(new double[] {
                Double.parseDouble(fields[2]),
                Double.parseDouble(fields[3]),
        });
        return out;
    }

    @Test
    public void get() throws Exception {
        final String id = "AA";

        GeospatManager manager = new GeospatManager();
        manager.setMongoManager(mongoManager);

        GeospatialCollection result = manager.get(id);

        assertNull(result);

    }

	@Test
	public void add() throws Exception {
		final String id = "BB";
		final String name = "Geronimo";
		final double latitude = -33.857639;
		final double longitude = 151.214706;

		GeospatManager manager = new GeospatManager();
		manager.setMongoManager(mongoManager);

		// cleanup
		manager.delete(id);

		GeospatialCollection in = new GeospatialCollection();
		in.setId(id);
		in.setName(name);
		in.setLongLat(new double[] {longitude, latitude});

		manager.add(in);

		GeospatialCollection result = manager.get(id);
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals(name, result.getName());

	}

	@Test
	public void delete() throws Exception {
		final String id = "CC";
		final String name = "Sitting Bull";

		GeospatManager manager = new GeospatManager();
		manager.setMongoManager(mongoManager);

		// cleanup
		manager.delete(id);

		GeospatialCollection in = new GeospatialCollection();
		in.setId(id);
		in.setName(name);

		manager.add(in);

		GeospatialCollection result = manager.get(id);
		assertNotNull(result);

		// Delete
		manager.delete(id);

		result = manager.get(id);
		assertNull(result);
	}

}
