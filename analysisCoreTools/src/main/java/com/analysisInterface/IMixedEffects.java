package com.analysisInterface;

public interface IMixedEffects {
	
	/*specified the mixed effects. Each member of they array has to be a 
	*seperate term in the model. So an example string can look as
	* mixedEffects={"(1|variable1)","(1|variable3/variable2)"}
	*/
	public void setMixedEffects(String[] mixedEffects);

}
