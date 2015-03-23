package com.dataframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;







import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void guessTypeTest()
    {		
    	//System.out.println(df.GetTypes());
    	Assert.assertTrue(df.GetTypes().equals("\"unknown_name\" - type: STRING - subtype: FREE_TEXT,\"Sepal.Length\" - type: DOUBLE - subtype: POSITIVE_REAL,\"Sepal.Width\" - type: DOUBLE - subtype: POSITIVE_REAL,\"Petal.Length\" - type: DOUBLE - subtype: POSITIVE_REAL,\"Petal.Width\" - type: DOUBLE - subtype: POSITIVE_REAL,\"Species\" - type: STRING - subtype: FACTOR"));
    }
    
    @Test
    public void guessMissingValuesTypeTest()
    {		
    	System.out.println(df_missing.GetTypes());
    	Assert.assertTrue(df_missing.GetTypes().equals("Sepal.Length - type: DOUBLE - subtype: POSITIVE_REAL,Sepal.Width - type: DOUBLE - subtype: POSITIVE_REAL,Petal.Length - type: DOUBLE - subtype: POSITIVE_REAL,Petal.Width - type: DOUBLE - subtype: POSITIVE_REAL,Species - type: STRING - subtype: FACTOR"));
    }
    
  //test that the largest column index is 6
    @Test
    public void columnNumber()
    {	
		
    	for (Entry<IndexKey, DataPoint> entry : df.df.entrySet()) {
  		  IndexKey key = entry.getKey();
  		  Assert.assertTrue(key.getColumn()<7);
  	
  		}
    
    }
    
    @Test
    public void testGetColumn()
    {		
    	ArrayList<DataPoint> column1;
		try {
			column1 = df.getColumn(1);
			Assert.assertTrue(column1.size()==150);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @Test
    public void testGetRow()
    {		
    	ArrayList<DataPoint> column1;
		try {
			column1 = df.getRow(1);
			Assert.assertTrue(column1.size()==6);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @Test
    public void testGetRowNames()
    {		
    	try {
			ArrayList<DataPoint> column1=df.getRow(1);
			Assert.assertTrue(true);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println(column1.toString());
    
    }
    
    @Test
    public void testFirstRowNames()
    {		
    	ArrayList<DataPoint> column1;
		try {
			column1 = df.getRow(1);
			Assert.assertTrue(column1.toString().equals("[\"1\", 5.1, 3.5, 1.4, 0.2, \"setosa\"]"));
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    
    @Test
    public void testGetColumnByName(){
    	try {
			ArrayList<DataPoint> paok=df_missing.getColumn("Sepal.Length");
			Assert.assertTrue(paok.get(0).toString().equals("?"));
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @Test
    public void testDropColumns(){
    	int[] columns={1,5};
    	df.dropColumns(columns);
    	
    	String[] strings=df.getIndexKeyListString().split("\n");

    	
    	System.out.println(strings[503]);
    	Assert.assertTrue(strings[4].equals("2,1"));
    	Assert.assertTrue(strings[503].equals("126,4"));
    }
    
    @Test
    public void testDropMiddleColumns(){
    	int[] columns={2,3};
    	df.dropColumns(columns);

    	
    	String[] strings=df.getIndexKeyListString().split("\n");

    	
    	System.out.println(strings[503]);
    	Assert.assertTrue(strings[4].equals("2,1"));
    	Assert.assertTrue(strings[503].equals("126,4"));
    }
    
    @Test
    public void testDropRows(){
    	int[] rows={1,2,3,150};
    	df.dropRows(rows);
    	
    	String[] strings=df.getIndexKeyListString().split("\n");

    	Assert.assertTrue(strings[875].equals("146,6"));
    }
    
    @Test
    public void testDropMiddleRows(){
    	int[] rows={2,3,4,150};
    	df.dropRows(rows);
    	
    	String[] strings=df.getIndexKeyListString().split("\n");

    	Assert.assertTrue(strings[875].equals("146,6"));
    }
    
    @Test
    public void computeNumberofMissingValues(){
    	try {
			Assert.assertTrue(df_missing.getNumMissingValuesForRow(1)==2);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    @Test
    public void computeNumberofMissingValuesForColumn(){
    	try {
			Assert.assertTrue(df_missing.getNumMissingValuesForColumn(1)==2);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
