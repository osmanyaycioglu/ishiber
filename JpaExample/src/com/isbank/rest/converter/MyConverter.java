package com.isbank.rest.converter;

import javax.persistence.AttributeConverter;

public class MyConverter implements AttributeConverter<MyType, String> {

	@Override
	public String convertToDatabaseColumn(MyType attribute) {
		return attribute.getHigh() +"," + attribute.getLow();
	}

	@Override
	public MyType convertToEntityAttribute(String dbData) {
		String[] split = dbData.split(",");
		MyType myType = new MyType();
		myType.setHigh(Integer.parseInt(split[0]));
		myType.setLow(Integer.parseInt(split[1]));
		return myType;
	}

}
