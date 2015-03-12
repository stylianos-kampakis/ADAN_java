package com.dataframe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.dataframe.DataFrame.CheckIfMissing;
import com.dataframe.DataFrame.CheckMissingCount;

/**
 * Hello world!
 *
 */
public class App 
{
		
    public static void main( String[] args ) throws IOException
    {
	String path="C:\\iris_missing.csv";
    	DataFrame df=new DataFrame();
    	df.readCSV(path);
    
    	CheckIfMissing check =new DataFrame.CheckIfMissing();
    	CheckMissingCount check2=new DataFrame.CheckMissingCount();
		try {
			System.out.println(df.applyLogicalToRow(check, 1));
			System.out.println(df.applyCountLogicalToRow(check2, 1));
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
