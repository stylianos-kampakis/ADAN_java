package com.analysisInterface.providers;

import com.analysisInterface.parameters.ParameterSet;
import com.dataframe.DataFrame;

public interface IModel {	
	
	//assumes that all the covariates will be used
	//If the interface is R then we can define interactions, such as covariates={"variable1*variable2"}
	//The functions should return some representation of the object in String format (such as XML)
	public String fit(String response,DataFrame df,ParameterSet parameters);
	public String fit(String response,DataFrame df,String[] covariates,ParameterSet parameters);
	
//Predict assumes that the names of the response and the covariates remain the same.
//Additional flexibility (allowing for other names for responses or covariates) should NOT
//be allowed, since this can be a source of errors. It is better to force the analyst to re-examine
//the names of the variables.
	public double[] predict(DataFrame df,ParameterSet parameters);
	
	//gets the fittedValues
	public double[] getFitted();

}
