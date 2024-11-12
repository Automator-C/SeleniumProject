package com.uiweb.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileUtil {
    static Properties props = new Properties();
    public String strFileName;
    public static String strValue;
    final static Logger logger = Logger.getLogger(ConfigFileUtil.class);

    public ConfigFileUtil(String strFileName) {
        this.strFileName = strFileName;
    }



    public static String fileWithDirectory(String directory) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        new File(directory + "/");
        return directory;
    }
}
