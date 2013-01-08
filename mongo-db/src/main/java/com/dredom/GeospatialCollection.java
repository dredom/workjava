package com.dredom;

import javax.persistence.Id;

public class GeospatialCollection {

    @Id
    String id;
    String name;
    double[] longLat;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double[] getLongLat() {
        return longLat;
    }
    public void setLongLat(double[] longLat) {
        this.longLat = longLat;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("[").append(id).append(", ").append(name).append("]")
            .toString();
    }
}
