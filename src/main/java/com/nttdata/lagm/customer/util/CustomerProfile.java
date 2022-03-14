package com.nttdata.lagm.customer.util;

public enum CustomerProfile {
	VIP(1, "VIP Profile"), PYME(2, "PYME Profile");

	private final Integer value;
	private final String reasonPhrase;

	CustomerProfile(Integer value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public Integer getValue() {
		return value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}
}
