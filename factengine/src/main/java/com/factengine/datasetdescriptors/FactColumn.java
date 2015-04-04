package com.factengine.datasetdescriptors;

import com.factengine.Fact;

public class FactColumn extends Fact {
	
	int numberMissingValues;
	int column;
	int numRows;
	
	public FactColumn(int column,int numRows){
		this.column=column;
		this.numRows=numRows;
	}
	
	public int getColumn(){
		return column;
	}
	
	public int getNumberMissingValues(){
		return numberMissingValues;
	}
	
	public int getNumRows(){
		return this.numRows;
	}
	
	public void setNumberMissingValues(int numMissingValues){
		this.numberMissingValues=numMissingValues;
	}
	
	public double getPercentageMissingValues(){
		return (double)getNumberMissingValues()/(double)getNumRows();
	}

}
