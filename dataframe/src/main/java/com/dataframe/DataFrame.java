package com.dataframe;
import com.dataframe.DataPoint;
import com.dataframe.IndexKey;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
	 */
	public ArrayList<DataPoint> getColumn(int column){
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
	 * @throws DataFrameIndexException 
	 */
	public ArrayList<DataPoint> getColumn(String columnName) throws DataFrameIndexException{
		ArrayList<DataPoint> result=new ArrayList<DataPoint>();
		int columnKey = -1;
		
		for(Entry<Integer, Column> entry: Columns.entrySet()){
			if (entry.getValue().equals(columnName)){
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
	 */
	public ArrayList<DataPoint> getRow(int row){
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
	 */
	protected void guessType(){

		for(Integer i : Columns.keySet()){
		ArrayList<DataPoint> column=this.getColumn(i);
		
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
	
	public void dropColumn(int column){
		
		
	}
	
	
	public void dropRow(int row){
		
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
	
	/**public String convertToR()
	 * 
	 * Returns R code that builds a data.frame equivalent to this DataFrame.
	 * 
	 */
	public String getRDataFrame(){
		
		String template="DataFrame=data.frame(";
		
		for(Map.Entry<Integer,Column> entry : this.Columns.entrySet()){
			Column col=entry.getValue();
			Integer key=entry.getKey();
			
			template=template+col.getName().replace("\"", "")+"=c(";
			
			ArrayList<DataPoint> list=this.getColumn(key);
			
			for(DataPoint point : list){
				
				template=template+point.toString()+",";
				
			}
			template=template+"),";
			
			
		}
		
		template=template+")";
		template=template.replace(",)", ")");
		
		//the symbol '?' is used for missing values, but in R we need to convert to NA
		template=template.replace("?", "NA");

		return template;
	}
	
}
