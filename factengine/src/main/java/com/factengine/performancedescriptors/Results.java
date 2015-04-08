package com.factengine.performancedescriptors;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Results is used to store ResultStatements. ResultStatements are free text description
 * of something (e.g. "coefficient X is significant at the 5% level").
 * 
 * Special notice needs to be taken, since everyobject that produces a ResultStatement must produce
 * a unique id within object scope. So, for example, a linear regression provider object, can 
 * produce result statements with the id 'lr' plus a random number. Therefore, the component of the 
 * system that produces the final description/presentation knows two things:
 * 
 *  a) This is a description that concerns a linear regression model.
 *  b) This description corresponds to a particular session of linear regression. Running a new session
 *  is going to be indexed by a new random number.
 * 
 * @author stelios
 *
 */
public class Results {
	
	HashMap<String,ResultStatement> results=new HashMap<String,ResultStatement>();
	
	/**
	 * Adds a result to the 'results' hashmap. The map is indexed by the id of the result.
	 * 
	 * @param result
	 */
	public void add(ResultStatement result){
		results.put(result.getId(),result);
	}
	
	public void add(ArrayList<ResultStatement> results){
		
		for (ResultStatement res:results){
			add(res);
		}		
	}
	
	public void add(Results results){
		for(ResultStatement res : this.results.values()){
			add(res);
		}
	}
	
	public HashMap<String, ResultStatement> getResults(){
		return this.results;
	}

}
