package com.dredom;

import org.junit.Assert;
import org.junit.Test;

public class JsonnyTest {

    @Test
    public void parseIt() {
        Jsonny service = new Jsonny();
        final String jsonstring = "{ _id: ObjectId('50f70018893b6acd22d6904d'), name: 'Frank', age: 22 }";
        Object obj = service.parse(jsonstring);
        Assert.assertNotNull(obj);
    }
}
