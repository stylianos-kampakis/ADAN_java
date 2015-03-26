package com.sample;

import java.io.IOException;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrame;
import com.datautils.DataUtils;
import com.factengine.ColumnRemovalCommander;
import com.factengine.FactFactory;
import com.factengine.FactRow;
import com.factengine.ImputationCommander;
import com.factengine.RowRemovalCommander;

public class PredictiveAnalysistest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
        
        String path="C:\\iris_missing.csv";
    	DataFrame df=new DataFrame();
    	df.readCSV(path);

    	FactFactory factFactory=new FactFactory();
    	List<FactRow> factsRows=factFactory.getDataFrameFacts(df);
    	
    	
    	factFactory.factInsertor(kSession, factsRows);
			
		//System.out.println(kSession.getFactCount());	
    	kSession.insert(df);
    	kSession.Ii

		kSession.fireAllRules();
	
	}

}
