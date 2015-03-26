package com.factengine;

import java.util.Arrays;

import com.analysisInterface.AnalysisExecutor;
import com.analysisInterface.ParameterSet;
import com.analysisInterface.PredictionResultSet;
import com.dataframe.DataFrame;

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

	public void crossVal(DataFrame df){
		setDataFrame(df);
		executor.crossVal(10, 10, model.getModelName());
	}

	public FactPerformanceRegression fit(DataFrame df,ParameterSet parameters) {
		setDataFrame(df);
		PredictionResultSet res= executor.fit(model.getModelName(), parameters);
	}

//weird coupling here!
	
	public FactPerformanceRegression predict(DataFrame df,ParameterSet parameters) {
		setDataFrame(df);
		PredictionResultSet res= executor.predict(model.getModelName(),parameters);
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
