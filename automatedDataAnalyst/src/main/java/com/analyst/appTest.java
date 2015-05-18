package com.analyst;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrame;
import com.datautils.DataUtils;
import com.factengine.FactFactory;
import com.factengine.commanders.ImputationCommander;
import com.factengine.datasetdescriptors.FactRow;
import com.factengine.performancedescriptors.Results;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class appTest  {
	DataFrame df_missing;
	DataFrame df;
	
	@Before
	public void prepareTest(){
		
		
        String path="C:\\iris_missing.csv";
        String path2="C:\\iris.csv";

    	df_missing=new DataFrame();
    	
    	df=new DataFrame();

    	try {
			df_missing.readCSV(path);
			df.readCSV(path2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void test_Imputation(){
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("data_preparation");
    	FactFactory factFactory=new FactFactory();
    	List<FactRow> factsRows=factFactory.getMissingRowFacts(df_missing);
    	ImputationCommander imputationCommander=new ImputationCommander(new DataUtils.meanImputor());
    	
    	factFactory.factInsertor(kSession, factsRows);
			//kSession.insert(factsRows);
		//System.out.println(kSession.getFactCount());	
    	kSession.insert(df_missing);
    	kSession.insert(imputationCommander);
		kSession.fireAllRules();
		System.out.println(imputationCommander.getRows().toString());
    	
	}
	
	@Test
	public void checkOrderOfRules_for_Descriptives(){
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("descriptives");
    	
    	Results results=new Results();
    	kSession.insert(df);
    	kSession.insert(results);
		kSession.fireAllRules();

	}
	
	@Test
	public void checkOrderOfRules_for_Linear_Regression(){
		
	}
	
	@Test
	public void test_Results_for_Linear_Regression(){
		
	}
}
