package com.factengine.performancedescriptors;


/**
 * The result statement is used by the rule engine in order to describe the results
 * of the analysis and then produce a natural language description of it.
 * 
 * @author stelios
 *
 */
public class ResultStatement {
	
private String description;

public ResultStatement(String description){
	this.description=description;
}

public String getDescription(){
	return description;
}
	

}
