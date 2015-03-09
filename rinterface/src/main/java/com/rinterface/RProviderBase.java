package com.rinterface;

import rcaller.RCaller;
import rcaller.RCode;

public class RProviderBase {

	protected String rPath;
	protected RCode code;
	protected RCaller caller;
	
	
	protected RProviderBase(){
		code=new RCode();
		caller=new RCaller();
	}
	
	protected RProviderBase(String path){
		code=new RCode();
		caller=new RCaller();
		this.setPath(path);
	}

	protected String getPath(){
		
		return this.rPath;
	}
	
	protected void setPath(String path){
		this.rPath=path;
	}
	
	protected void initialize(){
		code.clear();
		caller.cleanRCode();
		caller.setRExecutable(this.rPath);
		
	}
	
}
