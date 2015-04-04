package com.factengine.commanders;

import java.util.HashSet;
import java.util.Set;

import com.dataframe.DataFrame;

/**
 * Row removal commander is used by the kieSession in order to store rows that should be removed.
 * 
 * @author stelios
 *
 */
public class RowRemovalCommander extends ExecutionCommander {
	
		private Set<Integer> rows=new HashSet<Integer>();

		public Set<Integer> getRows(){
			return this.rows;
		}


		public void addRow(int i){
			
			this.rows.add(i);
		}		
		
		public void removeRows(DataFrame df){
			
			df.dropRows(getRows());
		}
		
}
