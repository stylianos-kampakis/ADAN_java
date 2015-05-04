package com.analyst;

import com.analysisInterface.providers.supervised.IRansac;
import com.dataframe.DataFrame;

public class RulesRANSAC extends RulesExecutorModel {
	
	public RulesRANSAC(DataFrame df,IRansac analysisExecutor) {
		super(df, "ransac",analysisExecutor);

	}

}
