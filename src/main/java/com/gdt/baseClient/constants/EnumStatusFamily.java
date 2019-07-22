package com.gdt.baseClient.constants;

//Copied from Response.Status.Family.html 
public enum EnumStatusFamily {
	CLIENT_ERROR(true), INFORMATIONAL(false), OTHER(true), REDIRECTION(false), SERVER_ERROR(true), SUCCESFUL(false);

	private boolean errorCode;

	EnumStatusFamily(boolean isAnErrorCode) {
		this.errorCode = isAnErrorCode;
	}
	
	public boolean isAnErrorCode() {
		return this.errorCode;
	}

	public static EnumStatusFamily getStatusFamily(int httpStatusCode) {
		switch (httpStatusCode / 100) {
		case 1:
			return EnumStatusFamily.INFORMATIONAL;
		case 2:
			return EnumStatusFamily.SUCCESFUL;
		case 3:
			return EnumStatusFamily.REDIRECTION;
		case 4:
			return EnumStatusFamily.CLIENT_ERROR;
		case 5:
			return EnumStatusFamily.SERVER_ERROR;
		default:
			return EnumStatusFamily.OTHER;
		}
	}

	public static boolean isAnErrorCode(int httpStatusCode) {
		return EnumStatusFamily.getStatusFamily(httpStatusCode).errorCode;
	}

}
