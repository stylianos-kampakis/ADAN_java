package com.rinterface;

import com.dataframe.DataFrame;

import java.io.IOException;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;



/**
 * Unit test for simple App.
 */
public class AppTest 
 
{
	DataFrame df;
	DataFrame df_missing;
	DataFrame df_numeric;
	RInterface rinter;

	
	  @Before
	    public void loadCSV(){
	    	
	    	df=new DataFrame();
	    	String path="C:\\iris.csv";
	    	try {
				df.readCSV(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	df_missing=new DataFrame();
	    	path="C:\\iris_missing.csv";
	    	try {
				df_missing.readCSV(path);
				df_missing.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	df=new DataFrame();
	    	path="C:\\iris.csv";
	    	try {
				df.readCSV(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	df_missing=new DataFrame();
	    	path="C:\\iris_numeric.csv";
	    	try {
				df_missing.readCSV(path);
				df_missing.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	rinter=new RInterface("C:/Program Files/R/R-3.1.2/bin/x64/RScript.exe");

	    	
	    }
  
  
    
    @Test
    public void testLinearRegressionAllCovariates()
    {
		String result=rinter.runLinearRegression("Sepal.Length", df);
	System.out.println(result);
        Assert.assertTrue( true );
    }
    
    @Test
    public void testChooseCovariatesLinearRegression(){
    	String result=rinter.runLinearRegression("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    	System.out.println(result);
            Assert.assertTrue( true );
    	
    }
}
