package com.sample;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataFrame.CheckIfMissing;
import com.dataframe.DataFrame.CheckMissingCount;
import com.datautils.DataUtils;
import com.factengine.Fact;
import com.factengine.FactFactory;
import com.factengine.FactRow;
import com.factengine.ImputationCommander;

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
        	ImputationCommander imputationCommander=new ImputationCommander();
        	
        	factFactory.factInsertor(kSession, factsRows);
    			//kSession.insert(factsRows);
    		//System.out.println(kSession.getFactCount());	
        	kSession.insert(df);
        	kSession.insert(imputationCommander);
    			kSession.fireAllRules();
    			System.out.println(imputationCommander.getRows().toString());
    		
    			
    		
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
