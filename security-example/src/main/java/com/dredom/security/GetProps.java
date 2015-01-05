package com.dredom.security;

/**
 * Test reading properties w & w/out security manager.
 *
 * java -Djava.security.manager -cp target/classes/ com.dredom.security.GetProps
 *
 */
public class GetProps {
    public static void main(String[] args) {

        String s;

        try {

            System.out.println("About to get os.name property value");

            s = System.getProperty("os.name", "not specified");
            System.out.println("  The name of your operating system is: " + s);

            System.out.println("About to get java.version property value");

            s = System.getProperty("java.version", "not specified");
            System.out.println("  The version of the JVM you are running is: " + s);

            System.out.println("About to get user.home property value");

            s = System.getProperty("user.home", "not specified");
            System.out.println("  Your user home directory is: " + s);

            System.out.println("About to get java.home property value");

            s = System.getProperty("java.home", "not specified");
            System.out.println("  Your JRE installation directory is: " + s);

            s = System.getProperty("java.ext.dirs", "not specified");
            System.out.println("  Your java.ext.dirs directory is: " + s);


        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }

    }

}
