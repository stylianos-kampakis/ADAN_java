package com.analysisInterface.providers.supervised;

import java.util.HashMap;

import com.analysisInterface.results.Coefficients;

public interface IStatisticalMethod {
	
	public double getAIC();
	
	public double getBIC();
	
	public double getLogLikelihood();
	
	public Coefficients getCoefficients();

}
