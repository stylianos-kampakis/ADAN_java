package com.datautils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.dataframe.IndexKey;

/**
 * Unit test for simple App.
 */
public class AppTest  

{
	DataFrame df;
	DataFrame df_missing;
	
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

    }
    
  
    @Test
    public void imputeMean()
    {	
try {
	ArrayList<DataPoint> points=DataUtils.impute(df_missing, 1, new DataUtils.meanImputor());
	System.out.println(points.toString());
	Assert.assertTrue(points.toString().equals("[5.854054054054055, 3.5, 1.8945945945945957, 0.2, 'setosa']"));
} catch (DataFrameIndexException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    
    }
}
