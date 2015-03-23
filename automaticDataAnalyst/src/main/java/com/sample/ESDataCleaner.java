package com.sample;

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
import com.factengine.FactFactory;
import com.factengine.FactRow;
import com.factengine.ImputationCommander;
import com.factengine.RowRemovalCommander;

public class ESDataCleaner {
	
	DataFrame df;
	KieServices ks;
	KieContainer kContainer;
	KieSession kSession;
	ImputationCommander imputationCommander;
	RowRemovalCommander rowRemovalCommander;
	
	public enum imputationOptions{MEAN,MEDIAN,KNN}
	
	public ESDataCleaner(DataFrame df){
	
		this.df=df;
		
		ks = KieServices.Factory.get();
	    kContainer = ks.getKieClasspathContainer();
	    kSession = kContainer.newKieSession("ksession-rules");


	    FactFactory factFactory=new FactFactory();
	    List<FactRow> factsRows=factFactory.getMissingRowFacts(df);
	    imputationCommander=new ImputationCommander();
	    rowRemovalCommander=new RowRemovalCommander();
   	
	    factFactory.factInsertor(kSession, factsRows);

	    kSession.insert(df);
	    kSession.insert(imputationCommander);
	    kSession.insert(rowRemovalCommander);
	    
		kSession.fireAllRules();


	}
	
	public DataFrame cleanData(imputationOptions options) throws DataFrameIndexException{
		df=imputeData(options);
		df.dropRows(rowRemovalCommander.getRows());
		
		return df;
	}
	
	public DataFrame imputeData(imputationOptions options) throws DataFrameIndexException{
		HashMap<Integer, ArrayList<DataPoint>> imputed = null;
		
		
		switch(options){
		case MEAN:
		imputed= DataUtils.imputeMean(df, imputationCommander.getRows());
		case MEDIAN:
		imputed= DataUtils.imputeMedian(df, imputationCommander.getRows());
		case KNN:
		imputed= DataUtils.imputeKnn(df, imputationCommander.getRows());
		}
		
		df.setRows(imputed);
		
		return df;
	}
}
