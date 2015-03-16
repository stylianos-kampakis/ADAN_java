package com.dataframe;
import com.dataframe.DataPoint;
import com.dataframe.IndexKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**DataFrame
*DataFrame is a class inspired by the DataFrame in R.
*It is not a direct implementation of the R data structure, but it 
*tries to carry over the most basic functionalities.
*
*The class is based on a HashMap. The functions have been built taking
*speed into account.
*
*Note: The missing values are represented by a '?'.
*
* @author Stylianos Kampakis
* @version 0.1 March 3, 2015.
*/
public class DataFrame {
	
	/*DataPointType and DataPointSubType are used for column and datapoint descriptions.
	 * The mapping is
	 * Integer-> Positive integer
	 * Double -> Positive_real
	 * String - > Factor, Ordinal_Factor
	 * 
	 * For a column to have a subtype, ALL the datapoints within the column should share this subtype.
	 * 
	 * Subtypes are useful for particular types of analysis. For example if we know that the 
	 * response variable of a model is in the set of the positive integers, then we	can use a
	 * Poisson regression model.
	*/
	public enum DataPointType{INTEGER, DOUBLE, STRING, NA}
	public enum DataPointSubType{POSITIVE_INTEGER, POSITIVE_REAL, FACTOR, ORDINAL_FACTOR, FREE_TEXT,NONE}
	
	public enum DatasetDescriptionTags{SCALED, NUMERIC_ONLY, NO_MISSING_VALUES};
	
	/*A dataframe is a hashmap that contains keys and datapoints. A key is of the form (row,column).
	 * This form is implemented because it can be faster for implementing various functions. When
	 * collecting points from a row, or a column or any other combination thereof, a function can
	 * create a list of keys, and then simply get the corresponding datapoints from the hashmap.
	 * This is much faster than iterating through every element of the hashmap each and every time.
	*/
	protected HashMap<IndexKey,DataPoint> df;
	
	//The variable Columns holds the key and information about each column.
	protected HashMap<Integer,Column> Columns;
	
	private DataPoint dp;
	
	public DataFrame(){
		
	
	}

	//need to add separators
	//need to add overloaded version where the column names are not in the first line.
	
	/**public void readCSV(String path)
	 * 
	 * Reads the .csv file specified in 'path' and loads it into the dataframe.
	 * This function guesses the types of the file loaded.
	 * 
	 * The function always assume that the column names are in the first line.
	 * 
	 * @param path		The path of the .csv file
	 * @throws DataFrameIndexException 
	 */
	public void readCSV(String path) throws IOException{
		//initialize the
		df=new HashMap<IndexKey,DataPoint>();
		Columns=new HashMap<Integer,Column>();
		
		String separator=",";
		BufferedReader CSVFile = 
		        new BufferedReader(new FileReader(path));

		//assume the column names are in the first line
		// Read first line and put the column names to the Columns variable.
		  String dataRow = CSVFile.readLine(); 
		  String[] dataArray = dataRow.split(separator);
		  Integer columnNameIndex=0;
		  
		  for (String item:dataArray) { 
			  columnNameIndex++;
			  Columns.put(columnNameIndex, new Column(item));
		   }
		  
		  
		  //Read the second line, which should not contain any column names
		  dataRow = CSVFile.readLine();
		  
		  // The while checks to see if the data is null. If 
		  // it is, we've hit the end of the file. If not, 
		  // process the data.
		  
		  int rowIndex=0;
		  int columnIndex=1;
		  while (dataRow != null){
		   rowIndex++;
		   dataArray = dataRow.split(",");
		   for (String item:dataArray) {  
		      dp=new DataPoint(item);
		      df.put(new IndexKey(rowIndex,columnIndex),dp);

		      columnIndex++;
		   }
		 
		   dataRow = CSVFile.readLine(); // Read next line of data.
		   
		   columnIndex=1;
		  }
		  
		  // Close the file once all data has been read.
		  CSVFile.close();

		  //Now guess the type of the columns.
		  this.guessType();
		  
		 } 
	
