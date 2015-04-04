package com.factengine.commanders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.datautils.DataUtils;
import com.datautils.IImputor;

/**
 * Imputation commander is used by the kieSession to store the rows for which imputation should be performed.
 * 
 * @author stelios
 *
 */
public class ImputationCommander extends ExecutionCommander {
private Set<Integer> rows=new HashSet<Integer>();
IImputor imputor;

public ImputationCommander(IImputor imputor){
	this.imputor=imputor;
}

public Set<Integer> getRows(){
	return this.rows;
}


public void addRow(int i){
	this.rows.add(i);
}

public void impute(DataFrame df){
	try {
		HashMap<Integer, ArrayList<DataPoint>> imputed= DataUtils.impute(df, getRows(),imputor);
		df.setRows(imputed);
	} catch (DataFrameIndexException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
}
