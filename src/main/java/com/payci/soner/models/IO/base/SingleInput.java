package com.payci.soner.models.IO.base;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;


@JacksonXmlRootElement(localName = "p")
public class SingleInput {
	
	public SingleInput() {}
	
	public SingleInput(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	@JacksonXmlProperty(isAttribute = true, localName = "n")
	private String key;
	
	@JacksonXmlText
	private Object value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
