package com.rinterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.analysisInterface.providers.plotting.IPlotter;
import com.dataframe.DataFrame;
import com.factengine.Response;
import com.factengine.performancedescriptors.ResultStatementPlot;

public class RPlotProvider extends RProviderBase implements IPlotter {

	public ResultStatementPlot makeHistogram(DataFrame df, Response res) {
		BufferedImage img=null;
		
		String template="hist("+res.getResponse()+")";
		code.clear();
		try {
			File file=code.startPlot();
			code.addRCode(template);
			code.endPlot();
			
			caller.runAndReturnResultOnline("names(results)");
			caller.runOnly();
			img=ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultStatementPlot result=new ResultStatementPlot("Histogram of "+res.getResponse(), "histogram", img);
				
		return result;
	}

}
