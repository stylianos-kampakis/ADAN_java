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
	int numColumns;
	
	FactRow(int row, int numColumns){
		this.row=row;
		this.numColumns=numColumns;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getNumColumns(){
		return numColumns;
	}
	
	public int getNumberMissingValues(){
		return numberMissingValues;
	}
	
	public void setNumberMissingValues(int numMissingValues){
		this.numberMissingValues=numMissingValues;
	}
	
	
	public double getPercentageMissingValues(){
		return (double)getNumberMissingValues()/(double)getNumColumns();
	}
	
}
