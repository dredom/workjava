package com.lvls.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class FreeMarkit {

	public class Beanie {
		public MyBean bean;
		public Date now;
		public MyBean getBean() { return bean; }
		public Date getNow() { return now; }
	}

	public void doit() throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		File dir = new File("src/main/resources/ftl");
		if (!dir.exists()) {
			throw new IOException(dir.getAbsolutePath() + " not found");
		}
		cfg.setDirectoryForTemplateLoading(dir);
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Map<String, Object> root = new HashMap<String, Object>();
		root.put("sample", "Shamis");
		MyBean bean = new MyBean();
		bean.setName("Dedumdee");
		root.put("bean", bean);
		root.put("now", new Date());

		Template template = cfg.getTemplate("example1.ftl");


		Writer out = new OutputStreamWriter(System.out);
		template.process(root, out);
		out.flush();

		Beanie beanie = new Beanie();
		beanie.bean = bean;
		beanie.now = new Date();
		Template template2 = cfg.getTemplate("example1.ftl");
		Writer out2 = new OutputStreamWriter(System.out);
		template2.process(beanie, out2, ObjectWrapper.BEANS_WRAPPER);
		out2.flush();

	}

	public void doitWithString() throws IOException, TemplateException {
		System.out.println();
		final String ftlString = "Yo ${bean.name}!";
		MyBean bean = new MyBean();
		bean.setName("String Thing");
		Beanie beanie = new Beanie();
		beanie.bean = bean;
		beanie.now = new Date();
		final Configuration ftlConfig = new Configuration();

		Template t = new Template(".", new StringReader(ftlString), ftlConfig);
		Writer out2 = new OutputStreamWriter(System.out);
		t.process(beanie, out2, ObjectWrapper.BEANS_WRAPPER);
		out2.flush();

	}
}
