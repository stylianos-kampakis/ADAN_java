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
	private String response;
	
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
			this.response=response;
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
			this.response=response;
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

	public double[] returnPrediction() throws IllegalStateException {
		if(modelExists=true){
			String template="results=predict(lm1)";
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

	public double[] returnFitted() {
		// TODO Auto-generated method stub
		return null;
	}

	public double AIC() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double BIC() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double likelihood() {
		// TODO Auto-generated method stub
		return 0;
	}

	public HashMap<String, Double> returnCoefficients() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double Rsquare() {
		// TODO Auto-generated method stub
		return null;
	}

}
