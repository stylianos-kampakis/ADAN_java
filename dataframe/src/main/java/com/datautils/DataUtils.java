package com.datautils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;

public class DataUtils {
	
	/**
	 * 
	 * Method that returns a new row, with the missing values imputed. The method requires an 
	 * imputor class that implements the Imputor interface and contains the imputation logic.
	 * 
	 * @param df
	 * @param row
	 * @param imputor
	 * @return an ArrayList<DataPoint> with the new row
	 * @throws DataFrameIndexException
	 */
	public static ArrayList<DataPoint> impute(DataFrame df, int row, Imputor imputor) throws DataFrameIndexException{
		
		ArrayList<DataPoint> points=df.getRow(row);
		
		//first find where the missing values are
		ArrayList<Integer> missing=getMissingColumnIndices(points);
			
		HashMap<Integer,DataPoint> newValues=imputor.getImputedValues(missing,df);
		for(Entry<Integer,DataPoint> point:newValues.entrySet()){
			//we need to subtract 1, because the key is in DataFrame index and
			//the columns start at 1.
			points.set(point.getKey()-1,point.getValue());
		}
	
		return points;
	}
	
	//helper class that gets the columns of a row where missing values exist
	private static ArrayList<Integer> getMissingColumnIndices(ArrayList<DataPoint> points)
	{
		ArrayList<Integer> missing=new ArrayList<Integer>();
		DataPoint point;
		//we need to start at 1, because the indexing of columns starts at 1
		for(int i=1;i<=points.size();i++){
			point=points.get(i-1);	
			if(point.getType()==DataFrame.DataPointType.NA)
			{
				missing.add(i);
			}
		}
		return missing;
	}
	
/**
 * 
 * Method that calculates the means of the specified columns.
 * 
 * @param columns
 * @param df
 * @return
 */
public static HashMap<Integer, Double>  getMeans(ArrayList<Integer> columns, DataFrame df){
	double sum;
	int count_points=0;
	ArrayList<DataPoint> points;
	HashMap<Integer, Double> means=new HashMap<Integer, Double>();
	
	for(Integer column:columns){
	sum=0.0;
	try {
		points=df.getColumn(column);
		for(DataPoint point:points){
			if(point.getType()!=DataFrame.DataPointType.NA){
				sum+=(Double)point.getPoint();
				count_points++;
			}
		}
		
		means.put(column, sum/count_points);	
	} catch (DataFrameIndexException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	}
	
	return means;
}
	
	
	//this is a 'delegate' for imputing missing values using the mean of a column
public static class meanImputor implements Imputor{		

	/**public HashMap<Integer, DataPoint> getImputedValues(ArrayList<Integer> missing, DataFrame df)
	 * 
	 * This method calculates the means of each column where a missing value exists and then
	 * returns a Map of the form <Column, Imputed value>.
	 * 
	 * @param ArrayList<Integer> missing
	 * @param DataFrame df
	 */
	public HashMap<Integer, DataPoint> getImputedValues(ArrayList<Integer> missing, DataFrame df) {
		HashMap<Integer, Double> means=getMeans(missing,df);
		HashMap<Integer, DataPoint> newValues = new HashMap<Integer,DataPoint>();
		
		for(Entry<Integer,Double> point : means.entrySet()){
			newValues.put(point.getKey(),new DataPoint(point.getValue()));
		}
		
		return newValues;
	}
}
	
	
}
