package com.factengine.performancedescriptors;

import com.data.plotter.Plot;

public class ResultStatementPlot extends ResultStatement {

	Plot plot;
	
	public ResultStatementPlot(String description, String id, Plot plot) {
		super(description, id);
		this.plot=plot;
		// TODO Auto-generated constructor stub
	}

}
