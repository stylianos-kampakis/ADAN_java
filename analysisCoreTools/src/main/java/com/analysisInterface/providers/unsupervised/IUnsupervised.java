package com.analysisInterface.providers.unsupervised;

import com.analysisInterface.parameters.ParameterSet;
import com.dataframe.DataFrame;

public interface IUnsupervised {
	
	public void fit(DataFrame df, ParameterSet parameters);

}
