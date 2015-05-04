package com.sample;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrame;
import com.factengine.FactFactory;
import com.factengine.FactRow;
import com.factengine.ImputationCommander;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class appTest  {

	@Before
	public void prepareTest(){
		
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");

        
        String path="C:\\iris_missing.csv";
    	DataFrame df=new DataFrame();
    	try {
			df.readCSV(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
