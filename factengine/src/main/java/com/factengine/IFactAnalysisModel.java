package com.factengine;

public interface IFactAnalysisModel {
	
	FactModel model=null;
	IFactPerformance performance=null;
	
	public IFactPerformance getPerformance();
	public void fit();
	public void predict();

}
