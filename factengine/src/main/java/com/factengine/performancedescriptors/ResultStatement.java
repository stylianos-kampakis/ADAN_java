package com.factengine.performancedescriptors;


/**
 * The result statement is used by the rule engine in order to describe the results
 * of the analysis and then produce a natural language description of it.
 * 
 * The 'description' variable holds the description of an event that took place.
 * The 'id' variable is used in order to identify clusters of descriptions.
 * 
 * @author stelios
 *
 */
public class ResultStatement {
	
private String description;
private String id;

public ResultStatement(String description,String id){
	this.description=description;
	this.id=id;
}

public String getDescription(){
	return description;
}

public String getId(){
	return id;
}
	

}
