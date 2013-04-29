package com.dredom.osgi.servlet;

import java.io.IOException;

import javax.jcr.Repository;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

@SlingServlet(paths = "/bin/company/repo", methods = "GET")
public class MySafeMethodServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = -6304665407524502423L;

	@Reference
	private Repository repository;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
			IOException {
		response.setHeader("Content-Type", "application/json");
//		response.getWriter().print("{\"coming\":\"soon\"}");

		String[] keys = repository.getDescriptorKeys();
		JSONObject jsonObj = new JSONObject();
		for (String key : keys) {
			try {
				jsonObj.put(key, repository.getDescriptor(key));
			} catch(JSONException e) {
				e.printStackTrace();
			}
		}
		response.getWriter().print(jsonObj.toString());
	}

}
