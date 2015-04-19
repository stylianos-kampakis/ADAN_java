package com.factengine.commanders;

import java.util.HashSet;
import java.util.Set;

import com.analyst.RulesExecutor;

/**
 * ExecutionCommander is a parent class that other commanders inherit from.
 * The Commander classes are used in order to facilitate the interaction of the kieSession with the
 * rest of the platform.
 * 
 * Each commander is used in order to store information that has been produced by the kieSession. This information
 * can then be used to execute various actions. See specific implementations (e.g. ImputationCommander) for examples.
 * 
 * @author stelios
 *
 */
public class ExecutionCommander {

	private Set<RulesExecutor> models=new HashSet<RulesExecutor>();
	
	
	public void addModel(RulesExecutor executor){
		models.add(executor);
	}
	
	public Set<RulesExecutor> getModels(){
		return(models);
	}
	
	
}

