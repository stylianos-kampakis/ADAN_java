package com.factengine;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;

import com.analysisInterface.parameters.ParameterSet;
import com.analysisInterface.results.PredictionResultSet;
import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.factengine.datasetdescriptors.FactColumn;
import com.factengine.datasetdescriptors.FactDataFrame;
import com.factengine.datasetdescriptors.FactRow;
import com.factengine.descriptionenums.DatasetDescriptionTags;
import com.factengine.factmodels.FactModel;
import com.factengine.performancedescriptors.FactPerformanceRegression;

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
		
		for(int i=1;i<=df.getNumberRows();i++){
			//The fact row is constructed taking the row number as the argument
			fact=new FactRow(i,df.getNumberColumns());
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
		
		for(int i=1;i<=df.getNumberColumns();i++){
			//The fact row is constructed taking the row number as the argument
			fact=new FactColumn(i,df.getNumberRows());
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
	
	/**
	 * 
	 * This functions is used after a model has been executed in a KieSession in order to convert
	 * the results of the model to a FactPerformanceRegressionModel.
	 * 
	 * @param model
	 * @param results
	 * @param parameters
	 * @return
	 */
	public FactPerformanceRegression createRegressionFacts(FactModel model,PredictionResultSet results, ParameterSet parameters){
		return new FactPerformanceRegression(model,results,parameters);
	}



	
	

	
}
