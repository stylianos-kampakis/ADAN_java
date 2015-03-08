package com.dataframe;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {//String path="C:\\iris_numeric.csv";
	String path="C:\\iris_missing.csv";
    	DataFrame df=new DataFrame();

    	try {
			df.readCSV(path);
			//System.out.println(df.GetTypes());
		//	System.out.println(df.getRDataFrame());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
