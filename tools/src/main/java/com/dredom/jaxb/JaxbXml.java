package com.dredom.jaxb;

import java.io.CharArrayWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbXml {

    public String toXml(Object cat) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(cat.getClass());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        CharArrayWriter writer = new CharArrayWriter();
        marshaller.marshal(cat, writer);
        return writer.toString();
    }
}
