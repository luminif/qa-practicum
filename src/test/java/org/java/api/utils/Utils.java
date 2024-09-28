package org.java.api.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class Utils {
    public static String getBaseUrl() {
        try (InputStream is = Utils.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties props = new Properties();
            props.load(is);
            return props.getProperty("api-base-url");
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти application.properties", e);
        }
    }
}