	/**public String getColumnNames()
	 * 
	 * Gets the column names.
	 */
	public String getColumnNames(){
		
		return Columns.toString();	 
		 
	}
	
	
	/**public HashMap<IndexKey,DataPoint> getDf()
	 * 
	 *Returns a deep copy of the hashmap that contains the data.
	 */
	public HashMap<IndexKey,DataPoint> getDf(){
		
		//This function uses serialization to create a deep copy of the dataframe hashmap.
		 ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		 ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(byteOut);
			out.writeObject(df);
			out.flush();
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
			return df.getClass().cast(in.readObject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	 
		 
	}
		
	/**public int getRowsNumber()
	 * 
	 *Returns the total number of rows.
	 */
	public int getRowsNumber(){
		
		return df.size()/Columns.size();
	}
		
	/**public int getColumnsNumber()
	 * 
	 *Returns the total number of columns.
	 */
	public int getColumnsNumber(){
		
		return Columns.size();
	}
		
	
	/**public ArrayList<DataPoint> getColumn(int column)
	 * 
	 *Returns an arraylist containing all the rows for a particular column.
	 *
	 *@param column		the key (index) of the column
	 * @throws DataFrameIndexException 
	 */
	public ArrayList<DataPoint> getColumn(int column) throws DataFrameIndexException{
		
		if(column>this.getColumnsNumber() || column<1){
			throw new DataFrameIndexException("Column index does not exist.");
		}
		
		ArrayList<DataPoint> result=new ArrayList<DataPoint>();
		IndexKey key=new IndexKey(1,column);
		
		/*The method creates a key and then queries
		 * hashmap to return the element for each key.
		*/
		for (int i=2;i<=this.getRowsNumber()+1;i++){
			result.add(this.df.get(key));
			key.setRow(i);
		}
		
		return result;
	}
	
	/**public ArrayList<DataPoint> getColumn(string columnName)
	 * 
	 *Finds a column with the corresponding name and returns it. Note, that
	 *if two columns share the same name, then it will return a column randomly, among
	 *all the columns that share the same name.
	 *
	 *@param columnName		the name of the column
	 *@throws DataFrameIndexException 
	 */
	public ArrayList<DataPoint> getColumn(String columnName) throws DataFrameIndexException{
		int columnKey = -1;
		
		for(Entry<Integer, Column> entry: this.Columns.entrySet()){
			if (entry.getValue().toString().equals(columnName)){
				columnKey=entry.getKey();
				break;
			}
			
		}
		// if the key was not found throw exception otherwise proceed normally
		if (columnKey>-1){
			return getColumn(columnKey);
		}
		else
		{
			throw new DataFrameIndexException("Column not found.");
		}
	}
	
	/**public ArrayList<DataPoint> getRow(int row)
	 * 
	 *Returns an arraylist containing all the elements of a row
	 *
	 *@param column		the key (index) of the row.
	 * @throws DataFrameIndexException 
	 */
	public ArrayList<DataPoint> getRow(int row) throws DataFrameIndexException{
		
		if(row>this.getRowsNumber() || row<1){
			throw new DataFrameIndexException("Invalid row index.");
		}
		
		ArrayList<DataPoint> result=new ArrayList<DataPoint>();
		IndexKey key=new IndexKey(row,1);
		
		for (int i=2;i<=this.getColumnsNumber()+1;i++){
			result.add(this.df.get(key));
			key.setColumn(i);
		}
		
		return result;
	}
	
	
	
	/**public void guessType()
	 * 
	 *Guesses the type and subtype of each column.
	 *The results then update the Columns variable.
	 * @throws DataFrameIndexException 
	 */
	protected void guessType(){

		for(Integer i : Columns.keySet()){
		ArrayList<DataPoint> column;
		try {
			column = this.getColumn(i);
			if(assertType(column,DataPointType.INTEGER)){
				Columns.get(i).setType(DataPointType.INTEGER);
				if(AssertSubType(column,DataPointSubType.POSITIVE_INTEGER)){
					Columns.get(i).setSubType(DataPointSubType.POSITIVE_INTEGER);
				}
			}
			else if(assertType(column,DataPointType.DOUBLE)){
				Columns.get(i).setType(DataPointType.DOUBLE);
				if(AssertSubType(column,DataPointSubType.POSITIVE_REAL)){
					Columns.get(i).setSubType(DataPointSubType.POSITIVE_REAL);
				}
			}
			else{
				Columns.get(i).setType(DataPointType.STRING);
				HashSet<String> factors=getFactors(column);
				
				/*The code implements the following trick to check if a column is a factor
				*If the number of factors returned from the getFactors function are not less
				*than the total number of elements in the column, then that column cannot be considered
				*a factor.
				*
				*Obviously, different algorithms can actually treat this as a factor, but it is most likely
				*that a field where every value is unique comes from a dataset where that field is used
				*to denote free text.
				*/
				if(factors.size()<column.size()){
					Columns.get(i).setSubType(DataPointSubType.FACTOR);
				}
				else{
					Columns.get(i).setSubType(DataPointSubType.FREE_TEXT);
				}

			}
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		}	
	}
	
	/**protected boolean assertType(ArrayList<DataPoint> column,DataPointType type)
	 * Helper function that asserts that the column is of a particular type.
	 * It is used by guessType().
	 * 
	 * @param column an arraylist with datapoints
	 * @param type	the type to be asserted
	 */
	protected boolean assertType(ArrayList<DataPoint> column,DataPointType type){
		for(DataPoint point : column){		
			if(point.getType()!=type && point.getType()!=DataPointType.NA){
			return false;	
			}
		}
		return true;		
	}
	
	/**protected boolean assertSubType(ArrayList<DataPoint> column,DataPointSubType subtype)
	 * Helper function that asserts that the column is of a particular subtype.
	 * It is used by guessType().
	 * 
	 * @param column an arraylist with datapoints
	 * @param subtype	the type to be asserted
	 */
	protected boolean AssertSubType(ArrayList<DataPoint> column,DataPointSubType subtype){
		for(DataPoint point : column){		
			//if a datapoint is a missing value, then it does not count
			if(point.getSubType()!=subtype && point.getType()!=DataPointType.NA){
			return false;	
			}
		}
		return true;		
	}
	
	
	/**public String GetTypes()
	 * 
	 * Returns a string with the names of the columns, their types and subtypes.
	 * 
	 */
	public String GetTypes(){
		String dummy="";
		String type="";
		String subtype="";
		
		for(Column col :Columns.values()){
			type=col.getType().toString();
			subtype=col.getSubType().toString();
			dummy=dummy+","+col.getName()+" - type: "+type+" - subtype: "+subtype;
		}
		dummy=dummy.replaceFirst(",", "");
		return dummy;
	}
	
	//public HashMap<Integer,<>> GetTypesAsHashMap(){
		
		//}
	
	
	//to do, should try to set the type of a paricular column
	public boolean SetType(int column){
	
	return true;
	}
	
	/**public void dropColumnHelper(int column)
	 * 
	 * Helper function used to remove a single column.
	 * 
	 */
	protected void dropColumnHelper(int column){
		IndexKey key=new IndexKey(1,column);
		int rowsNumber=this.getRowsNumber();
		
		for (int i=2;i<=rowsNumber+1;i++){
			this.df.remove(key);
			key.setRow(i);
		}
		
		this.Columns.remove(column);
		
	}
	
	/**protected void dropColumns(int[] columns)
	 * 
	 * Removes many columns at once.
	 * 
	 * @param columns an int[] array of columns to be removed.
	 * 
	 */
	public void dropColumns(int[] columns){
		for(int col : columns){
			dropColumnHelper(col);
			
		}
		rebuildIndexColumn();
		rebuildColumnsMap(columns);
	}
	
	public void dropColumn(int column){
		this.dropColumns(new int[]{column});
	}
	

	/**private void rebuildColumnsMap(int[] columns)
	 * 
	 *This is used to rebuild the HashMap that contains the column id-name mappings.
	 * 
	 * @param columns an int[] array of columns that were removed.
	 * 
	 */
	private void rebuildColumnsMap(int[] columns) {
		HashMap<Integer,Column> dummyColumns=new HashMap<Integer,Column>();
		
		for(int column:columns){
		for(Entry<Integer, Column> entry : this.Columns.entrySet()){
			if(entry.getKey()>column){			
				dummyColumns.put(entry.getKey()-1, entry.getValue());
			}
			else{
				dummyColumns.put(entry.getKey(), entry.getValue());

			}
			
		}
		}
		
		Columns=dummyColumns;		
	}

	/**protected void dropRowHelper(int row)
	 * 
	 * Helper function used to remove a single row.
	 * 
	 */
	protected void dropRowHelper(int row){
		IndexKey key=new IndexKey(row,1);
		int columnsNumber=getColumnsNumber();
		
		for (int i=2;i<=columnsNumber+1;i++){
			df.remove(key);
			key.setColumn(i);
		}		
	}
	
	/**public void dropRows(int[] rows)
	 * 
	 * Function used in order to remove many rows at once. Rebuilds the index after
	 * the rows are removed.
	 * 
	 * @param rows	an int[] array with the indices of the rows.
	 * 
	 */
	public void dropRows(int[] rows){
		
		for(int row : rows){
			dropRowHelper(row);
		}
		rebuildIndexRow();
	}
	
public void dropRows(ArrayList<Integer> rows){
		
		for(int row : rows){
			dropRowHelper(row);
		}
		rebuildIndexRow();
	}
	
	
	/**public void dropRow(int row)
	 * 
	 * Function used in order to remove a single row.
	 *
	 * 
	 */
	public void dropRow(int row){
		
		dropRows(new int[]{row});

	}
	
	
	/**private void rebuildIndexColumn()
	 * 
	 * This function is used in order to rebuild the index after one or more columns
	 * have been removed.
	 * 
	 * Once a row is removed, the whole index needs to be rebuilt. The reason is that 
	 * when retrieving rows or columns, the IndexKeys are supposed to be in order. The reason
	 * is that the getRow and getColumn functions work by updating sequentially the IndexKey.
	 * 
	 */
	private void rebuildIndexColumn(){
		//first we create a list with all the index keys
		Set<IndexKey> keys=df.keySet();	
		ArrayList<IndexKey> list=new ArrayList<IndexKey>(keys);
		
		int rowsNumber=this.getRowsNumber();
		
		//This code adds a column at each row, with the value 0. This is used as a dummy
		//column in order to detect whether the columns' numbering starts at a value
		//greater than 0.
		for(int row=1;row<=rowsNumber;row++){
			list.add(new IndexKey(row,0));
		}
		
		Collections.sort(list,new compareIndexKey());
		
		IndexKey dummy1;
		IndexKey dummy2;
		IndexKey newIndex;
		int difference_col;
	
		//This for loop compares the IndexKeys in pairs.
		//if it finds two IndexKeys where the number of columns
		for(int i=0;i<list.size()-1;i++)	
		{
			
			dummy1=list.get(i);
			dummy2=list.get(i+1);
			difference_col=dummy2.getColumn()-dummy1.getColumn();
			

			//if the column difference is more than 1, then
			//remove the old IndexKey and add a new one
			if(difference_col>1 && dummy1.getRow()==dummy2.getRow()){
				newIndex=new IndexKey(dummy2.getRow(),dummy1.getColumn()+1);
				DataPoint point=df.get(dummy2);
				df.remove(dummy2);
				df.put(newIndex, point);
				//update the list accordingly
				list.set(i+1, newIndex);	

			}
			
		
		}
		
	}
	
	
	/**private void rebuildIndexRow()
	 * 
	 * This function is used in order to rebuild the index after one or more rows
	 * have been removed.
	 * 
	 * Once a row is removed, the whole index needs to be rebuilt. The reason is that 
	 * when retrieving rows or columns, the IndexKeys are supposed to be in order. The reason
	 * is that the getRow and getColumn functions work by updating sequentially the IndexKey.
	 * 
	 */
	private void rebuildIndexRow(){
		//first we createa a list with all the index keys
		int columnsNumber=getColumnsNumber();

		Set<IndexKey> keys=df.keySet();	
		ArrayList<IndexKey> list=new ArrayList<IndexKey>(keys);
		
		//now, we add a row where the row number is 0. This row is used in order to check
		//whether the new df is not starting at 1, but at some higher value (e.g. row 4).
		for(int column=1;column<=columnsNumber;column++){
			list.add(new IndexKey(0,column));
		}
		
		
		Collections.sort(list,new compareIndexKey());
		
		IndexKey dummy1;
		IndexKey dummy2;
		IndexKey newIndex;
		int difference_row;
		
		
//this for loop works by jumping from row to row. It starts by checking the first column
//of each row
		for(int i1=0;i1<list.size()-columnsNumber;i1=i1+columnsNumber){
			
			dummy1=list.get(i1);		
			dummy2=list.get(i1+columnsNumber);
			difference_row=dummy2.getRow()-dummy1.getRow();
			
			//if a difference was detected in the first column of the first row then
			//operate on the whole row. Remove the IndexKey from the df, and then
			//add a new IndexKey.
			if(difference_row>1){
			
				for(int j=0;j<columnsNumber;j++){
					
					dummy2=list.get(i1+j+columnsNumber);
					newIndex=new IndexKey(dummy1.getRow()+1,dummy2.getColumn());
					
					DataPoint point=df.get(dummy2);
					df.remove(dummy2);
					df.put(newIndex, point);
					//update the list used in this loop
					list.set(i1+j+columnsNumber, newIndex);				
				}			
			}
		}
	}
	
	/**public void setRow(int rowIndex,ArrayList<DataPoint> row)
	 * 
	 * Sets the row of the selected rowIndex to the particular row.
	 * 
	 * @param rowIndex the index (row) of the row.
	 * @param the row(contents) that will replace the old row.
	 * 
	 */
	public void setRow(int rowIndex, ArrayList<DataPoint> row){
		
	}
	
	/**public void setColumn(int columnIndex, ArrayList<DataPoint> column)
	 * 
	 * Sets the column of the selected columnIndex to the particular column.
	 * 
	 * @param columnIndex the index (column) of the column.
	 * @param the column(contents) that will replace the old column.
	 * 
	 */
	public void setColumn(int columnIndex, ArrayList<DataPoint> column){
		
	}
	
	/**public void setPoint(IndexKey key,DataPoint point)
	 * 
	 * Replaces a single point by a new point indexed by an IndexKey
	 * 
	 * @param key the IndexKey of the point that will be replaced
	 * @param point the contents
	 */
	public void setPoint(IndexKey key,DataPoint point){
		
	}
	
	/**public HashSet<String> getFactors(ArrayList<DataPoint> column)
	 * 
	 * This helper function goes through the contents of a column and adds  
	 * each element as a separate factor. The function implements a set, so duplicates will not
	 * be inserted.
	 * 
	 * This function is used by guessType in order to assess whether a column is really a factor
	 * or just free text.
	 * 
	 */
	protected HashSet<String> getFactors(ArrayList<DataPoint> column){
		
		HashSet<String> factors=new HashSet<String>();
		
		for(DataPoint point : column){
			factors.add(point.toString());
		}
			
		return factors;
		
	}
	
	/**public String getRDataFrame()
	 * 
	 * Returns R code that builds a data.frame equivalent to this DataFrame.
	 * @throws DataFrameIndexException 
	 * 
	 */
	public String getRDataFrame(){
		
		String template="DataFrame=data.frame(";
		
		for(Map.Entry<Integer,Column> entry : this.Columns.entrySet()){
			Column col=entry.getValue();
			Integer key=entry.getKey();
			
			template=template+col.getName().replace("\"", "")+"=c(";
			
			ArrayList<DataPoint> list;
			try {
				list = this.getColumn(key);
				for(DataPoint point : list){
					
					template=template+point.toString()+",";
					
				}
				template=template+"),";	
				template=template+")";
				template=template.replace(",)", ")");
				
				//the symbol '?' is used for missing values, but in R we need to convert to NA
				template=template.replace("?", "NA");
				return template;
			} catch (DataFrameIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
				return null;
	}
	
	public String toString(){
		
		String dummy="";
		for(int i=1;i<=this.getRowsNumber();i++){
			try {
				dummy=dummy+"\n "+i+": "+this.getRow(i);
				
			} catch (DataFrameIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return dummy;
		
	}
	
	/**public String getIndexKeyListString()
	 * 
	 * Returns all the IndexKeys. Useful for debugging purposes and to check whether
	 * the index has been rebuilt correctly after removing or adding rows/columns.
	 * 
	 */
	public String getIndexKeyListString(){
		String dummy="";
		ArrayList<IndexKey> list=new ArrayList<IndexKey>(df.keySet());
		Collections.sort(list,new compareIndexKey());
    	for(IndexKey key: list){
    		dummy=dummy+key.toString()+"\n"; 		
    	}
    	return dummy;
	}
	
	/**public boolean applyLogicalToRow(ILogicalRowCheck check,int row) 
	 * 
	 * Applies a logical condition to a single row.
	 * 
	 * @throws	DataFrameIndexException
	 * 
	 */
	public boolean applyLogicalToRow(ILogicalRowCheck check,int row) throws DataFrameIndexException{
		
		return check.rowConditionToCheck(this.getRow(row));
		
	}
	
	/**public boolean returnCountLogicalToRow(ILogicalRowCheck check,int row)
	 * 
	 * Applies a logical condition to a single row and then returns the number of times
	 * it evaluated to true.
	 * 
	 * @throws	DataFrameIndexException
	 * 
	 */
	public int applyCountLogicalToRow(ICountLogicalRowCheck check,int row) throws DataFrameIndexException{
		
		return check.rowCountConditionToCheck(this.getRow(row));
		
	}
	
	/**ArrayList<Integer> getRowsWithMissingValuesAboveThreshold(int threshold)
	 * 
	 * Gets all rows where the number of missing values is above a 
	 * pre-specified threshold.
	 * 
	 * 
	 * @param threshold
	 * 
	 */
	public ArrayList<Integer> getRowsWithMissingValuesAboveThreshold(int threshold){
		ArrayList<Integer> rows = new ArrayList<Integer>();
		
		for(int i=1;i<getRowsNumber()+1;i++){
		try {
			if(applyCountLogicalToRow(new DataFrame.CheckMissingCount(),i)>threshold){
				rows.add(i);
			}
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
		return rows;		
		
	}
	
	/**public int getNumMissingValuesForRow(int row)
	 * 
	 * Gets the number of missing values for a particular row.
	 * 
	 * @throws DataFrameIndexException
	 * 
	 */
	public int getNumMissingValuesForRow(int row) throws DataFrameIndexException{
		return applyCountLogicalToRow(new DataFrame.CheckMissingCount(),row);
	}
	
	
	
	//Helper class, used to compare IndexKeys by row. It is used when rebuilding the index.
	private class compareIndexKey implements Comparator<IndexKey>{

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
	
	
	
	/**public static class CheckIfMissing
	 * 
	 * This class is used to check if there are missing values in a row, in conjuction with
	 * the method applyLogicalToRow.
	 *
	 */
	public class CheckIfMissing implements ILogicalRowCheck{

		public boolean rowConditionToCheck(ArrayList<DataPoint> row) {
			for(DataPoint point :row){
				if(point.getType().equals(DataFrame.DataPointType.NA)){
					return true;
				}			
			}
			return false;
		}  	
    }

	/**public static class CheckMissingCount
	 * 
	 * This class is used to get the total number of missing values, used in conjuction with
	 * the method applyCountLogicalToRow()
	 *
	 */
	public class CheckMissingCount implements ICountLogicalRowCheck{

		public int rowCountConditionToCheck(ArrayList<DataPoint> row) {
			int occurences=0;
			for(DataPoint point :row){
				if(point.getType().equals(DataFrame.DataPointType.NA)){
					occurences++;
				}			
			}
			return occurences;
		}  	
    }
	
}
