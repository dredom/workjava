package com.dredom;

import com.mongodb.util.JSON;

public class Jsonny {
    private static final String COLLECTION = "zoot";
    private MongoManager mongoManager;

    public Object parse(String jsonable) {
        Object parsed = JSON.parse(jsonable);
        return parsed;
    }
}
