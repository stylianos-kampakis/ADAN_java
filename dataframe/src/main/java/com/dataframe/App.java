package com.dataframe;

import com.datautils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.dataframe.DataFrame.CheckIfMissing;
import com.dataframe.DataFrame.CheckMissingCount;
import com.datautils.DataUtils;

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
    
  
    	
    }

}
