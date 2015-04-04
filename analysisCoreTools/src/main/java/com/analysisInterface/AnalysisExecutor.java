package com.analysisInterface;

import java.io.File;

import com.analysisInterface.parameters.ParameterSet;
import com.analysisInterface.providers.IGaussianProcess;
import com.analysisInterface.providers.IKnn;
import com.analysisInterface.providers.ILinearRegression;
import com.analysisInterface.providers.ILogisticRegression;
import com.analysisInterface.providers.IMixedEffectsModelClassification;
import com.analysisInterface.providers.IMixedEffectsModelLinearRegression;
import com.analysisInterface.providers.INaiveBayes;
import com.analysisInterface.providers.INegativeBinomialRegression;
import com.analysisInterface.providers.INeuralNetworks;
import com.analysisInterface.providers.IPoissonRegression;
import com.analysisInterface.providers.IRandomForests;
import com.analysisInterface.providers.ISvm;
import com.analysisInterface.results.PredictionResultSet;
import com.dataframe.DataFrame;

/**AnalysisExecutor
 * 
 * This class is the central commander for any kind of analysis. 
 * It can load a configuration file outlying which technologies and libraries are to be used
 * for each kind of algorithm.
 * 
 *  This design decouples the development of specific interfaces 
 *  for languages/tools (R, Python, Java libs) etc.
*
* @author Stylianos Kampakis
* @version 0.1 March 3, 2015.
*/
public class AnalysisExecutor {
	
	DataFrame df;
		
	//Predictive models and fit
	ILinearRegression linearRegressionProvider;
	IPoissonRegression poissonRegressionProvider;
	ILogisticRegression logisticRegressionProvider;
	INegativeBinomialRegression negativeBinomialRegressionProvider;
	
	IMixedEffectsModelLinearRegression mixedEffectsModelLinearRegressionProvider;
	IMixedEffectsModelClassification mixedEffectsModelClassification;

	
	INeuralNetworks neuralNetworksProvider;	
	IRandomForests randomForestsProvider;
	IKnn knnProvider;
	ISvm svmProvider;
	IGaussianProcess gaussianProcessProvider;
	INaiveBayes naivesBayesProvider;
	
	public void setConfigFile(File path){
		
	}
	
	
	/**
	 * Creates new providers, anything stored in the old ones is deleted.
	 * 
	 */
	public void initialize(){
		
	}
	
	public void setDataFrame(DataFrame df){
		this.df=df;
	}
	
	
	public PredictionResultSet crossVal(int numFolds,int numRepeats,Algorithms algorithm){
		PredictionResultSet results=new PredictionResultSet();
		
		switch(algorithm){
		case LINEAR_REGRESSION:
		
		}
		
		return results;
		
	}
	
	
	public PredictionResultSet fit(Algorithms algorithm,ParameterSet parameters){
		PredictionResultSet results=new PredictionResultSet();
		
		switch(algorithm){
		case LINEAR_REGRESSION:
		
		}
		
		return results;
		
	}

	
	public PredictionResultSet predict(Algorithms algorithm,ParameterSet parameters) {
		PredictionResultSet results=new PredictionResultSet();
		
		switch(algorithm){
		case LINEAR_REGRESSION:
		
		}
		
		return results;
		
	}


}
