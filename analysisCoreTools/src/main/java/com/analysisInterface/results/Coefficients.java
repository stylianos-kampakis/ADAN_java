package com.analysisInterface.results;

import java.util.HashMap;

public class Coefficients {
	HashMap<String,Double> coefficients;
	HashMap<String,Double> pvalues;
	boolean pvaluesexist=false;
	
	public Coefficients(HashMap<String,Double> coefficients){
		this.coefficients=coefficients;
	}
	
	public Coefficients(HashMap<String,Double> coefficients,HashMap<String,Double>pvalues){
		this.coefficients=coefficients;
		this.pvalues=pvalues;
		pvaluesexist=true;
	}
	
	public HashMap<String,Double> getCoefficients(){
		return(this.coefficients);
	}
	
	public boolean pvaluesExist(){
		return(pvaluesexist);
	}
	
}
