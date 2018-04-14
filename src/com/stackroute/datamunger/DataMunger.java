package com.stackroute.datamunger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;
import com.stackroute.datamunger.reader.CsvQueryProcessor;


public class DataMunger {
	
	public static void main(String[] args){
		
		
		//read the file name from the user
		
			Scanner sc = new Scanner(System.in);
			//System.out.println("Enter a file name");
			String filename = sc.nextLine();
			//System.out.println(filename);
				
		/*
		 * create object of CsvQueryProcessor. We are trying to read from a file inside
		 * the constructor of this class. Hence, we will have to handle exceptions.
		 */
			CsvQueryProcessor csvfile=null;
			try {
				csvfile = new CsvQueryProcessor(filename);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}		
		
		//call getHeader() method to get the array of headers
		
			Header header = new Header();
			try {
				header=csvfile.getHeader();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			String [] finalheader = header.getHeaders();
			
		
		/*
		 * call getColumnType() method of CsvQueryProcessor class to retrieve the array
		 * of column data types which is actually the object of DataTypeDefinitions
		 * class
		 */
			DataTypeDefinitions datatypes = new DataTypeDefinitions();
			try {
				datatypes = csvfile.getColumnType();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			String [] finaldatatype = datatypes.getDataTypes();	
		
		/*
		 * display the columnName from the header object along with its data type from
		 * DataTypeDefinitions object
		 */
		
			for(int index = 0; index<finalheader.length; index++)
			{
				System.out.println(finalheader[index] + ":" + finaldatatype[index]);
			}
		
	}

}
