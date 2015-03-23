package com.factengine;

public class FactColumn extends Fact {
	
	int numberMissingValues;
	int column;
	
	FactColumn(int column){
		this.column=column;
	}
	
	public int getColumn(){
		return column;
	}
	
	public int getNumberMissingValues(){
		return numberMissingValues;
	}
	
	public void setNumberMissingValues(int numMissingValues){
		this.numberMissingValues=numMissingValues;
	}

}
