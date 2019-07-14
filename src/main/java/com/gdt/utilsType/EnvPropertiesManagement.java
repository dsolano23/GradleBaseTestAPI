package com.gdt.utilsType;
/**
 * @author David Solano
 * 2019 / 07 / 14
 */


import com.gdt.enviroment.EnvironmentConstantsFiles;
import com.gdt.enviroment.EnvironmentConstantsNames;
import com.gdt.exception.NotFoundResourceException;

import java.io.*;
import java.util.Properties;

public class EnvPropertiesManagement {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EnvPropertiesManagement.class);
    /**
     * properties path
     */
    private static String PropertiesDir;

    static {
        try {
            PropertiesDir = new File( "." ).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Singleton properties object
     */
    private static Properties props = null;



    /**
     * Always return the same instance (singleton)
     * @return
     */
    public static Properties getEnvProps() throws NotFoundResourceException {
        if(props == null){
            props = loadEnvironmentProperties();
        }
        return props;
    }


    public static Object getProperty(String propName, Class propertyClass){
        Object oValue=props.getProperty(propName);

        if(oValue.getClass()== propertyClass){
            return oValue;
        }else{
            //try to cast
            return oValue;
        }
    }

    /**
     * Fill data from file into properties object
     * @return
     */
    public static Properties loadEnvironmentProperties() throws NotFoundResourceException {

        InputStream input = null;
        String path = EnvironmentConstantsFiles.PATH_ENVIRONMENT_PROPERTIES;
        String fileName = System.getProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE);

        String enviromentProperties = PropertiesDir + path + fileName + ".properties";
        logger.debug("enviromentProperties: " + enviromentProperties);
        try {

            boolean exist = new File(enviromentProperties).exists();
            if(exist) {
                input = new FileInputStream(enviromentProperties);
                // load a properties file
                if(EnvPropertiesManagement.props == null){
                    EnvPropertiesManagement.props = new Properties();
                }
                EnvPropertiesManagement.props.load(input);
            }else {
                throw new NotFoundResourceException("The file "+ enviromentProperties + " does not exists");
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NotFoundResourceException(String.format("%s %s", "Error on access to"+  enviromentProperties +" does not exists ", ex.getMessage() ));
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }


    /**
     * Persist data object to file
     *
     */
    public static void saveProps(){
        OutputStream output = null;
        String path = EnvironmentConstantsFiles.PATH_ENVIRONMENT_PROPERTIES;
        String fileName = "defaultEnvironment";
        String enviromentProperties = PropertiesDir + path + fileName + ".properties";
        try {
            output = new FileOutputStream(enviromentProperties);
            // Save properties to project root folder

            EnvPropertiesManagement.props.store(output, null);
        }

        catch (IOException io) {
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
}
