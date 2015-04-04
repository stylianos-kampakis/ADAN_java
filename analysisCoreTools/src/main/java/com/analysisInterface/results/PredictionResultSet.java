package com.analysisInterface.results;

import java.util.ArrayList;

public class PredictionResultSet {
	
	public enum resultType {CLASSIFICATION, REGRESSION};
	 
	ArrayList<Double> predictions;
	ArrayList<Double> originals;

}
