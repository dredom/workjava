package com.dredom;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Test use of Jackson mapping.
 *
 */
public class PersonTest {
	static final String CONNECTION_STRING = "mongodb://localhost/testdb";
	static final String COLLECTION = "person";

	DB db;

	@Before
	public void before() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI(CONNECTION_STRING);
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		db = mongoClient.getDB("testdb");
	}

	@Test
	public void dostuff() throws Exception {
		final Integer id = 123;
		final String name = "George";
		final Integer age = 27;

		PersonCollection person = new PersonCollection();
		person.setId(id);
		person.setName(name);
		person.setAge(age);
		person.setTimestamp(new Date());

		DBCollection coll = db.getCollection(COLLECTION);
		JacksonDBCollection<PersonCollection, Integer> jcoll = JacksonDBCollection.wrap(coll, PersonCollection.class, Integer.class);

		jcoll.removeById(id);	// cleanup

		WriteResult<PersonCollection, Integer> result = jcoll.insert(person);
		assertNotNull(result);

		PersonCollection saved = result.getSavedObject();
		assertNotNull(saved);
		assertEquals(name, saved.getName());

	}

}
