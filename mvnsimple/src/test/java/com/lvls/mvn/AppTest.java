package com.lvls.mvn;

import static junit.framework.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	Properties props;
	
	@Before
	public void setup() throws Exception {
		InputStream is = getClass().getClassLoader().getResourceAsStream("placeholder.properties");

//		File file = new File("placeholder.properties");
//		InputStream inStream;
//		try {
//			inStream = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			System.out.println("Not found: " + file.getAbsolutePath());
//			throw e;
//		}
		props = new Properties();
		props.load(is);
	}
	
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void testApp() {
		
		assertTrue(true);
	}
}
