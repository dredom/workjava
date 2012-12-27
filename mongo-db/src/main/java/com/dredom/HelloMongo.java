/**
 *
 */
package com.dredom;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Hello, Mongo DB!
 *
 * @author auntiedt
 *
 */
public class HelloMongo {

	String host;
	String connectionString;

	MongoClient mongoClient;

	private volatile Object lock = new Object();

	public MongoClient getClient() throws UnknownHostException {
		if (mongoClient == null) {
			synchronized (lock) {
				if (mongoClient == null) {
					MongoClient mongoClient;
					if (connectionString != null) {
						MongoClientURI mongoClientURI = new MongoClientURI(connectionString);
						mongoClient = new MongoClient(mongoClientURI);
					} else {
						mongoClient = new MongoClient(host);
					}
					this.mongoClient = mongoClient;
				}
			}
		}
		return this.mongoClient;
	}

	public void add(Person person, String collectionName) throws UnknownHostException {
		DB db = getClient().getDB("test");
		DBCollection coll = db.getCollectionFromString(collectionName);
		BasicDBObject doc = new BasicDBObject("name", person.getName())
				.append("age", person.getAge());
		coll.insert(doc);
	}

	public Person find(String name, String collectionName) throws UnknownHostException {
		DB db = getClient().getDB("test");
		DBCollection coll = db.getCollectionFromString(collectionName);
		BasicDBObject query = new BasicDBObject("name", name);
		DBCursor cursor = coll.find(query);
		try {
			while(cursor.hasNext()) {
				DBObject doca = cursor.next();
				BasicDBObject doc = (BasicDBObject) doca;
				Person person = new Person();
				person.setName( doc.getString("name"));
				person.setAge(doc.getInt("age"));
				return person;
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	void setHost(String host) {
		this.host = host;
	}

	void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

}
