package com.factengine.performancedescriptors;

import java.awt.Image;

public class ResultStatementPlot extends ResultStatement {

	Image plot;
	
	public ResultStatementPlot(String description, String id, Image plot) {
		super(description, id);
		this.plot=plot;
		
	}

}
