package com.datautils;

import java.util.HashMap;
import java.util.Set;

import com.dataframe.DataFrame;
import com.dataframe.DataPoint;

//the imputor interface is used to construct 'delegates' that impute 
public interface Imputor {

	//this method is used when dealing with lots of missing values
	HashMap<Integer, DataPoint> getImputedValues(Set<Integer> missing, DataFrame df);

	//this method is used when dealing with a single missing value
	Double getImputedValues(int column, DataFrame df);
}
