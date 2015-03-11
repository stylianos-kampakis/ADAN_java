package com.dataframe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static class compareIndexKey implements Comparator<IndexKey>{

		public int compare(IndexKey first, IndexKey second) {
			
				if(first.getRow()>second.getRow()){
					return 1;
				}
				if(second.getRow()>first.getRow()){
					return -1;
				}
				
				if(first.getColumn()>second.getColumn()){
					return 1;
				}
				
				if(first.getColumn()<second.getColumn()){
					return -1;
				}
				
				return 0;
		}
		

	}
	
    public static void main( String[] args ) throws IOException
    {
	String path="C:\\iris_missing.csv";
    	DataFrame df=new DataFrame();
    	df.readCSV(path);
    	System.out.println(df.getRow(2));
//    	df.dropRow(2);
//    	df.dropRow(3);
//    	df.dropRow(4);
//    	df.rebuildIndex();
//    	df.dropColumn(2);
//    	
//    
//    	
//    	System.out.println(df.getRow(2));
//    	df.rebuildIndex();
    	int[] rows={1,2,3,150};
     	df.dropRows(rows);
     	
    	int[] columns={3,4};
    	df.dropColumns(columns);
     	
    	System.out.println(df.toString());
    	
    	ArrayList<IndexKey> list=new ArrayList<IndexKey>(df.getDf().keySet());
		Collections.sort(list,new compareIndexKey());
    	for(IndexKey key: list){
    		System.out.println(key.toString());
    		
    	}

    }
    


}
