package com.factengine.analysisdescriptors;

import java.util.Arrays;

import com.analysisInterface.AnalysisExecutor;
import com.analysisInterface.parameters.ParameterSet;
import com.analysisInterface.results.PredictionResultSet;
import com.dataframe.DataFrame;
import com.factengine.descriptionenums.ModelProperties;
import com.factengine.factmodels.FactModel;
import com.factengine.performancedescriptors.FactPerformanceRegression;
import com.factengine.performancedescriptors.FactPerformanceRegressionStatistical;
import com.factengine.performancedescriptors.IFactPerformance;

public class FactAnalysisRegression implements IFactAnalysisModel {

	FactPerformanceRegression performance;
	FactModel model;
	AnalysisExecutor executor;
	
	public FactAnalysisRegression(FactModel model, AnalysisExecutor analysisExecutor){
		this.model=model;
		this.executor=executor;
		this.performance=performance;
	}
	

	public void setDataFrame(DataFrame df){
		executor.setDataFrame(df);
	}
	
	public IFactPerformance getPerformance() {
		return performance;
	}

	public FactPerformanceRegression crossVal(DataFrame df,ParameterSet parameters){
		setDataFrame(df);
		PredictionResultSet res = executor.crossVal(10, 10, model.getModelName());
		FactPerformanceRegression fact=new FactPerformanceRegression(model,res,parameters);
		return fact;
	}

	public FactPerformanceRegressionStatistical fit(DataFrame df,ParameterSet parameters) {
		
		setDataFrame(df);
		PredictionResultSet res = executor.fit(model.getModelName(), parameters);
		FactPerformanceRegressionStatistical fact=new FactPerformanceRegressionStatistical(model,res,parameters);
		return fact;
	}

	
	public boolean returnsCoefs(){
		ModelProperties[] properties=model.getModelProperties();
		return Arrays.asList(properties).contains(ModelProperties.RETURNS_COEFFICIENTS);
	}


	public PredictionResultSet fit(DataFrame df) {
		// TODO Auto-generated method stub
		return null;
	}


	public PredictionResultSet predict(DataFrame df) {
		// TODO Auto-generated method stub
		return null;
	}

}
