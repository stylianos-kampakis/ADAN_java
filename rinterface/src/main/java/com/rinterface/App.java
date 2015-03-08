package com.rinterface;
import java.io.File;
import java.io.IOException;

import com.dataframe.DataFrame;

import rcaller.RCaller;
import rcaller.RCode;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String path="C:\\iris_numeric.csv";
    		DataFrame df=new DataFrame();
RInterface rinter=new RInterface("C:/Program Files/R/R-3.1.2/bin/x64/RScript.exe");

    	    	try {
    				df.readCSV(path);
    				String result =rinter.runLinearRegression("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    				//System.out.println(df.getRDataFrame());
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    }
    }

