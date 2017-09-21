package com.sample;

import java.io.Serializable;
public class ObjectB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer someVariable;

	public Integer getSomeVariable() {
		return someVariable;
	}

	public void setSomeVariable(Integer someVariable) {
		this.someVariable = someVariable;
	}
	
}
