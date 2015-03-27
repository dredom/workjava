package com.dredom.json;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, String> map = new HashMap<String, String>();
		map.put("en", "English");
		map.put("es", "Spanish");
		subcat.setMap(map);
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
	@Test
	public void jsonFromObject() throws Exception {
		Category cat = new Category();
		cat.setId(1);
		cat.setName("Jason");

		Object obj = cat;
		String result = service.toJson(obj);
		assertNotNull(result);
		assertTrue("empty", result.length() > 0);
		System.out.println(result);
	}

	@Test
	public void jsonStringToObject() throws Exception {
	    String filePath = "src/test/resources/category_1.json";
        File file = new File(filePath);

        Category obj = service.toObject(file, Category.class);
        System.out.println(obj);
	}
}
