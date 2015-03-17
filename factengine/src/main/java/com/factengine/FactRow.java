package com.factengine;

/**
 * A FactRow is a class that contains all important information for a row. This is used to reason 
 * upon later by the kieSession.
 * 
 * @author stelios
 *
 */
public class FactRow extends Fact {

	int numberMissingValues;
	int row;
	
	FactRow(int row){
		this.row=row;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getNumberMissingValues(){
		return numberMissingValues;
	}
	
	public void setNumberMissingValues(int numMissingValues){
		this.numberMissingValues=numMissingValues;
	}
	
}
