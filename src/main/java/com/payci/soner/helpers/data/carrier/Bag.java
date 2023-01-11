package com.payci.soner.helpers.data.carrier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.payci.soner.models.IO.base.InputBase;
import com.payci.soner.models.IO.base.SingleInput;

public class Bag {
	private final HashMap<String, Object> _hashMap = new HashMap<String, Object>();
	
	public boolean put(String key, Object obj) {
		if(obj == null) return false;
		_hashMap.put(key, obj);
		return true;
	}
	
	public Object get(String key) {
		return _hashMap.get(key);
	}
	
	public Object remove(String key) {
		return _hashMap.remove(key);
	}
	
	public void clear() {
		_hashMap.clear();
	}

	
	public List<SingleInput> toIOList() {
		List<SingleInput> params = new ArrayList<SingleInput>();
		
		this._hashMap.forEach((k,v) -> {
			params.add(new SingleInput(k, v));
		});
		
		return params;
	}
	
	// TODO : what if value is another hashMap or similar?
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		_hashMap.forEach((key, value) -> {
			sb.append(key)
			.append(": ")
			.append(value)
			.append('\n');
		});
		
		return sb.toString();
	}
}
