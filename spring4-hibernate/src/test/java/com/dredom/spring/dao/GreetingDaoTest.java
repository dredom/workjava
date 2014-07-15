package com.dredom.spring.dao;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dredom.spring.model.Greeting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-main.xml")
public class GreetingDaoTest {

	@Autowired
	GreetingDao greetingDao;
	@Autowired
	SessionFactory sessionFactory;

	private Session session;

	@Before
	public void setup() {
		session = sessionFactory.openSession();
	}
	@After
	public void teardown() {
		session.close();
	}
	@Test
	@Transactional(readOnly = true)
	public void getGreetingById() throws Exception {
		Integer id = 1;
		Greeting result = greetingDao.load(id);

		Integer rid = result.getId();
		String greeting = result.getGreeting();
		int version = result.getVersion();
		Timestamp created = result.getCreated();
		System.out.printf("id=%s,greeting=%s,version=%s, created=%s \n", rid, greeting, version, created );
	}
}
