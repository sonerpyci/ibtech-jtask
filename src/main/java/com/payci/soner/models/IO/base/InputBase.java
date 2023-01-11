package com.payci.soner.models.IO.base;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.payci.soner.helpers.data.carrier.Bag;

public class InputBase {	
	@JacksonXmlElementWrapper(localName = "p", useWrapping = false)
    private List<SingleInput> params = new ArrayList<SingleInput>();

	public List<SingleInput> getKeyValuePairs() {
		return params;
	}

	public void setKeyValuePairs(List<SingleInput> params) {
		this.params = params;
	}
	
	public Bag toBag() {
		Bag bag = new Bag();
		
		for (SingleInput singleInput : this.getKeyValuePairs()) {
			bag.put(singleInput.getKey(), singleInput.getValue());
		}
		
		return bag;
	}
}
