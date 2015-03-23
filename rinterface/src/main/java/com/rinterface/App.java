package com.rinterface;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
    	String path="C:\\iris_numeric_short.csv";
    		DataFrame df=new DataFrame();
RLinearRegressionProvider rinter=new RLinearRegressionProvider("C:/Program Files/R/R-3.1.2/bin/x64/R.exe");
OutputStream rInput;

    	    	try {
    				df.readCSV(path);
    				System.out.println(df.toString());

    				String result =rinter.fit("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    				double res=rinter.getLogLikelihood();
    				rinter.getPvalueCoefficients();
    		
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    }
    }

