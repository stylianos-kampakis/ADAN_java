package com.factengine.analysisdescriptors;

import com.analysisInterface.results.PredictionResultSet;
import com.dataframe.DataFrame;
import com.factengine.factmodels.FactModel;
import com.factengine.performancedescriptors.IFactPerformance;

public interface IFactAnalysisModel {
	
	FactModel model=null;
	IFactPerformance performance=null;
	
	public IFactPerformance getPerformance();
	public PredictionResultSet fit(DataFrame df);
	public PredictionResultSet predict(DataFrame df);

}
