package com.lvls;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        for (String arg : args) {
            System.out.printf(" arg: %s \n", arg);
        }
        System.out.printf(" Build: %s %s \n", BuildInfo.version, BuildInfo.dateAndTime);
    }
}
