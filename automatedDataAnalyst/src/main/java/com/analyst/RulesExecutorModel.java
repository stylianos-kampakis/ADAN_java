package com.analyst;

import com.analysisInterface.providers.supervised.IModel;
import com.dataframe.DataFrame;

public class RulesExecutorModel extends RulesExecutor {

	public final IModel analysisExecutor;
	
	public RulesExecutorModel(DataFrame df,String packageName, String sessionName, IModel analysisExecutor) {
		super(df, packageName, sessionName);
		this.analysisExecutor=analysisExecutor;
	}

}
