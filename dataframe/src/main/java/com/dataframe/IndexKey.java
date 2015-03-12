package com.dataframe;

import java.io.Serializable;

/** Description of IndexKey
 * 
 * IndexKey is a class used by DataFrame. It contains an 
*
* @author Stylianos Kampakis
* @version 0.1 March 3, 2015.
*/

//serializable needs to be implemented in order to make deep copies
public class IndexKey implements Serializable{

	
	private static final long serialVersionUID = 24958L;
	protected int row;
	protected int column;
	
	    public IndexKey(int row, int column) {
	        this.row = row;
	        this.column = column;
	    }

	    @Override
	    public int hashCode() {
	        return Integer.parseInt(String.valueOf(row)+String.valueOf(column));
	    }

	    @Override
	    //Equals is checking if the reference is the same, or if the (row,column) are the same.
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        IndexKey other = (IndexKey) obj;
	        if (row != other.row)
	            return false;
	        if (column != other.column)
	            return false;
	        return true;
	    }
	    
	    @Override
	    public String toString(){
	    	return String.valueOf(row)+","+String.valueOf(column);
	    }
	    
	    public int getRow(){
	    	return this.row;
	    }
	    
	    public int getColumn(){
	    	return this.column;	
	    }
	    
	    public void setRow(int row){
			this.row=row;
		}
		
		public void setColumn(int column){
			this.column=column;
		}
		
		
	
	}



