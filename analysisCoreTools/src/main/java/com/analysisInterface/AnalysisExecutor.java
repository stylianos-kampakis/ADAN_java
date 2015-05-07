package com.analysisInterface;

import java.io.File;

import com.analysisInterface.parameters.ParameterSet;
import com.analysisInterface.providers.supervised.IGaussianProcess;
import com.analysisInterface.providers.supervised.IKnn;
import com.analysisInterface.providers.supervised.ILinearRegression;
import com.analysisInterface.providers.supervised.ILogisticRegression;
import com.analysisInterface.providers.supervised.IMixedEffectsModelClassification;
import com.analysisInterface.providers.supervised.IMixedEffectsModelLinearRegression;
import com.analysisInterface.providers.supervised.INaiveBayes;
import com.analysisInterface.providers.supervised.INegativeBinomialRegression;
import com.analysisInterface.providers.supervised.INeuralNetworks;
import com.analysisInterface.providers.supervised.IPoissonRegression;
import com.analysisInterface.providers.supervised.IRandomForests;
import com.analysisInterface.providers.supervised.ISvm;
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

//The class has been deprecated in favour of a different design
@Deprecated
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
