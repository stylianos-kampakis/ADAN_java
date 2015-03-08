package com.analysisInterface;

public interface INeuralNetworks extends IClassification, IRegression {
	
	public void setNumLayers(int numLayers);
	
	//the array holds the number of neurons for each layer
	public void setNumNeurons(Double[] numNeurons);

}
