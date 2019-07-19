package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CucumberDataManagement {


    public static Boolean setValue(String value){
        Boolean notSetValue = true;
        if(value.equalsIgnoreCase(CucumberConstants.NOT_SET)){
            notSetValue = false;
        }
        return notSetValue;
    }



    public static String getValidString(String textToSearch, Integer stringLimitLength){
        String textForTest = textToSearch;
        switch(textToSearch) {
            case CucumberConstants.NULL_VALUE:
                textForTest="";
                break;
            case CucumberConstants.NOT_ALPHA_NUMERIC:
                textForTest="|@#$%&/()";
                break;
            case CucumberConstants.BELOW_UPPER_LIMIT:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitLength-1);
                break;
            case CucumberConstants.ABOVE_UPPER_LIMIT:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitLength+1);
                break;
            case CucumberConstants.EQUAL_UPPER_LIMIT:
                textForTest = RandomStringUtils.randomAlphanumeric(stringLimitLength);
                break;
            default:
                textForTest=textToSearch;
        }
        return textForTest;
    }


    public static Integer getValidInteger(String value){
        Integer validValue = null;
        if (!value.equalsIgnoreCase(CucumberConstants.NOT_SET)){
            validValue = Integer.parseInt(value);
        }
        return validValue;
    }

}
