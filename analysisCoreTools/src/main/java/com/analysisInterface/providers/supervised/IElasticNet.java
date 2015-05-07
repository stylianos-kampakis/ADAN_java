package com.analysisInterface.providers.supervised;

import com.analysisInterface.parameters.ParameterSet;
import com.analysisInterface.results.Coefficients;
import com.dataframe.DataFrame;
import com.factengine.Response;

public class IElasticNet implements IModel, IStatisticalMethod, IRegression {

	public double getAIC() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getBIC() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getLogLikelihood() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Coefficients getCoefficients() {
		// TODO Auto-generated method stub
		return null;
	}

	public void fit(Response response, DataFrame df, ParameterSet parameters) {
		// TODO Auto-generated method stub

	}

	public void fit(Response response, DataFrame df, String[] covariates,
			ParameterSet parameters) {
		// TODO Auto-generated method stub

	}

	public void fit(Response response, DataFrame df) {
		// TODO Auto-generated method stub

	}

	public void fit(Response response, DataFrame df, String[] covariates) {
		// TODO Auto-generated method stub

	}

	public double[] predict(DataFrame df, ParameterSet parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] getFitted() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean modelExists() {
		// TODO Auto-generated method stub
		return false;
	}

}
