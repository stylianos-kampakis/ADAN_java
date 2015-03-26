package com.factengine;

import com.analysisInterface.PredictionResultSet;
import com.dataframe.DataFrame;

public interface IFactAnalysisModel {
	
	FactModel model=null;
	IFactPerformance performance=null;
	
	public IFactPerformance getPerformance();
	public PredictionResultSet fit(DataFrame df);
	public PredictionResultSet predict(DataFrame df);

}
