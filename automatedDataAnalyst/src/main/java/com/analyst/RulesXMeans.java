package com.analyst;

import com.analysisInterface.providers.supervised.IModel;
import com.dataframe.DataFrame;

public class RulesXMeans extends RulesExecutorModel {

	public RulesXMeans(DataFrame df, String sessionName, IModel analysisExecutor) {
		super(df,"clustering_preparation", "clustering_preparation", analysisExecutor);
		// TODO Auto-generated constructor stub
		
	}

}
