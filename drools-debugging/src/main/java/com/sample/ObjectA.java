package com.sample;

import java.io.Serializable;

public class ObjectA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObjectB objectB;
	private Integer someVariable;
	
	public ObjectB getObjectB() {
		return objectB;
	}
	public void setObjectB(ObjectB objectB) {
		this.objectB = objectB;
	}
	public Integer getSomeVariable() {
		return someVariable;
	}
	public void setSomeVariable(Integer someVariable) {
		this.someVariable = someVariable;
	}
	
}
