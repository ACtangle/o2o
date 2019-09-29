package com.melon.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] encryptPropNames = {"jdbc.master.username", "jdbc.master.password", "jdbc.slave.username", "jdbc.slave.password","redis.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            String decryptValue = DESUtils.getDecryptString(propertyValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    private boolean isEncryptProp(String propertyName) {
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }
}
