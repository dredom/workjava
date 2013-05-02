package com.dredom.osgi.service.impl;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dredom.osgi.service.api.HelloService;

/**
 * Example Service - to see if we can reference it in the JSP on CQ5 (AEM).
 * <pre>
 *   <% HelloService service = sling.getService(com.dredom.osgi.service.api.HelloService.class);
 *      pageContext.setAttribute("service", service);
 *   %>
 *      <%= service.getGreeting() %>
 *      <p> jstl: ${service.greeting} :jstl </p>
 *      ${sling.request}
 * </pre>
 */
@Component(metatype = false)
// @Service signifies it will be registered as a service in OSGi - fully qualified HelloService name.
@Service(value = HelloService.class)
public class HelloServiceImpl implements HelloService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private final String greeting = "The time is Nau - from Service Component";

	@Override
	public void greet() {
		log.info(greeting);
		System.out.println(greeting);
	}

	@Activate
	protected void activate() {
		log.info("service activated");
	}

	@Deactivate
	protected void deactivate() {
		log.info("service deactivated");
	}

	@Override
	public String getGreeting() {
		return greeting;
	}

}
