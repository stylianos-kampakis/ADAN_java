package com.analysisInterface;

import java.util.ArrayList;

import com.dataframe.DataFrame;

public interface IModel {	

	
	//The name of the response must either be provided or must be
	//set when executing the fit functions. The predict function does not change
	//the name of the response, since a new dataset might not follow the original convention.
	String response=null;
	
	//Like with the response, the covariates' names get saved when executing 'fit'
	String[] covariates=null;
	
	//assumes that all the covariates will be used
	//If the interface is R then we can define interactions, such as covariates={"variable1*variable2"}
	//The functions should return some representation of the object in String format (such as XML)
	public String fit(String response,DataFrame df);
	public String fit(String response,DataFrame df,String[] covariates);
	
//Predict assumes that the names of the response and the covariates remain the same.
//Additional flexibility (allowing for other names for responses or covariates) should NOT
//be allowed, since this can be a source of errors. It is better to force the analyst to re-examine
//the names of the variables.
	public double[] predict(DataFrame df);
	
	//getters for the fittedValues and the predictedValues
	public double[] returnPrediction();
	public double[] returnFitted();

}
