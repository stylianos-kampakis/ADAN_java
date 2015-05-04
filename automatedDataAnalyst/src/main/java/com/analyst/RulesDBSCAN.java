package com.analyst;

import com.analysisInterface.providers.supervised.IModel;
import com.dataframe.DataFrame;

public class RulesDBSCAN extends RulesExecutorModel {

	public RulesDBSCAN(DataFrame df, String sessionName, IModel analysisExecutor) {
		super(df, "clustering_preparation", analysisExecutor);
		// TODO Auto-generated constructor stub
	}

}
