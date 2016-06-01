package br.com.paulo.animais.properties;

import java.util.ResourceBundle;

public class PropertiesBundle {
    
    private static final String baseName = "br.com.paulo.animais.properties.resource";
    
    public static String getProperty(String key) {
        return ResourceBundle.getBundle(baseName).getString(key);
    } 
    
}