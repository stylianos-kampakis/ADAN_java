package com.analysisInterface.providers.supervised;


public interface IRandomForests extends IClassification,IRegression {
	
	public void setNumTrees(int numTrees);

}
