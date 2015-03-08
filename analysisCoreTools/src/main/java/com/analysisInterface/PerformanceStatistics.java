package com.analysisInterface;

import java.util.HashMap;

public class PerformanceStatistics {
	
	/**public HashMap<String,Double> calculateAllMetrics(PredictionResultSet results)
	 * 
	 * Automatically determines which type of task the result set comes from (by
	 * using the enumeration stored in a result set) and then returns all the relevant
	 * metrics (classification, or regression).
	 * 
	 * @param results	a PredictionResultSet results object
	 */
	public HashMap<String,Double> calculateAllMetrics(PredictionResultSet results){
		return null;		
	}

	public Double correlation(PredictionResultSet results){
		return null;
	};
	
	public Double MAE(PredictionResultSet results){
		return null;
	};
	
	public Double RMSE(PredictionResultSet results){
		return null;
	};
	
	public Double concordanceCorrelation(PredictionResultSet results){
		return null;
	};
	
	public Double kappaStatistic(PredictionResultSet results) {
		return null;
	}
	
	public Double accuracy(PredictionResultSet results) {
		return null;
	}
	
	public Double AUC(PredictionResultSet results) {
		return null;
	}
	
}
