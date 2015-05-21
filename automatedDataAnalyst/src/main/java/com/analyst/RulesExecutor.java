package com.analyst;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.analyst.RulesDataCleaner.imputationOptions;
import com.dataframe.DataFrame;
import com.factengine.Response;
import com.factengine.performancedescriptors.Results;



public class RulesExecutor {
	
	private boolean hasExecuted=false;
	
	protected DataFrame df;
	protected KieServices ks;
	protected KieContainer kContainer;
	protected KieSession kSession;
	
	public RulesExecutor(DataFrame df,String packageName,String sessionName){
		
		this.df=df;
		
		//code taken from http://stackoverflow.com/questions/24558451/cant-run-hello-world-on-drools-dlr-files-are-not-picked-from-classpath-by-kie
		//in order to fix a problem of Drools 6.0.0+
	    KieServices kieServices = KieServices.Factory.get();
	    KieFileSystem kfs = kieServices.newKieFileSystem();

	    // for each DRL file, referenced by a plain old path name:
	    FileInputStream fis;
		try {
			fis = new FileInputStream( "src/main/resources/"+packageName+"/"+sessionName+".drl" );
			 kfs.write( "src/main/resources/data_preparation/"+packageName+"/"+sessionName+".drl",
		                kieServices.getResources().newInputStreamResource( fis ) );

		    KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
		    org.kie.api.builder.Results results = kieBuilder.getResults();
		    if( results.hasMessages( Message.Level.ERROR ) ){
		        System.out.println( results.getMessages() );
		        throw new IllegalStateException( "### errors ###" );
		    }

		    KieContainer kieContainer =
		    kieServices.newKieContainer( kieServices.getRepository().getDefaultReleaseId() );

		    KieBase kieBase = kieContainer.getKieBase();
		    kSession = kieContainer.newKieSession();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
		

	
}
	
	public Results analyzeData(Response response){
		return null;
	}
	
	public boolean hasExecuted(){
		return hasExecuted;
	}
}
