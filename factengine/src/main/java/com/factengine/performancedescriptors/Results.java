package com.factengine.performancedescriptors;

import java.util.ArrayList;

public class Results {
	
	ArrayList<ResultStatement> results=new ArrayList<ResultStatement>();
	
	public void add(ResultStatement result){
		this.results.add(result);
	}
	
	public void add(ArrayList<ResultStatement> results){
		
		for (ResultStatement res:results){
			this.results.add(res);
		}
		
	}
	
	public ArrayList<ResultStatement> getResults(){
		return this.results;
	}

}
