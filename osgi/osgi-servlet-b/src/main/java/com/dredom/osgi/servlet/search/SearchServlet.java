package com.dredom.osgi.servlet.search;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFactory;
import javax.jcr.query.qom.Constraint;
import javax.jcr.query.qom.QueryObjectModel;
import javax.jcr.query.qom.QueryObjectModelFactory;
import javax.jcr.query.qom.Selector;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.day.cq.wcm.api.PageManager;

/**
 * http://localhost:4502/content/geometrixx/en.search.html?q=ceo
 * Where "search" is the 'selector'.
 */
@SlingServlet(resourceTypes = "geometrixx/components/homepage", selectors = "search")
public class SearchServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = -6304665407524502423L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException,
			IOException {
		response.setHeader("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();
		JSONArray resultArray = new JSONArray();

		try {
			// This is the current node that is requested, in case of a page that is in the jcr:content node.
			Node currentNode = request.getResource().adaptTo(Node.class);
			PageManager pageManager = request.getResource().getResourceResolver().adaptTo(PageManager.class);
			// Node that is the cq:Page containing the requested node.
			Node queryRoot = pageManager.getContainingPage(currentNode.getPath()).adaptTo(Node.class);

			String queryTerm = request.getParameter("q");
			if (queryTerm != null) {
				NodeIterator searchResults = performSearch(queryRoot, queryTerm);
				while (searchResults.hasNext()) {
					resultArray.put(searchResults.nextNode().getPath());
				}
				jsonObject.put("results", resultArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.getWriter().print(jsonObject.toString());
	}

	private NodeIterator performSearch(Node queryRoot, String queryTerm) throws RepositoryException {
		// JQOM infrastructure
		QueryObjectModelFactory qf = queryRoot.getSession().getWorkspace().getQueryManager().getQOMFactory();
		ValueFactory vf = queryRoot.getSession().getValueFactory();

		// Selectors
		final String SELECTOR_NAME = "all results";
		final String SELECTOR_NT_UNSTRUCTURED = "nt:unstructured";

		// Select all unstructured nodes
		Selector selector = qf.selector(SELECTOR_NT_UNSTRUCTURED, SELECTOR_NAME);

		// Constraints
		// full text constraint
		Constraint constraint = qf.fullTextSearch(SELECTOR_NAME, null, qf.literal(vf.createValue(queryTerm)));
		// path constraint
		constraint = qf.and(constraint, qf.descendantNode(SELECTOR_NAME, queryRoot.getPath()));

		// Execute
		// execute query without explicit order and columns
		QueryObjectModel query = qf.createQuery(selector, constraint, null, null);

		return query.execute().getNodes();
	}
}
