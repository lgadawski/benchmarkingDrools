package com.gadawski.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    private static final String testToCheck = "hejka!";

    /**
     * Create the test case
     * 
     * @param testName
     *            name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    @org.junit.Test
    public void testWritingToFile() throws MalformedURLException {
        String path = "D:/_development/workspace/example/myCode/my-app/src/main/resources/com/gadawski/app/data/generatedData.dat";
        File file = readHejkaToFile(path);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            assertEquals(testToCheck, reader.readLine());
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param resource
     * @return
     */
    private File readHejkaToFile(String path) {
        File file = null;
        Writer writer = null;
        if (path != null) {
            file = new File(path);
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file), "utf-8"));
                writer.write(testToCheck);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
