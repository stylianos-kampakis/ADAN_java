package com.analyst;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.analyst.RulesDataCleaner.imputationOptions;
import com.dataframe.DataFrame;
import com.factengine.Response;
import com.factengine.performancedescriptors.Results;



public class RulesExecutor {
	
	private boolean hasExecuted=false;
	
	protected DataFrame df;
	protected KieServices ks;
	protected KieContainer kContainer;
	protected KieSession kSession;
	
	public RulesExecutor(DataFrame df,String sessionName){
		
		this.df=df;
		
		ks = KieServices.Factory.get();
	    kContainer = ks.getKieClasspathContainer();
	    kSession = kContainer.newKieSession(sessionName);
	
}
	
	public Results analyzeData(Response response){
		return null;
	}
	
	public boolean hasExecuted(){
		return hasExecuted;
	}
}
