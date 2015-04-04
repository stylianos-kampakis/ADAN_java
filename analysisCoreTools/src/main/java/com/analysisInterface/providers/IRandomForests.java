package com.analysisInterface.providers;

public interface IRandomForests extends IClassification,IRegression {
	
	public void setNumTrees(int numTrees);

}
