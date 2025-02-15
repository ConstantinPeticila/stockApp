package com.investing.domain;

public enum Gender {
	MALE("Mr."), FEMALE("Ms.");

	private final String greeting;

	Gender(String greeting) {
		this.greeting = greeting;
	}

	public String getGreeting() {
		return greeting;
	}
}
