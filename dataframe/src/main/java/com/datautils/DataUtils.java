package com.datautils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.dataframe.DataPointType;

public class DataUtils {
	
	public static ArrayList<DataPoint> imputeMean(DataFrame df, int row) throws DataFrameIndexException{
		return DataUtils.impute(df, row, new DataUtils.meanImputor());
	}
	
	public static HashMap<Integer,ArrayList<DataPoint>> imputeMean(DataFrame df, Set<Integer> rows) throws DataFrameIndexException{
		return DataUtils.impute(df, rows, new DataUtils.meanImputor());
	}
	
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
		Set<Integer> missing=getMissingColumnIndices(points);
			
		//The imput does not return a new row, but a map of the form <column, mean/median/mode/etc.>
		HashMap<Integer,DataPoint> newValues=imputor.getImputedValues(missing,df);
		for(Entry<Integer,DataPoint> point:newValues.entrySet()){
			//we need to subtract 1, because the key is in DataFrame index and
			//the columns start at 1.
			points.set(point.getKey()-1,point.getValue());
		}	
		return points;
	}
	
	/**
	 * 
	 * This method accepts an array of rows that need to be imputed.<p>
	 * NOTE: This method works ONLY for imputation methods that calculate statistics on a whole column.
	 * Example statistics include mean/median/mode. It cannot be used with K-nn or other predictive methods
	 * for example.
	 * 
	 * @param df
	 * @param rows
	 * @param imputor
	 * @return
	 * @throws DataFrameIndexException
	 */
public static HashMap<Integer,ArrayList<DataPoint>> impute(DataFrame df, Set<Integer> rows, Imputor imputor) throws DataFrameIndexException{
	
	HashMap<Integer,ArrayList<DataPoint>> newRows=new HashMap<Integer,ArrayList<DataPoint>>();
	ArrayList<DataPoint> points;
	
	HashMap<Integer,Double> means=new HashMap<Integer,Double>();
	
	for(int row:rows){
		points=df.getRow(row);
		Set<Integer> missing=getMissingColumnIndices(points);

		//This part checks whether the mean for a particular column has been caclulated before
		//If not, then it does calculate it. After that, the missing values in the row are replaced
		//with the corresponding column mean.
		for(int column:missing){
			if(!means.containsKey(column)){
				//in that case the 'imputor' is just calculating a mean or median
				means.put(column, imputor.getImputedValues(column,df));
			}
			points.set(column-1,new DataPoint(means.get(column)));
		}
		newRows.put(row, points);
	}	
	return newRows;		
	}
	

/**
 * 
 * This method accepts an array of rows that need to be imputed.<p>
 * NOTE: This method works ONLY for imputation methods that calculate statistics on a whole column.
 * Example statistics include mean/median/mode. It cannot be used with K-nn or other predictive methods
 * for example.
 * 
 * @param df
 * @param rows
 * @param imputor
 * @return
 * @throws DataFrameIndexException
 */
public static HashMap<Integer,ArrayList<DataPoint>> impute(DataFrame df, int[] rows, Imputor imputor) throws DataFrameIndexException{

	Set<Integer> rowSet=new HashSet<Integer>();
			
	for(int row:rows){
		rowSet.add(row);
	}
	
	return impute(df,rowSet,imputor);
	
}

	
	//helper class that gets the columns of a row where missing values exist
	private static Set<Integer> getMissingColumnIndices(ArrayList<DataPoint> points)
	{
		Set<Integer> missing=new HashSet<Integer>();
		DataPoint point;
		//we need to start at 1, because the indexing of columns starts at 1
		for(int i=1;i<=points.size();i++){
			point=points.get(i-1);	
			if(point.getType()==DataPointType.NA)
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
public static HashMap<Integer, Double> getMeans(Set<Integer> columns, DataFrame df){
	double sum;
	int count_points=0;
	ArrayList<DataPoint> points;
	HashMap<Integer, Double> means=new HashMap<Integer, Double>();
	
	for(Integer column:columns){
	sum=0.0;
	try {
		points=df.getColumn(column);
		for(DataPoint point:points){
			if(point.getType()!=DataPointType.NA){
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


/**
 * 
 * Method that calculates the means of the specified columns.
 * 
 * @param columns
 * @param df
 * @return
 */
public static HashMap<Integer, Double> getMeans(int[] columns, DataFrame df){
	Set<Integer> cols=new HashSet<Integer>();
	
	for(int column:columns){
		cols.add(column);
	}
	
	return getMeans(cols,df);
}

/**
 * 
 * Method that calculates the means of a single column.
 * 
 * @param columns
 * @param df
 * @return
 */
public static double getMeans(int column, DataFrame df){
	Set<Integer> list=new HashSet<Integer>();
	list.add(column);
	return getMeans(list,df).get(column).doubleValue();
}
	
	
	//this is a 'delegate' for imputing missing values using the mean of a column
public static class meanImputor implements Imputor{		

	/**
	 * This method calculates the means of each column where a missing value exists and then
	 * returns a Map of the form <Column, Imputed value>.
	 * 
	 * @param ArrayList<Integer> missing
	 * @param DataFrame df
	 */
	public HashMap<Integer, DataPoint> getImputedValues(Set<Integer> missing, DataFrame df) {
		HashMap<Integer, Double> means=getMeans(missing,df);
		HashMap<Integer, DataPoint> newValues = new HashMap<Integer,DataPoint>();
		
		for(Entry<Integer,Double> point : means.entrySet()){
			newValues.put(point.getKey(),new DataPoint(point.getValue()));
		}
		
		return newValues;
	}
	
	public Double getImputedValues(int missing, DataFrame df) {

		return getMeans(missing,df);
	}
}
	
	
}
