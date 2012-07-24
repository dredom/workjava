package com.lvls.freemarker;

import org.junit.Test;

public class FreeMarkitTest {

	@Test
	public void doit() throws Exception {
		FreeMarkit engine = new FreeMarkit();
		engine.doit();
	}
	@Test
	public void doitString() throws Exception {
		FreeMarkit engine = new FreeMarkit();
		engine.doitWithString();
	}
}
