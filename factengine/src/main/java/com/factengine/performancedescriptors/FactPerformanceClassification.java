package com.factengine.performancedescriptors;

public class FactPerformanceClassification implements IFactPerformance {
	
	public double accuracy;
	public double fmeasure;
	public double precision;
	public double recall;
	public double kappa;
	
	
	public double getAccuracy() {
		return accuracy;
	}
	
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	
	public double getFmeasure() {
		return fmeasure;
	}
	
	public void setFmeasure(double fmeasure) {
		this.fmeasure = fmeasure;
	}
	
	public double getPrecision() {
		return precision;
	}
	
	public void setPrecision(double precision) {
		this.precision = precision;
	}
	
	public double getRecall() {
		return recall;
	}
	
	public void setRecall(double recall) {
		this.recall = recall;
	}
	
	public double getKappa() {
		return kappa;
	}
	
	public void setKappa(double kappa) {
		this.kappa = kappa;
	}
	
	
}
