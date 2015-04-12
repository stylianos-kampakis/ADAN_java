package com.analysis.providers.statisticaltests;

import com.analysisInterface.parameters.ParameterSet;
import com.dataframe.DataFrame;

public interface IStatisticalTest {
	
	
	public double test(DataFrame df,String... variables);

}
