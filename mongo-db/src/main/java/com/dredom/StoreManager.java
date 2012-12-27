/**
 *
 */
package com.dredom;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * @author auntiedt
 *
 */
public class StoreManager {
	private static final String COLLECTION = "store";
	private MongoManager mongoManager;

	public StoreCollection get(String id) {
		DBCollection coll = getCollection();
		BasicDBObject query = new BasicDBObject("_id", id);
		DBObject obj = coll.findOne(query);
		if (obj == null) {
			return null;
		}
		BasicDBObject result = (BasicDBObject) obj;
		StoreCollection out = new StoreCollection();
		out.setId(result.getString("_id"));
		out.setName(result.getString("name"));
		return out;
	}

	public void add(StoreCollection store) throws Exception {
		DBCollection coll = getCollection();
		BasicDBObject doc = new BasicDBObject(
				"_id", store.getId())
				.append("name", store.getName());
		WriteResult result = coll.insert(doc);
		if (result.getError() != null) {
			System.err.format("insert %s[%s] failed: %s\n", COLLECTION, store.getId(), result.getError());
			throw new Exception(result.getError());
		}
	}

	public boolean delete(String id) {
		DBCollection coll = getCollection();
		BasicDBObject query = new BasicDBObject("_id", id);
		WriteResult result = coll.remove(query);
		if (result.getError() != null) {
			System.err.format("delete %s[%s] failed: %s\n", COLLECTION, id, result.getError());
			return false;
		}
		return true;
	}

	private DBCollection getCollection() {
		DB db = mongoManager.getDb();
		return db.getCollection(COLLECTION);
	}

	public void setMongoManager(MongoManager mongoManager) {
		this.mongoManager = mongoManager;
	}

}
