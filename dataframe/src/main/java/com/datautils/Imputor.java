package com.datautils;

import java.util.ArrayList;
import java.util.HashMap;

import com.dataframe.DataFrame;
import com.dataframe.DataPoint;

//the imputor interface is used to construct 'delegates' that impute 
public interface Imputor {

	HashMap<Integer, DataPoint> getImputedValues(ArrayList<Integer> missing, DataFrame df);


}
