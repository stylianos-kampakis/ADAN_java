package com.rinterface;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
RLinearRegressionProvider rinter=new RLinearRegressionProvider("C:/Program Files/R/R-3.1.2/bin/x64/R.exe");

    	    	try {
    				df.readCSV(path);
    				String result =rinter.fit("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    				double res=rinter.getLogLikelihood();
    		
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    }
    }

