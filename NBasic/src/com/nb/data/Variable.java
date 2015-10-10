package com.nb.data;

public class Variable {
	DataType dataType = DataType.NULL;
	String name = "";
	String value = "";
	
	public Variable(String name, String value, DataType dataType) {
		this.dataType = dataType;
		this.name = name;
		this.value = value;
	}
	
	public DataType getDataType() {
		return dataType;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
}
