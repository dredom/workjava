package com.dredom.jaxb;

import org.junit.Test;

public class JaxbXmlTest {

    @Test
    public void toXml() throws Exception {
        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Felicity");
        cat.setColor("yellow");
        JaxbXml service = new JaxbXml();
        String result = service.toXml(cat);
        System.out.println(result);
    }
}
