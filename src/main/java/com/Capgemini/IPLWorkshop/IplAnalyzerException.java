package com.Capgemini.IPLWorkshop;

public class IplAnalyzerException extends Exception {
	
	enum ExceptionType{
		INCORRECT_PATH;
	}
	
	ExceptionType type;
	
	public IplAnalyzerException(String msg, ExceptionType type) {
		super(msg);
		this.type = type;
	}

}
