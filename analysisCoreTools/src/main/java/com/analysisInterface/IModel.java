package com.analysisInterface;

import java.util.ArrayList;

import com.dataframe.DataFrame;

public interface IModel {	
	/* Normal pipeline to be followed by each class
	 * 
	 * 1. First run fit
	 * 2. Fit stores the name of the response and the covariates
	 * 3. Fit stores the original and the fitted values in the fittedValues variable
	 * 4. Use predict for test set
	 * 5. predict stores the new results every time in the predictedValues variable
	 * 
	 */
	
	
	//stores the original and the fitted values after training
	PredictionResultSet fittedValues=null;
	//stores the predicted and the original values after a test prediction
	PredictionResultSet predictedValues=null;

	//The name of the response must either be provided or must be
	//set when executing the fit functions. The predict function does not change
	//the name of the response, since a new dataset might not follow the original convention.
	
	String response=null;
	
	//Like with the response, the covariates' names get saved when executing 'fit'
	String[] covariates=null;
	
	//assumes that all the covariates will be used
	//If the interface is R then we can define interactions, such as covariates={"variable1*variable2"}
	public void fit(String response,DataFrame df);
	public void fit(String response,DataFrame df,String[] covariates);
	
//Predict assumes that the names of the response and the covariates remain the same.
//Additional flexibility (allowing for other names for responses or covariates) should NOT
//be allowed, since this can be a source of errors. It is better to force the analyst to re-examine
//the names of the variables.
	public void predict(DataFrame df);
	
	//getters for the fittedValues and the predictedValues
	public PredictionResultSet returnPrediction();
	public PredictionResultSet returnFitted();

}
