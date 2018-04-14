package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	FileReader file = null;
	BufferedReader bufferedReader = null;
	String filename = null;

	/*
	 * parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.filename=fileName;
		file = new FileReader(filename);
		bufferedReader = new BufferedReader(file);
	}

	/*
	 * implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 */
	@Override
	public Header getHeader() throws IOException {
		file = new FileReader(filename);
		bufferedReader = new BufferedReader(file);
		
		Header finalheader = new Header();
		
		String headers = bufferedReader.readLine();
		
		String [] headerArray = headers.split(",");
		
		finalheader.setHeaders(headerArray);
		
		return  finalheader;
	}
	

	/**
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {

	}

	/*
	 * implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
	 */
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		file = new FileReader(filename);
		bufferedReader = new BufferedReader(file);
		
		DataTypeDefinitions finaloutput = new DataTypeDefinitions();
		
		String headers = bufferedReader.readLine();
		
		String [] headerArray = headers.split(",");
		
		String firstline = bufferedReader.readLine();
				
		String [] firstlineArray = firstline.split(",");
		
		
		String[] datatypes = new String[headerArray.length];
		
		for(int index = 0; index<headerArray.length; index++)
		{
			try
	        {
	            
	            datatypes[index]= classType(firstlineArray[index]);
	        }
	        catch(ArrayIndexOutOfBoundsException e)
	        {
	        	 datatypes[index]= classType(" ");
	            
	        }
		 
		}
		finaloutput.setDatatypes(datatypes);
		
		
		return finaloutput;
	
		
		
		
		
				
		// checking for date format dd/mm/yyyy
		
		// checking for date format mm/dd/yyyy
		
		// checking for date format dd-mon-yy
		
		// checking for date format dd-mon-yyyy
		
		// checking for date format dd-month-yy
		
		// checking for date format dd-month-yyyy
		
		// checking for date format yyyy-mm-dd
		
		
	}
	
	public String classType(String data) {
		
        try {
        	// checking for Integer
            Integer tempdata = Integer.parseInt(data);
            return tempdata.getClass().getName();
        } catch (Exception e) {
            try {
            	java.util.Date date1 = null;
                String tempdata = data;
                
                // checking for date format dd/mm/yyyy
        		        		
                if(tempdata.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})"))
                {
                date1 = new SimpleDateFormat("dd-mm-yyyy").parse(tempdata);
                return date1.getClass().getName();
                }else 
                	{
                	// checking for date format mm/dd/yyyy
            		
            			if(tempdata.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})"))
                		{
                			date1 = new SimpleDateFormat("mm-dd-yyyy").parse(tempdata);
                			return date1.getClass().getName();
                		}
                		else
                    	{
                			// checking for date format dd-mon-yy
                    		
                    		if(tempdata.matches("([0-9]{2})-[a-zA-Z]{3}-([0-9]{2})"))
                    		{
                    			date1 = new SimpleDateFormat("dd-mon-yy").parse(tempdata);
                    			return date1.getClass().getName();
                    		}
                    		else
                    		{
                        		// checking for date format dd-mon-yyyy
                        		
                    			if(tempdata.matches("([0-9]{2})-[a-zA-Z]{3}-([0-9]{4})"))
                    			{
                    				date1 = new SimpleDateFormat("dd-MMMM-yyyy").parse(tempdata);
                        			return date1.getClass().getName();
                    			}
                    			else
                    			{
                    				// checking for date format dd-month-yy
                            		
                    				if(tempdata.matches("([0-9]{2})-[a-zA-Z]*-([0-9]{2})"))
									{
										date1 = new SimpleDateFormat("dd-MMMM-yy").parse(tempdata);
	                        			return date1.getClass().getName();
									}
                    				else
                    				{
                                		// checking for date format dd-month-yyyy
                                		
                    					if(tempdata.matches("([0-9]{2})-[a-zA-Z]*-([0-9]{4})"))
                    					{
                    						date1 = new SimpleDateFormat("dd-MMMM-yyyy").parse(tempdata);
    	                        			return date1.getClass().getName();
                    					}
                    					else
                    					{

                                    		// checking for date format yyyy-mm-dd
                    						if(tempdata.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
                    		                {
                    							date1 = new SimpleDateFormat("yyyy-mm-dd").parse(tempdata);
                    							
                    							return date1.getClass().getName();
                    		                }
                    						else
                    						{
                    							// checking for floating point numbers
                    							Double date2 = Double.parseDouble(tempdata);
                    			                return date2.getClass().getName();
                    						}
                    					}
                    					
                    				}

                    			}
                    		}
                    	}
                		
                	}
                
                
            } catch (Exception e1) {
                String tempdata = data;
                if (tempdata.trim().equals("")) {
                    Object object = new Object();
                    //System.out.println(object.getClass().getName());
                    return object.getClass().getName();
                } else {
                    return tempdata.getClass().getName();
                }
            }
        }
    }

	
	

}
