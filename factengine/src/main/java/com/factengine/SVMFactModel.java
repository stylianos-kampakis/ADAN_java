package com.factengine;

/**
 * Support Vector Machine facts.
 * 
 * @author stelios
 *
 */
public class SVMFactModel extends FactModel {
	
	public SVMFactModel(String modelName) {
		
		super(modelName,new ModelProperties[]{ModelProperties.KERNEL_METHOD,
				//<REFERENCE>
				ModelProperties.POOR_SCALABILITY,
				ModelProperties.POOR_MISSING_VALUES,
				ModelProperties.POOR_INTERPRETABILITY,
				ModelProperties.POOR_MIXED_DATA,
				ModelProperties.NUMERICAL_DATA_ONLY,
				//REFERENCE : ESLII
				ModelProperties.SENSITIVE_TO_MONOTONIC_TRANSFORM,
				ModelProperties.SENSITIVE_TO_OUTLIERS,
				
				ModelProperties.GOOD_HIGH_DIMENSIONAL_DATA,
				
				ModelProperties.EXTRACTS_LINEAR_COMBINATIONS
		
		});

	}

}
