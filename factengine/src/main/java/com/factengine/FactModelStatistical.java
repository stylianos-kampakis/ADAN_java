package com.factengine;

import com.analysisInterface.Algorithms;

public class FactModelStatistical extends FactModel {

	double AIC;
	double BIC;
	double likelihood;

	FactCoefficients factCoefficients;
	
	public FactModelStatistical(Algorithms name,
			ModelProperties[] modelProperties, FactCoefficients factCoefficients) {
		super(name, modelProperties);
		this.factCoefficients=factCoefficients;
	}

}
