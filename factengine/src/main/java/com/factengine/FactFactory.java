package com.factengine;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;

public class FactFactory {

	/**
	 * This method gets the number of missing values for each row in the dataframe. It
	 * returns a list of FactRow.
	 * 
	 * 
	 * @param df
	 * @return
	 */
	public ArrayList<FactRow> getMissingRowFacts(DataFrame df){
		
		ArrayList<FactRow> factRow=new ArrayList<FactRow>();
		FactRow fact;
		
		for(int i=1;i<=df.getRowsNumber();i++){
			fact=new FactRow(i);
			try {
				fact.setNumberMissingValues(df.applyCountLogicalToRow(df.new CheckMissingCount(),i));
				factRow.add(fact);
			} catch (DataFrameIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		return factRow;		
	}
	
	/**
	 * 
	 * Utility method used to insert lists of facts inside the kieSession.
	 * 
	 * @param kSession
	 * @param facts
	 */
	public void factInsertor(KieSession kSession, List<?> facts){
		for(Object fact : facts){
			kSession.insert(fact);
		}
	}
	
}
