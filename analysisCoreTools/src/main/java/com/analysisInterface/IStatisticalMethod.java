package com.analysisInterface;

import java.util.HashMap;

public interface IStatisticalMethod {
	
	public double AIC();
	
	public double BIC();
	
	public double likelihood();
	
	public HashMap<String,Double> returnCoefficients();

}
