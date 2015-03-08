package com.rinterface;
import java.io.IOException;
import java.util.HashMap;

import com.analysisInterface.ILinearRegression;
import com.analysisInterface.PredictionResultSet;
import com.dataframe.DataFrame;

import rcaller.RCaller;
import rcaller.RCode;

public class RInterface implements ILinearRegression {
	
	private String RScriptPath;
	RCode code;
	RCaller caller;
	
	
	public RInterface(){
		code=new RCode();
		caller=new RCaller();
	}
	
	public RInterface(String path){
		code=new RCode();
		caller=new RCaller();
		this.setPath(path);
	}

	public String getPath(){
		
		return this.RScriptPath;
	}
	
	public void setPath(String path){
		this.RScriptPath=path;
	}
	
	private void initialize(){
		code.clear();
		caller.cleanRCode();
		caller.setRscriptExecutable(this.RScriptPath);
	}
	
	public String runLinearRegression(String response,DataFrame df){
		
		this.initialize();
		
		String dfR=df.getRDataFrame();
		code.addRCode(dfR);
		
		String template="m1=lm(<RESPONSE>~.,data=DataFrame)";
		template=template.replace("<RESPONSE>", response);
		
		
		code.addRCode(template);

		caller.setRCode(code);
		caller.runAndReturnResult("m1");
		try {
			String result = caller.getParser().getXMLFileAsString();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

	public String runLinearRegression(String response,DataFrame df,String[] covariates){
		
		this.initialize();
		
		String dfR=df.getRDataFrame();
		
		String template="m1=lm(<RESPONSE>~";
		for(String covariate :covariates){
			
			template=template+covariate+"+";
		}
			
		template=template+",data=DataFrame)";
		template=template.replace("+,", ",");
		
		template=template.replace("<RESPONSE>", response);
		
	
		code.addRCode(dfR);
		code.addRCode(template);

		caller.setRCode(code);
		caller.runAndReturnResult("m1");
		try {
			String result = caller.getParser().getXMLFileAsString();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void fit(String response, DataFrame df) {
		// TODO Auto-generated method stub
		
	}

	public void fit(String response, DataFrame df, String[] covariates) {
		// TODO Auto-generated method stub
		
	}

	public void predict(DataFrame df) {
		// TODO Auto-generated method stub
		
	}

	public PredictionResultSet returnPrediction() {
		// TODO Auto-generated method stub
		return null;
	}

	public PredictionResultSet returnFitted() {
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
