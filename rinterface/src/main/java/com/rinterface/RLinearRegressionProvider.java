package com.rinterface;
import java.io.IOException;
import java.util.HashMap;

import com.analysisInterface.ILinearRegression;
import com.analysisInterface.PredictionResultSet;
import com.dataframe.DataFrame;

import rcaller.RCaller;
import rcaller.RCode;

public class RLinearRegressionProvider extends RProviderBase implements ILinearRegression {
	
		
	private boolean modelExists=false;
	
	
	public RLinearRegressionProvider(){
		super();
	}
	
	public RLinearRegressionProvider(String path){
		super(path);
	}

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



}
