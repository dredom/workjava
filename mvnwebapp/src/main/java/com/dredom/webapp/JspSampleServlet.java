/**
 *
 */
package com.dredom.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Use a JSP page in the response.
 *
 * @author auntiedt
 *
 */
public class JspSampleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        String message = "How now brown JSP.";
        request.setAttribute("message", message);
	    request.getRequestDispatcher("jspsample.jsp").forward(request, response);
	}
}
