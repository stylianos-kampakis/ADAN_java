package com.analysisInterface;


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
public class analysisExecutor {
	
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
	

}
