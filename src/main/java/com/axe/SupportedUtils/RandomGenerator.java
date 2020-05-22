package com.axe.SupportedUtils;

import org.apache.commons.lang3.RandomStringUtils;

public final class RandomGenerator {


    private static String randomAlphanumeric(Integer length) {
        String alphaNumeric="Govind-"+RandomStringUtils.randomNumeric(length);
        return alphaNumeric;
    }

    
    public static String randomEmailAddress(Integer length) {
        String email = randomAlphanumeric(length) + "@example.com";
        return email.toLowerCase();
    }




}