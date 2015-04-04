package com.factengine.analysisdescriptors;

import java.util.HashMap;

import com.factengine.Fact;

public class FactCoefficients extends Fact {
	
	HashMap<String,Double> coefValues;
	HashMap<String,Double> coefPValues;
	
	
	public FactCoefficients(String[] names,Double[] coefficients,Double[] pvalues){
		
		for(int i=0;i<names.length;i++){
			coefValues.put(names[i], coefficients[i]);
			coefPValues.put(names[i], pvalues[i]);
		}
		
		
	}

}
