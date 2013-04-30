package com.dredom.osgi.service.impl;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dredom.osgi.service.api.HelloService;

@Component(metatype = false)
@Service(value = HelloService.class)
public class HelloServiceImpl implements HelloService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	public void greet() {
		final String greeting = "Hello Service component";
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

}
