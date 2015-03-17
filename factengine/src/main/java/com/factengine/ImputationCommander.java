package com.factengine;

import java.util.HashSet;
import java.util.Set;

/**
 * Imputation commander is used by the kieSession to store the rows for which imputation should be performed.
 * 
 * @author stelios
 *
 */
public class ImputationCommander extends ExecutionCommander {
private Set<Integer> rows=new HashSet<Integer>();

public Set<Integer> getRows(){
	return this.rows;
}


public void addRow(int i){
	
	this.rows.add(i);
}
	
}
