/**
 *
 */
package com.dredom;

import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;

import com.mongodb.DB;
import com.mongodb.DBCollection;

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

	private JacksonDBCollection<StoreCollection, String> getCollection() {
		DB db = mongoManager.getDb();
		DBCollection coll = db.getCollection(COLLECTION);
		return JacksonDBCollection.wrap(coll, StoreCollection.class, String.class);

	}

	public void setMongoManager(MongoManager mongoManager) {
		this.mongoManager = mongoManager;
	}

}
