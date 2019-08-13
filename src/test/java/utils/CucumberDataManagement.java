package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CucumberDataManagement {


    public static Boolean shouldSetIt(String value) {
        Boolean notSetValue = true;
        if (value.equalsIgnoreCase(CucumberConstants.NOT_SET)) {
            notSetValue = false;
        }
        return notSetValue;
    }

    public static String getValidListString(String key, String valueToSet, Integer stringLimitLength, Integer numElements) {
        String fieldValue;
        String listOfStrings = "";
        switch (valueToSet) {
            case CucumberConstants.NULL_VALUE:
                fieldValue = "";
                break;
            case CucumberConstants.NOT_ALPHA_NUMERIC:
                fieldValue = "|@#$%&/()";
                break;
            case CucumberConstants.BELOW_UPPER_LIMIT:
                fieldValue = RandomStringUtils.randomAlphanumeric(stringLimitLength - 1);
                break;
            case CucumberConstants.EQUAL_UPPER_LIMIT:
                fieldValue = RandomStringUtils.randomAlphanumeric(stringLimitLength);
                break;
            case CucumberConstants.ABOVE_UPPER_LIMIT:
                fieldValue = RandomStringUtils.randomAlphanumeric(stringLimitLength + 1);
                break;
            default:
                fieldValue = valueToSet;
        }

        listOfStrings = listOfStrings + fieldValue;

        for (int i = 1; i < numElements; i++) {
            listOfStrings = listOfStrings + "&" + key + "=" + fieldValue;
        }


        return listOfStrings;
    }


    public static String getValidString(String cucumberAnnotation, Integer stringLimitMinLength, Integer stringLimitMaxLength) {
        String textForTest = cucumberAnnotation;
        switch (cucumberAnnotation) {
            case CucumberConstants.NULL_VALUE:
                textForTest = "";
                break;
            case CucumberConstants.NOT_ALPHA_NUMERIC:
                textForTest = RandomStringUtils.random(stringLimitMaxLength,35,38,false,false);
                break;
            case CucumberConstants.BELOW_UPPER_LIMIT:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitMaxLength - 1);
                break;
            case CucumberConstants.EQUAL_UPPER_LIMIT:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitMaxLength);
                break;
            case CucumberConstants.ABOVE_UPPER_LIMIT:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitMaxLength + 1);
                break;
            case CucumberConstants.BELOW_LOWER_LIMIT:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitMinLength - 1);
                break;
            case CucumberConstants.EQUAL_LOWER_LIMIT:
            case CucumberConstants.RANDOM_VALUE:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitMinLength);
                break;
            case CucumberConstants.ABOVE_LOWER_LIMIT:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitMinLength + 1);
                break;
            default:
                textForTest = cucumberAnnotation;
        }
        return textForTest;
    }

    public static Boolean getValidBoolean(String value) {
        Boolean validValue = null;
        if (!value.equalsIgnoreCase(CucumberConstants.NULL_VALUE)) {
            validValue = Boolean.parseBoolean(value);
        }
        return validValue;
    }

    public static Integer getValidInteger(String textToSearch, Integer integerLimit) {
        Integer validValue = null;

        switch (textToSearch) {
            case CucumberConstants.NOT_SET:
                validValue = null;
                break;
            case CucumberConstants.BELOW_UPPER_LIMIT:
                validValue = integerLimit - 1;
                break;
            case CucumberConstants.ABOVE_UPPER_LIMIT:
                validValue = integerLimit + 1;
                break;
            case CucumberConstants.EQUAL_UPPER_LIMIT:
                validValue = integerLimit;
                break;
            default:
                validValue = Integer.parseInt(textToSearch);
        }

        return validValue;
    }

}
