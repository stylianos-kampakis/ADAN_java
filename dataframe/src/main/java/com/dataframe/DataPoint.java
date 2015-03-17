package com.dataframe;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dataframe.DataPointSubType;



/** DataPoint
 * DataPoint is a class used in order to save individual datapoints in a frame.
 * 
 * The class also implements methods to check the type of an object.
 * 
* @author Stylianos Kampakis
* @version 0.1 March 3, 2015.
*/

//NEED TO WRITE N/A CODE

//serializable needs to be implemented in order to make deep copies
public class DataPoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private Object point;
	private DataPointType type;
	private DataPointSubType subtype=DataPointSubType.NONE;
	List<String> missingValues=Arrays.asList("NA","N/A","N\\A","na","n\\a","n/a","?");


	/**public DataPoint(String item)
	 * 
	 * Constructor for DataPoint. Loads a String item and then checks the type. It loads the
	 * item in the variable point.
	 */
	public DataPoint(String item) {

		if(missingValues.contains(item)){
			this.type=DataPointType.NA;
			point="?";
		}
		else if (isDouble(item)){
			point=Double.parseDouble(item);	
			this.type=DataPointType.DOUBLE;
		}
		else if (isInteger(item)){
			point=Integer.parseInt(item);
			this.type=DataPointType.INTEGER;
		}
		else{
			point=item;
			this.type=DataPointType.STRING;
		}
		
		this.guessSubType();
		
	}
	
	
	public DataPoint(double number) {
		this.point=number;
		this.type=DataPointType.DOUBLE;
	}
	
	public DataPoint(int number) {
		this.point=number;
		this.type=DataPointType.INTEGER;
	}


	/**private boolean isDouble(String str) 
	 * 
	 * Helper method used by the constructor.
	 */
	private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	/**private boolean isInteger(String str) 
	 * 
	 * Helper method used by the constructor.
	 */
	private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	/**getter for the 'type' variable.
	 * 
	 */
	public DataPointType getType(){
		
		return this.type;
	}
	
	/**getter for the 'subtype' variable.
	 * 
	 */
	public DataPointSubType getSubType(){
		
		return this.subtype;
	}
	
	
	/**private void guessSubType()
	 * 
	 * Helper method used by the constructor in order to guess the subtype.
	 * 
	 * DataPoints do not
	 */
	private void guessSubType(){	
		
			if(this.type==DataPointType.INTEGER){
				if((Integer)this.point>0){
					this.subtype=DataPointSubType.POSITIVE_INTEGER;
				}	
			}
		
		
			else if(this.type==DataPointType.DOUBLE){
				if((Double)this.point>0){
					this.subtype=DataPointSubType.POSITIVE_REAL;
				}	
			}			
		
		
	}
	
	public Object getPoint(){
		return this.point;
	}
	
	 @Override
	    public String toString(){
		 
		 //if the point is of type string, then it should be enclosed in quotation marks
		 if(this.type==DataPointType.STRING){
			 
			 String dummy=(String)point;
			 if(!dummy.contains("'") && !dummy.contains("\"")){
				 dummy="\'"+dummy+"\'";
			 }
			 return dummy;
			 
		 }
	    	
		 return point.toString();
	    }
	 
}
