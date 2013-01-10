/**
 *
 */
package com.dredom;

import java.util.ArrayList;
import java.util.List;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

/**
 * Geospatial handling.
 *
 * @author auntiedt
 * @see    <a href="http://docs.mongodb.org/manual/applications/geospatial-indexes/">docs.mongodb.org/manual/applications/geospatial-indexes</a>
 */
public class GeospatManager {
	private static final String COLLECTION = "geospat";
	static final double KM_IN_DEGREE = 111.12;
	private MongoManager mongoManager;

	public GeospatialCollection get(String id) {
		JacksonDBCollection<GeospatialCollection, String> coll = getCollection();
		GeospatialCollection obj = coll.findOneById(id);
		return obj;
	}

	public void add(GeospatialCollection store) throws Exception {
		JacksonDBCollection<GeospatialCollection, String> coll = getCollection();

		WriteResult<GeospatialCollection, String> result = coll.insert(store);
		if (result.getError() != null) {
			System.err.format("insert %s[%s] failed: %s\n", COLLECTION, store.getId(), result.getError());
			throw new Exception(result.getError());
		}
	}

	public boolean delete(String id) {
		JacksonDBCollection<GeospatialCollection, String> coll = getCollection();
		WriteResult<GeospatialCollection, String> result = coll.removeById(id);
		if (result.getError() != null) {
			System.err.format("delete %s[%s] failed: %s\n", COLLECTION, id, result.getError());
			return false;
		}
		return true;
	}

	public void ensure2dIndex() {
	    DBObject keys = BasicDBObjectBuilder.start()
	            .add("longLat", "2d")
	            .get();
        DB db = mongoManager.getDb();
        DBCollection coll = db.getCollection(COLLECTION);
        coll.ensureIndex(keys);
	}

	/**
	 * The radius of the Earth is approximately 3963.192 miles or 6378.137 kilometers.
	 * Convert distance by 111.12 (one degree is approximately 111.12 kilometers) when using km,
	 * or leave distance as it is on using degree.
	 * $near assumes an idealized model of a flat earth,
	 * meaning that an arcdegree of latitude (y) and longitude (x) represent the same distance everywhere.
	 * So you have to convert the radius by 111 or 69 to get the results.
	 * But $nearSphere you need to convert the radius by (6371 km or 3959 miles) to get it work...
	 * @param longitude
	 * @param latitude
	 * @param maxDistance in degrees
	 * @return
	 */
	public GeospatialCollection[] geoFind(double longitude, double latitude, double maxDistance) {
        JacksonDBCollection<GeospatialCollection, String> coll = getCollection();
        DBObject ref = QueryBuilder.start("longLat")
              .near(longitude, latitude, maxDistance / KM_IN_DEGREE)
//              .nearSphere(longitude, latitude, maxDistance / KM_IN_DEGREE)
              .get();
        DBCursor<GeospatialCollection> cursor = coll.find(ref);
//        if (cursor.size() == 0) {
//            return null;
//        }
//        return cursor.toArray().toArray(new GeospatialCollection[0]);
        List<GeospatialCollection> list = new ArrayList<GeospatialCollection>();
        while (cursor.hasNext()) {
            GeospatialCollection type = cursor.next();
            list.add(type);
        }
        if (list.size() == 0) {
            return null;
        }
        return list.toArray(new GeospatialCollection[0]);
	}

    public DBObject[] geoFindRaw(double longitude, double latitude, double maxDistance) {
        DB db = mongoManager.getDb();
        DBCollection coll = db.getCollection(COLLECTION);
        DBObject ref = QueryBuilder.start("longLat")
              .near(longitude, latitude, maxDistance)
              .get();
        com.mongodb.DBCursor cursor = coll.find(ref);
//	        if (cursor.size() == 0) {
//	            return null;
//	        }
//	        return cursor.toArray().toArray(new GeospatialCollection[0]);
        List<DBObject> list = new ArrayList<DBObject>();
        while (cursor.hasNext()) {
            DBObject type = cursor.next();
            list.add(type);
        }
        if (list.size() == 0) {
            return null;
        }
        return list.toArray(new DBObject[0]);
    }

	private JacksonDBCollection<GeospatialCollection, String> getCollection() {
		DB db = mongoManager.getDb();
		DBCollection coll = db.getCollection(COLLECTION);
		return JacksonDBCollection.wrap(coll, GeospatialCollection.class, String.class);
	}

	public void setMongoManager(MongoManager mongoManager) {
		this.mongoManager = mongoManager;
	}

}
