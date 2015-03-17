package com.factengine;

import java.util.HashSet;
import java.util.Set;

public class RowRemovalCommander {
	
		private Set<Integer> rows=new HashSet<Integer>();

		public Set<Integer> getRows(){
			return this.rows;
		}


		public void addRow(int i){
			
			this.rows.add(i);
		}

			
		
}
