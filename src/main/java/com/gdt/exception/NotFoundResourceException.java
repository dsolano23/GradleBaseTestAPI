package com.gdt.exception;
/**
 * @author David Solano
 * 2019 / 07 / 14
 */

public class NotFoundResourceException extends Exception{
    /**
     * Main exception message
     */
    private static final String message = "Not found Resource.";

    /**
     * Default constructor
     */
    public NotFoundResourceException(){
        super();
    }

    /**
     * Argumented constructor
     */
    public NotFoundResourceException(String msg){

        super(String.format("%s %s", message, msg));
    }
}
