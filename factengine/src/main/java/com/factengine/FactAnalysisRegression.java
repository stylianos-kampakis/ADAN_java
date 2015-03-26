package com.factengine;

import com.analysisInterface.analysisExecutor;

public class FactAnalysisRegression implements IFactAnalysisModel {

	FactPerformanceRegression performance;
	FactModel model;
	analysisExecutor executor;
	
	
	public IFactPerformance getPerformance() {
		return performance;
	}


	public void fit() {
		// TODO Auto-generated method stub
		executor.fit(model.getModelName());
	}


	public void predict() {
		// TODO Auto-generated method stub
		
	}

}
