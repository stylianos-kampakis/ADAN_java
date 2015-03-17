package com.factengine;

import java.util.HashSet;
import java.util.Set;

/**
 * Row removal commander is used by the kieSession in order to store rows that should be removed.
 * 
 * @author stelios
 *
 */
public class RowRemovalCommander {
	
		private Set<Integer> rows=new HashSet<Integer>();

		public Set<Integer> getRows(){
			return this.rows;
		}


		public void addRow(int i){
			
			this.rows.add(i);
		}

			
		
}
