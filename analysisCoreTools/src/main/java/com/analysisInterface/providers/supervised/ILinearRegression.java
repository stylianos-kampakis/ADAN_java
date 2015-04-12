package com.analysisInterface.providers.supervised;

import java.util.HashMap;

import com.dataframe.DataFrame;

public interface ILinearRegression extends IRegression, IStatisticalMethod{
		
	public double rSquared();	
	
	public double adjustedRSquared();
	
}
