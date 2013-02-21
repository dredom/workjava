package com.dredom.lang;

public class Constructoring {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String name = "Jonas";
        // No system property
        Constructoring instance1 = new Constructoring(name);
        System.out.println(instance1.getName());

        // With system property
        System.setProperty(SYSTEM_PROPERTY_KEY, "Friedrich");
        Constructoring instance2 = new Constructoring(name);
        System.out.println(instance2.getName());


    }

    static final String SYSTEM_PROPERTY_KEY = "com.dredom.lang.Constructoring.name";
    private String name;

    /**
     * @param name
     */
    public Constructoring(String name) {
        super();

        String sysName = System.getProperty(SYSTEM_PROPERTY_KEY);
        if (sysName != null) {
            System.out.println("Using system property");
            this.name = sysName;
        } else {
            this.name = name;
        }
    }

    public final String getName() {
        return name;
    }


}
