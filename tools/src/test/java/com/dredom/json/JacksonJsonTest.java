package com.dredom.json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Convert Object to JSON string - tests.
 */
public class JacksonJsonTest {

	JacksonJson service = new JacksonJson();

	@Test
	public void jsonToString() throws Exception {
		Category cat = new Category();
		cat.setId(1);
		cat.setName("Jason");

		String result = service.toJson(cat);
		assertNotNull(result);
		assertTrue("empty", result.length() > 0);
		System.out.println(result);
	}
	@Test
	public void jsonObjectsToString() throws Exception {
		int catId = 1;
		final String catName = "Jasonian";
		final String subcatName = "Sub: Gina";
		Category cat = new Category();
		cat.setId(catId);
		cat.setName(catName);
		SubCat subcat = new SubCat();
		subcat.setId(2);
		subcat.setName(subcatName);
		subcat.setCategoryId(catId);
		List<SubCat> list = new ArrayList<SubCat>();
		list.add(subcat);
		cat.setSubCategories(list);

		String result = service.toJson(cat);
		assertNotNull(result);
		assertTrue("empty", result.length() > 0);
		assertTrue("cat name", result.contains(catName));
		assertTrue("subcat name", result.contains(subcatName));
		System.out.println(result);
	}
}
