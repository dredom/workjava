package com.dredom;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class HelloMongoTest {

	static final String HOST = "localhost";
	static final String CONNECTION_STRING = "mongodb://admin:mypwd@localhost/test";

	@Test
	public void getClientHost() throws Exception {
		HelloMongo instance = new HelloMongo();
		instance.setHost(HOST);

		MongoClient client = instance.getClient();
		assertNotNull(client);
	}

	@Test
	public void getClientConnectionString() throws Exception {
		HelloMongo instance = new HelloMongo();
		instance.setConnectionString(CONNECTION_STRING);

		MongoClient client = instance.getClient();
		assertNotNull(client);
	}

	@Test
	public void findTest() throws Exception {
		HelloMongo instance = new HelloMongo();
		instance.setHost(HOST);

		MongoClient client = instance.getClient();
		assertNotNull(client);

		DB testDb = client.getDB("test");
		CommandResult result = testDb.command("find()");
		assertNotNull(result);
		assertTrue(result.size() > 0);
		assertFalse(result.ok());

		Set<String> colls = testDb.getCollectionNames();

		for (String s : colls) {
		    System.out.println(s);
		}

		DBCollection collection = testDb.getCollectionFromString("test");
		assertNotNull(collection);
		assertTrue(0 < collection.count());
	}

	@Test
	public void addAndFind() throws Exception {
		final String personName = "Joe";
		final Integer age = 23;
		Person person = new Person();
		person.setName(personName);
		person.setAge(age);
		String collectionName = "test";
		HelloMongo instance = new HelloMongo();
		instance.setHost(HOST);

		instance.add(person, collectionName);

		Person personGet = instance.find(personName, collectionName);
		assertNotNull(personGet);
		assertEquals(personName, personGet.getName());
		assertEquals(age, personGet.getAge());
	}
}
