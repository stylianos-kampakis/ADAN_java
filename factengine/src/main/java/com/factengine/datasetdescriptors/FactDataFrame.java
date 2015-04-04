package com.factengine.datasetdescriptors;

import java.util.ArrayList;

import com.dataframe.DataFrame;
import com.factengine.descriptionenums.DatasetDescriptionTags;

public class FactDataFrame {
	
	DataFrame df;
	ArrayList<DatasetDescriptionTags> tags;

	public FactDataFrame(DataFrame df, ArrayList<DatasetDescriptionTags> tags){
		this.df=df;
		this.tags=tags;
	}
	
}
