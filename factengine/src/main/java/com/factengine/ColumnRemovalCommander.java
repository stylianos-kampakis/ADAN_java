package com.factengine;

import java.util.HashSet;
import java.util.Set;

import com.dataframe.DataFrame;

public class ColumnRemovalCommander extends ExecutionCommander {
	private Set<Integer> columns=new HashSet<Integer>();

	public Set<Integer> getColumns(){
		return columns;
	}


	public void addColumn(int i){		
		this.columns.add(i);
	}
	
	public void removeColumns(DataFrame df){
		df.dropColumns(getColumns());
	}
}
