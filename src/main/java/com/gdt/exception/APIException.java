package com.gdt.exception;

import com.gdt.exception.beans.ErrorDto;
import com.gdt.exception.beans.ObjectErrorBean;
import com.gdt.exception.constants.ErrorsEnum;

/**
 * Main exception class for controlled errors on API
 * @author David Solano
 * 2019 / 07 / 14
 */
public class APIException extends Exception{
	/**
	 * Error Code
	 */
	private Integer code;
	/**
	 * Info related
	 * TODO:Maybe could be the PO file traducction
	 */
	private String detail;
	/**
	 * Error based on Rest link information
	 */
	private String type;

	private ErrorDto error;
	private ErrorDto[] errors;
	/**
	 * Constructor based on Error Enum main error catalog
	 * @param error
	 */
	public APIException(ErrorDto error){
		super("Error:"+ error.getCode() +" Cause: "+error.getDetail());
		this.type = error.getType();
		this.code= error.getCode();
		this.detail =error.getDetail();
		this.error = error;

	}

	public APIException(ObjectErrorBean error){
		super();
		errors = error.getObjErrors();
	}

	public APIException(ErrorDto[] errors){
		this.errors = errors;
	}

	/**
	 * Constructor based on Error Enum main error catalog
	 * @param error
	 */
	public APIException(ErrorsEnum error){
		super("Error:"+ error.getCode() +" Cause: "+error.getDetail());
		this.code= error.getCode();
		this.detail =error.getDetail();
		this.type = error.getType();
	}

	/**
	 * Constructor based on Error Enum main error catalog
	 * @param error
	 */
	public APIException(ErrorsEnum error, Object moreInfo){
		super("Error:"+ error.getCode() +" Cause: "+error.getDetail() + ", extendedInfo: " +moreInfo);
		this.code= error.getCode();
		this.detail =error.getDetail() +moreInfo;
		this.type = error.getType();
	}

	/**
	 * From general Uncontrolled exception
	 * @param e
	 */
	public APIException(Exception e) {
		super(e);
		this.code=500;
		this.detail = e.getMessage();
	}


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ErrorDto getError() {
		return error;
	}

	public void setError(ErrorDto error) {
		this.error = error;
	}

	public ErrorDto[] getErrors() {
		return errors;
	}
}
