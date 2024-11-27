package com.uiweb.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
    public String strFileName;
    public static String strValue;
    final static Logger logger = Logger.getLogger(ConfigManager.class);

    public ConfigManager(String strFileName) {
        this.strFileName = strFileName;
    }


    public static String getProperty(String strKey){
        try {
            File f = new File("src/test/resources/config/config.properties");
            FileInputStream fis = new FileInputStream(f);
            Properties props = new Properties();
            props.load(fis);

            strValue = props.getProperty(strKey);
            logger.info("Property fetched is: "+strValue);
            fis.close();
        }catch(Exception e){
            logger.info("Exception in fetching properties "+e);
        }
        return strValue;
    }



    public static String fileWithDirectory(String directory) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        new File(directory + "/");
        return directory;
    }
}
