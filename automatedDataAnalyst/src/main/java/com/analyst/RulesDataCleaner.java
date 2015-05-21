package com.analyst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.datautils.DataUtils;
import com.factengine.Fact;
import com.factengine.FactFactory;
import com.factengine.commanders.ImputationCommander;
import com.factengine.commanders.RowRemovalCommander;
import com.factengine.datasetdescriptors.FactRow;

public class RulesDataCleaner extends RulesExecutor {
	
	
	private ImputationCommander imputationCommander;
	private RowRemovalCommander rowRemovalCommander;
	
	public enum imputationOptions{MEAN,MEDIAN,KNN}
	
	public RulesDataCleaner(DataFrame df, imputationOptions options){
	
		
		super(df,"data_preparation","data_cleaning");
		
	    FactFactory factFactory=new FactFactory();
	    List<FactRow> factsRows=factFactory.getMissingRowFacts(df);
	    switch(options){
		case MEAN:
		    imputationCommander=new ImputationCommander(new DataUtils.meanImputor());
		    break;
		case MEDIAN:
		    imputationCommander=new ImputationCommander(new DataUtils.medianImputor());
		    break;
		case KNN:
		    imputationCommander=new ImputationCommander(new DataUtils.knnImputor());
		    break;
		}
	    rowRemovalCommander=new RowRemovalCommander();
	    kSession.insert(factFactory);
	    factFactory.factInsertor(kSession, factsRows);

	    kSession.insert(df);
	    kSession.insert(imputationCommander);
	    kSession.insert(rowRemovalCommander);
	    

	}
	
	public void cleanData(){
		kSession.fireAllRules();
	}


	public DataFrame getDataFrame(){
		return this.df;
	}
}
