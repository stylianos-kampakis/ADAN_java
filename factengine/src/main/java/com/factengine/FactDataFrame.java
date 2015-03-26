package com.factengine;

import com.dataframe.DataFrame;

public class FactDataFrame {
	
	DataFrame df;
	DatasetDescriptionTags[] tags;

	public FactDataFrame(DataFrame df, DatasetDescriptionTags[] tags){
		this.df=df;
		this.tags=tags;
	}
	
}
