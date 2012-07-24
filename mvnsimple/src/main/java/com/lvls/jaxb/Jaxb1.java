package com.lvls.jaxb;

import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.lvls.jaxb.foo.Hello;

public class Jaxb1 {

	public void doit() throws JAXBException {
//		JAXBContext jc = JAXBContext.newInstance("com.lvls.jaxb.foo");
		JAXBContext jc = JAXBContext.newInstance(com.lvls.jaxb.foo.Hello.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		OutputStream os = System.out;

		Hello element = new Hello();
		element.setId(1);
		element.setGreeting("Hello");

		m.marshal(element, os);

	}
}
