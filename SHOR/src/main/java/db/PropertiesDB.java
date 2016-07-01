package db;

import java.io.*;
import java.util.Properties;

public class PropertiesDB {

    public void Put(String namedb,String adressdb,String username,String password) {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("C:\\Users\\Петро\\IdeaProjects\\SHOR\\config.properties");

            // set the properties value
            prop.setProperty(namedb, adressdb);
            prop.setProperty(username, username);
            prop.setProperty(password, password);

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public String Get(String key) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("C:\\Users\\Петро\\IdeaProjects\\SHOR\\config.properties");
            prop.load(input);



        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop.getProperty(key);
    }

}




