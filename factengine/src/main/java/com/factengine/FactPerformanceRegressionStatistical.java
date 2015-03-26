package com.factengine;

import com.analysisInterface.ParameterSet;
import com.analysisInterface.PredictionResultSet;

public class FactPerformanceRegressionStatistical extends
		FactPerformanceRegression {

	double AIC;
	double BIC;
	double likelihood;
	
	FactPerformanceRegressionStatistical(FactModel model,
			PredictionResultSet results, ParameterSet parameters) {
		super(model, results, parameters);
		
	}

}
