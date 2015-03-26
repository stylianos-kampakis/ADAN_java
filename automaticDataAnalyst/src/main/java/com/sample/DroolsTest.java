package com.sample;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.dataframe.DataFrame.CheckIfMissing;
import com.dataframe.DataFrame.CheckMissingCount;
import com.datautils.DataUtils;
import com.factengine.ColumnRemovalCommander;
import com.factengine.Fact;
import com.factengine.FactFactory;
import com.factengine.FactRow;
import com.factengine.ImputationCommander;
import com.factengine.RowRemovalCommander;
import com.datautils.*;
/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
            
            String path="C:\\iris_missing.csv";
        	DataFrame df=new DataFrame();
        	df.readCSV(path);

        	FactFactory factFactory=new FactFactory();
        	List<FactRow> factsRows=factFactory.getMissingRowFacts(df);
        	ImputationCommander imputationCommander=new ImputationCommander(new DataUtils.meanImputor());
        	RowRemovalCommander rowCommander=new RowRemovalCommander();
        	ColumnRemovalCommander columnCommander=new ColumnRemovalCommander();
        	
        	factFactory.factInsertor(kSession, factsRows);
    			
    		//System.out.println(kSession.getFactCount());	
        	kSession.insert(df);
        	kSession.insert(imputationCommander);
        	kSession.insert(rowCommander);
        	kSession.insert(columnCommander);
    		kSession.fireAllRules();
    		System.out.println(df.getRows(imputationCommander.getRows()));
    		System.out.println(df.getRows(1,15));
    		
    		
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
