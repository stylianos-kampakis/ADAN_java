package com.analysisInterface;

import java.util.HashMap;

public interface IStatisticalMethod {
	
	public double getAIC();
	
	public double getBIC();
	
	public double getLogLikelihood();
	
	public HashMap<String,Double> getCoefficients();

}
