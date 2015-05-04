package com.analysisInterface.providers.plotting;

import com.dataframe.DataFrame;
import com.factengine.Response;
import com.factengine.performancedescriptors.ResultStatementPlot;

public interface IPlotter {
	
	public ResultStatementPlot makeHistogram(DataFrame df, Response res);

}
