package com.factengine;

import com.analysisInterface.Algorithms;

public class FactPerformanceRegression implements IFactPerformance {

	double RMSE;
	double MAE;
	//correlation
	double corr;
	//concordance correlation coefficient
	double ccc;
	
	FactModel model;
	
	public Algorithms getModelName(){
		return model.getModelName();
	}
	
	
	public double getRMSE() {
		return RMSE;
	}
	public void setRMSE(double rMSE) {
		RMSE = rMSE;
	}
	public double getMAE() {
		return MAE;
	}
	public void setMAE(double mAE) {
		MAE = mAE;
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
