package com.stackroute.datamunger.query;

//this class contains the data type definitions
public class DataTypeDefinitions {

	/*
	 * this class should contain a member variable which is a String array, to hold
	 * the data type for all columns for all data types
	 */	
	private String [] datatypes;
		
	
	
	public void setDatatypes(String [] datatypes) {
		this.datatypes = datatypes;
	}




	public String[] getDataTypes() {
			
		return datatypes;
		
	}
}
