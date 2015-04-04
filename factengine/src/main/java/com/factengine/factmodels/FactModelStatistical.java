package com.factengine.factmodels;

import com.analysisInterface.Algorithms;
import com.factengine.analysisdescriptors.FactCoefficients;
import com.factengine.descriptionenums.ModelProperties;

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
