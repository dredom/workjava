package com.dredom.lang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AutoClosing {

    static String filePath = "src/test/resources/test.txt";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        AutoClosing instance = new AutoClosing();
        instance.readFile(filePath);
    }

    public void readFile(String path) throws Exception {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        try ( BufferedReader breader = new BufferedReader(reader) ) {
            String line;
            while ( (line = breader.readLine()) != null ) {
                System.out.println(line);
            }
        }
        // reader.close();
    }

}
