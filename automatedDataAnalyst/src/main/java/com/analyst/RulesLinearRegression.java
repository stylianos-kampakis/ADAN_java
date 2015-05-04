package com.analyst;

import java.util.ArrayList;

import org.drools.core.WorkingMemoryEntryPoint;
import org.drools.core.marshalling.impl.ProtobufMessages.FactHandle;
import org.kie.api.runtime.rule.EntryPoint;

import com.analysisInterface.AnalysisExecutor;
import com.analysisInterface.providers.supervised.ILinearRegression;
import com.dataframe.DataFrame;
import com.factengine.Response;
import com.factengine.performancedescriptors.FactPerformanceRegressionStatistical;
import com.factengine.performancedescriptors.ResultStatement;
import com.factengine.performancedescriptors.Results;

public class RulesLinearRegression extends RulesExecutorModel{

	
	private boolean hasExecuted=false;
	
	private org.kie.api.runtime.rule.FactHandle handleResponse;
	private org.kie.api.runtime.rule.FactHandle handleResults;
	private Results results=new Results();
	
	public RulesLinearRegression(DataFrame df,ILinearRegression analysisExecutor) {
		//the constructor initializes for the drools session "statistical" corresponding to the statistical.drl
		super(df,"regression_statistical_linear_regression",analysisExecutor);
		handleResults=kSession.insert(results);
		kSession.insert(analysisExecutor);

	}
	
	public Results analyzeData(Response response){
		//We have to remove any old responses and add the current response variable.
		//The documentation was not clear on how to replace the deprecated 'retract' method.
		kSession.retract(handleResponse);
		kSession.retract(handleResults);
		kSession.insert(response);
		
		//we have to clean up any old results before running the session again
		results=new Results();
		kSession.insert(results);
		
		kSession.fireAllRules();
		hasExecuted=true;
		
		return results;
	}
	
	
	

}