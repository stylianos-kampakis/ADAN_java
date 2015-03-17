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
	RLinearRegressionProvider rinter;
	
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
	    	rinter=new RLinearRegressionProvider("C:/Program Files/R/R-3.1.2/bin/x64/R.exe");    	
	    }
    
	
	  
	@Test
    public void testLinearRegressionAllCovariates()
    {
		String result=rinter.fit("Sepal.Length", df);
	System.out.println(result);
	//we are using only a single coefficient int the assertion because the result string is
	//too long.
        Assert.assertTrue(result.contains("<value>0.202165115655111"));
    }
    
    @Test
    public void testChooseCovariatesLinearRegression(){
    	String result=rinter.fit("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    	//System.out.println(result);
    	//we are using only a single coefficient int the assertion because the result string is
    	//too long.
    Assert.assertTrue(result.contains("<value>2.24914016038323"));
    

    
    }
    
    @Test
    public void testPrediction(){
    	rinter.fit("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    	//System.out.println(result);
    	//we are using only a single coefficient int the assertion because the result string is
    	//too long.
 
    
    double[] results=rinter.predict(df);
	Assert.assertTrue(results[2]==4.76831540748799);
    
    }
    
    @Test
    public void testFitted(){
    	rinter.fit("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    	//System.out.println(result);
    	//we are using only a single coefficient int the assertion because the result string is
    	//too long.

    
    double[] results=rinter.getFitted();
	Assert.assertTrue(results[2]==4.76831540748799);
    
    }
    
    @Test
    public void testAIC(){
    	rinter.fit("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    
    double result=rinter.getAIC();
	Assert.assertTrue(result==101.025499819592);
    }
    
    @Test
    public void testBIC(){
    	rinter.fit("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    
    double result=rinter.getBIC();
	Assert.assertTrue(result==113.068040995977);
    }
   
    @Test
    public void testLogLik(){
    	rinter.fit("Sepal.Length", df,new String[]{"Petal.Length","Sepal.Width"});
    
    double result=rinter.getLogLikelihood();
	Assert.assertTrue(result==-46.51);
    }
    
    
}
