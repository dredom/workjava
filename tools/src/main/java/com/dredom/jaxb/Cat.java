package com.dredom.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cat {

    private int id;
    private String name;
    private String color;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

}
