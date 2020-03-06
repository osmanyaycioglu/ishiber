package com.isbank.rest.converter;

import java.io.Serializable;

public class MyType implements Serializable {
	private int high;
	private int low;

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}
	
	
}
