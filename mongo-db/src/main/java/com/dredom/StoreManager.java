/**
 *
 */
package com.dredom;

import java.util.HashMap;
import java.util.Map;

import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

/**
 * @author auntiedt
 *
 */
public class StoreManager {
	private static final String COLLECTION = "store";
	private MongoManager mongoManager;

	public StoreCollection get(String id) {
		JacksonDBCollection<StoreCollection, String> coll = getCollection();
		StoreCollection obj = coll.findOneById(id);
		return obj;
	}

	public void add(StoreCollection store) throws Exception {
		JacksonDBCollection<StoreCollection, String> coll = getCollection();

		WriteResult<StoreCollection, String> result = coll.insert(store);
		if (result.getError() != null) {
			System.err.format("insert %s[%s] failed: %s\n", COLLECTION, store.getId(), result.getError());
			throw new Exception(result.getError());
		}
	}

	public boolean delete(String id) {
		JacksonDBCollection<StoreCollection, String> coll = getCollection();
		WriteResult<StoreCollection, String> result = coll.removeById(id);
		if (result.getError() != null) {
			System.err.format("delete %s[%s] failed: %s\n", COLLECTION, id, result.getError());
			return false;
		}
		return true;
	}

	public StoreCollection findByName(String name) {
        JacksonDBCollection<StoreCollection, String> coll = getCollection();
        DBObject ref = QueryBuilder.start("name_lc")
               .is(name.toLowerCase())
               .get();
        Map<String, Integer> returnFields = new HashMap<String, Integer>();
        returnFields.put("name", 1);
        returnFields.put("geo", 1);
        returnFields.put("tag", 1);
        DBObject fields = new BasicDBObject(returnFields);
        StoreCollection obj = coll.findOne(ref, fields);
        return obj;
	}

	private JacksonDBCollection<StoreCollection, String> getCollection() {
		DB db = mongoManager.getDb();
		DBCollection coll = db.getCollection(COLLECTION);
		return JacksonDBCollection.wrap(coll, StoreCollection.class, String.class);

	}

	public void setMongoManager(MongoManager mongoManager) {
		this.mongoManager = mongoManager;
	}

}
