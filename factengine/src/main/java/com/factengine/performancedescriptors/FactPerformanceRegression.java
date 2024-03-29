package com.factengine.performancedescriptors;

import com.analysisInterface.Algorithms;
import com.analysisInterface.parameters.ParameterSet;
import com.analysisInterface.results.PerformanceStatistics;
import com.analysisInterface.results.PredictionResultSet;
import com.factengine.factmodels.FactModel;

public class FactPerformanceRegression implements IFactPerformance {

	double RMSE;
	double MAE;
	//correlation
	double corr;
	//concordance correlation coefficient
	double ccc;
	
	PerformanceStatistics statistics=new PerformanceStatistics();
	ParameterSet parameters;
	
	FactModel model;
	
	public FactPerformanceRegression(FactModel model,PredictionResultSet results, ParameterSet parameters){
		setRMSE(statistics.RMSE(results));
		setMAE(statistics.MAE(results));
		setCcc(statistics.concordanceCorrelation(results));
		this.parameters=parameters;
	}
	
	public Algorithms getModelName(){
		return model.getModelName();
	}
	
	public ParameterSet getParameters(){
		return parameters;
	}
	
	public double getRMSE() {
		return RMSE;
	}
	public void setRMSE(double RMSE) {
		this.RMSE = RMSE;
	}
	public double getMAE() {
		return MAE;
	}
	public void setMAE(double MAE) {
		this.MAE = MAE;
	}
	public double getCorr() {
		return corr;
	}
	public void setCorr(double corr) {
		this.corr = corr;
	}
	public double getCcc() {
		return ccc;
	}
	public void setCcc(double ccc) {
		this.ccc = ccc;
	}
	
	
	
}
