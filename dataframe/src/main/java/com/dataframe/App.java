package com.dataframe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Hello world!
 *
 */
public class App 
{
	

	
    public static void main( String[] args ) throws IOException
    {
	String path="C:\\iris.csv";
    	DataFrame df=new DataFrame();
    	df.readCSV(path);
    

     	
    	int[] columns={1,5};
    	df.dropColumns(columns);

    	
    	System.out.println(df.getIndexKeyListString());
    	
    	System.out.println(df.getColumnNames());

    }
    


}
