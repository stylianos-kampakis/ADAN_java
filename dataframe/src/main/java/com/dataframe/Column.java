package com.dataframe;


import java.util.Set;

import com.dataframe.DataPointSubType;


public class Column {
	protected Set<String> factors;
	protected DataPointType type;
	protected DataPointSubType subtype=DataPointSubType.NONE;
	
	String name;
	
	public Column(String name){

		if(name.isEmpty() || name.equals("\"\"")){

			this.name="\"unknown_name\"";
			
		}
		else{
		this.name=name;
		}
		
	}
	
public String getName(){
	return this.name;
}
	
	public void setType(DataPointType type){
		
		this.type=type;
	}
	
	public void setSubType(DataPointSubType subtype){
		
		this.subtype=subtype;
	}
	
	public DataPointType getType(){
		return this.type;
	}
	
	public DataPointSubType getSubType(){
		return this.subtype;
	}
	
	public Set<String> getFactors(){
		return this.factors;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	
	
}
