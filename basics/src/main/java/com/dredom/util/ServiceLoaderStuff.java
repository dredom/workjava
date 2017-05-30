package com.dredom.util;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * See META-INF/services
 *
 * @author andre
 *
 */
public class ServiceLoaderStuff {

    public static void main(String[] args) {
        ServiceLoader<MyDoit> loader = ServiceLoader.load(MyDoit.class);
        Iterator<MyDoit> iter = loader.iterator();
        while (iter.hasNext()) {
            MyDoit myDoit = iter.next();
            System.out.println(myDoit.toString());
        }

    }

}
