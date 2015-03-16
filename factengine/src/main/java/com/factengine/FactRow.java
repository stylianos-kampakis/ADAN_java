package com.factengine;

public class FactRow extends Fact {

	int numberMissingValues;
	int row;
	//int numberCategorical;
	//int numberNumerical;
	
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
