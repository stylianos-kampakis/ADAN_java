package com.datautils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;





import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;



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
    
    
    @Test
    public void imputeMultipleRows()
    {	
try {
	Set<Integer> rows=new HashSet<Integer>();
	rows.add(1);
	rows.add(2);
	HashMap<Integer, ArrayList<DataPoint>> points=DataUtils.impute(df_missing, rows , new DataUtils.meanImputor());
	//System.out.println(points.toString());
	Assert.assertTrue(points.toString().equals("{1=[5.854054054054055, 3.5, 3.7891891891891913, 0.2, 'setosa'], 2=[4.9, 3.0, 1.4, 1.2135135135135142, 'setosa']}"));
} catch (DataFrameIndexException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

    }

    @Test
    public void testImputation(){
    	
    	try {
        	df_missing.setRows(DataUtils.impute(df_missing, new int[]{2,3,5,7,8,14}, new DataUtils.meanImputor()));
			df_missing.getRows(new int[]{2,3,5,7,8,14});
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    Assert.assertTrue(true);
    }


}
