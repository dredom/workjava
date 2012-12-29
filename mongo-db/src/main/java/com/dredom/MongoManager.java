/**
 *
 */
package com.dredom;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Handles database connections for a specific database - according to the connection string.
 *
 * <p>Connection string: <pre>
 *  mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
 * </pre>
 * {@code database} required for this implementation.
 *
 * @author auntiedt
 * @see    <a href="http://api.mongodb.org/java/2.10.1/">api.mongodb.org/java</a>
 */
public class MongoManager {

	private String connectionString;

	MongoClientURI mongoClientURI;

	/**
	 * MongoClient is thread safe.
	 */
	private MongoClient mongoClient;

	private volatile Object lock = new Object();

	public void init() throws UnknownHostException {
		if (mongoClient == null) {
			assert connectionString != null;
			synchronized (lock) {
				if (mongoClient == null) {
					mongoClientURI = new MongoClientURI(connectionString);
					assert mongoClientURI.getDatabase() != null;
					MongoClient mongoClient = new MongoClient(mongoClientURI);
					this.mongoClient = mongoClient;
				}
			}
		}
	}

	public MongoClient getClient()  {
		return this.mongoClient;
	}

	public DB getDb() {
		return this.mongoClient.getDB(mongoClientURI.getDatabase());
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

}
