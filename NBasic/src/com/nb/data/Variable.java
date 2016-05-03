package com.nb.data;

public class Variable {

	DataType dataType = DataType.NULL;
	String name = "";
	Object value = "";

	public Variable(String name, Object value, DataType dataType) {
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

	public Object getValue() {
		return value;
	}

	public void setValue(Object o) {
		value = o;
	}
}
