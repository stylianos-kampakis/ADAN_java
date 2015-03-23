package com.factengine;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;

/**
 * The FactFactory class contains methods that get 
 * as input a DataFrame or another object and then comes back with an ArrayList of facts.
 * These facts should be inserted in a kieSession and reasoned upon.
 * 
 * 
 * @author stelios
 *
 */
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
			//The fact row is constructed taking the row number as the argument
			fact=new FactRow(i);
			try {
				fact.setNumberMissingValues(df.getNumMissingValuesForRow(i));
				factRow.add(fact);
			} catch (DataFrameIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		return factRow;		
	}
	
	public ArrayList<FactColumn> getMissingColumnFacts(DataFrame df){
		
		ArrayList<FactColumn> factColumn=new ArrayList<FactColumn>();
		FactColumn fact;
		
		for(int i=1;i<=df.getColumnsNumber();i++){
			//The fact row is constructed taking the row number as the argument
			fact=new FactColumn(i);
			try {
				fact.setNumberMissingValues(df.getNumMissingValuesForColumn(i));
				factColumn.add(fact);
			} catch (DataFrameIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
		return factColumn;		
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
