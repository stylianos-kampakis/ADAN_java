package com.rinterface;
import java.io.IOException;
import java.util.HashMap;

import com.analysisInterface.parameters.ParameterSet;
import com.analysisInterface.providers.ILinearRegression;
import com.analysisInterface.results.PredictionResultSet;
import com.dataframe.DataFrame;

import rcaller.RCaller;
import rcaller.RCode;

public class RLinearRegressionProvider extends RProviderBase implements ILinearRegression {
	
	//This variable indicates whether a model exists or not. Once a model has been fit
	//to a dataset, then this variable is set to true.
	private boolean modelExists=false;
	
	
	public RLinearRegressionProvider(){
		super();
	}
	
	public RLinearRegressionProvider(String path){
		super(path);
	}

	/**
	 * Fits a linear regression model to the specified dataframe, by using the function 'lm' of R.
	 * It assumes that all the variables, besides the response, are covariates.
	 * 
	 */
	public String fit(String response, DataFrame df) {
		initialize();
		
		String dfR=df.getRDataFrame();
		code.addRCode(dfR);
		
		String template="lm1=lm(<RESPONSE>~.,data=DataFrame)";
		template=template.replace("<RESPONSE>", response);
		
		
		code.addRCode(template);

		caller.setRCode(code);
		caller.runAndReturnResultOnline("lm1");
		try {
			String result = caller.getParser().getXMLFileAsString();	
			modelExists=true;
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;		
	}

	/**
	 * Fits a linear regression model to the specified dataframe, by using the function 'lm' of R.
	 * The user needs to specify the names of the covariates in the 'Covariates' argument.
	 * 
	 */
	public String fit(String response, DataFrame df, String[] covariates) {
		initialize();
		
		String dfR=df.getRDataFrame();
		
		String template="lm1=lm(<RESPONSE>~";
		for(String covariate :covariates){
			
			template=template+covariate+"+";
		}
			
		template=template+",data=DataFrame)";
		template=template.replace("+,", ",");
		
		template=template.replace("<RESPONSE>", response);
		

		code.addRCode(dfR);
		code.addRCode(template);

		caller.setRCode(code);
		caller.runAndReturnResultOnline("lm1");
		
		try {
			String result = caller.getParser().getXMLFileAsString();
			modelExists=true;
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * Makes predictions for a new dataset. The model should have been fit first to a dataset, otherwise
	 * the function will not run.
	 * 
	 * 
	 */
	public double[] predict(DataFrame df) throws IllegalStateException {
		if(modelExists=true){
		String dfR=df.getRDataFrame();
		
		String template="results=predict(lm1,data="+dfR.replace("DataFrame=", "")+")";
		code.clear();
		code.addRCode(template);
		caller.setRCode(code);
		caller.runAndReturnResultOnline("results");
		double[] results=caller.getParser().getAsDoubleArray("results");
		return results;
		}
		else{
			throw new IllegalStateException("There must a model that has been fit first.");
		}
	}



	/**
	 * Gets the fitted values from the last fit of the model to a dataset.
	 */
	public double[] getFitted() {
		if(modelExists=true){
			String template="results=fitted(lm1)";
			code.clear();
			code.addRCode(template);
			caller.setRCode(code);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results;
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}

	/**
	 * Gets the Akaike Information Criterion from the last fit of the model.
	 * 
	 */
	public double getAIC() {
		if(modelExists=true){
			String template="results=AIC(lm1)";
			code.clear();
			code.addRCode(template);
			caller.setRCode(code);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results[0];
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}

	/**
	 * Gets the Bayesian Information Criterion from the last fit of the model.
	 * 
	 */
	public double getBIC() {
		if(modelExists=true){
			String template="results=BIC(lm1)";
			code.clear();
			code.addRCode(template);
			caller.setRCode(code);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results[0];
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}
	

	
	/**
	 * Gets the log likelihood from the last fit of the model.
	 * 
	 */
	public double getLogLikelihood() {
		if(modelExists=true){
			String template="results=summary(logLik(lm1))[[1]]";
			code.clear();
			code.addRCode(template);
			caller.setRCode(code);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results[0];
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}

	
	
	/**
	 * Gets the coefficients from the last fit of the model.
	 * 
	 */
	public HashMap<String,Double> getCoefficients() {
		if(modelExists=true){
			
			HashMap<String,Double> results=new HashMap<String,Double>();
			
			String template="results=coef(lm1)";
			code.clear();
			code.addRCode(template);
			caller.setRCode(code);
			caller.runAndReturnResultOnline("names(results)");
			String[] names=caller.getParser().getAsStringArray("names(results)");
			caller.runAndReturnResultOnline("results");
			double[] coefs=caller.getParser().getAsDoubleArray("results");
			
			
			for(int i=0;i<names.length;i++){
				results.put(names[i], coefs[i]);
			}
			
			return results;
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}
	
	/**
	 * Gets the R squared from the last fit of the model.
	 * 
	 */
	public double rSquared() {
		if(modelExists=true){
			String template="results=summary(lm1)$r.squared";
			code.clear();
			code.addRCode(template);
			caller.setRCode(code);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results[0];
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}
	
	/**
	 * Gets the adjusted r squared from the last fit of the model.
	 * 
	 */
	public double adjustedRSquared() {
		if(modelExists=true){
			String template="results=summary(lm1)$adj.r.squared";
			code.clear();
			code.addRCode(template);
			caller.setRCode(code);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results[0];
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}
	
	/**
	 * Gets the p-values of the coefficients.
	 * 
	 */
	public double getPvalueCoefficients() {
		if(modelExists=true){
			String template="results=summary(lm1)$coefficients[,4]";
			code.clear();
			code.addRCode(template);
			caller.setRCode(code);
			caller.runAndReturnResultOnline("results");
			try {
				System.out.println(caller.getParser().getXMLFileAsString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results[0];
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}

	//These three methods are 'inactive'. They have to be implemented because of inheritance,
	//but they don't carry any meaning for linear regression, unless a regularizer would be
	//to be used (which is not used in this provider).
	public String fit(String response, DataFrame df, ParameterSet parameters) {

		return fit(response,df,parameters);
	}

	public String fit(String response, DataFrame df, String[] covariates,
			ParameterSet parameters) {
		
		return fit(response,df,covariates);
	}

	public double[] predict(DataFrame df, ParameterSet parameters) {
		
		return predict(df);
		
	}



}
