package com.ttaylorr.uhc.pvpmanager.util;

public enum CountdownType {

	ENTER("spawn"),
	EXIT("exit"),
	RESPAWN("respawn");
	
	private String message;
	
	private CountdownType(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
