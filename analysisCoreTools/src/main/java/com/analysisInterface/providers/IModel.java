package com.analysisInterface.providers;

import com.analysisInterface.parameters.ParameterSet;
import com.dataframe.DataFrame;
import com.factengine.Response;

public interface IModel {	
	
	//assumes that all the covariates will be used
	//If the interface is R then we can define interactions, such as covariates={"variable1*variable2"}
	public void fit(Response response,DataFrame df,ParameterSet parameters);
	public void fit(Response response,DataFrame df,String[] covariates,ParameterSet parameters);
	
	//these two methods ensure that a model has a 'default' parameter set ready as well.
	public void fit(Response response,DataFrame df);
	public void fit(Response response,DataFrame df,String[] covariates);
	
	
	
//Predict assumes that the names of the response and the covariates remain the same.
//Additional flexibility (allowing for other names for responses or covariates) should NOT
//be allowed, since this can be a source of errors. It is better to force the analyst to re-examine
//the names of the variables.
	public double[] predict(DataFrame df,ParameterSet parameters);
	
	//gets the fittedValues
	public double[] getFitted();
	
	public boolean modelExists();

}
