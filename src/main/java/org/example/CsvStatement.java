package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import com.google.gson.JsonArray;

public class CsvStatement implements Statement {
	private Path directory;
	private int i;
	CsvStatement(Path directory) {
		this.directory = directory;
		this.i=0;
	}

	@Override
	public ResultSet executeQuery(String s)  throws SQLException{
		//throw new SQLException("My Error");
		
		HttpResponseHandler httpResponseHandler = new HttpResponseHandler();
		try {
			JsonArray response= httpResponseHandler.SendPost(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		  //String fileName = getFileName("test.csv");
		  
		  //Path filePath = directory.resolve(fileName);
		Path filePath = directory.resolve("test.csv");
		try {
			new FileOutputStream(filePath.toString()).close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Row="";
		 try {
	         Files.write(filePath, Row.getBytes());
	      } 
	      catch (IOException e) {
	         System.out.println(e);
	      }
		  if (!Files.exists(filePath)) throw new
		  SQLException(filePath.toAbsolutePath().toString() + " does not exists");
		  
		  try { 
			  JsonArray response= httpResponseHandler.SendPost(s);
			 return new CsvResultSet(response); 
		  } 
		  catch (Exception e)
		  {  
		  }
		  return null; 
	}

	private String getFileName(String sqlExpression) throws SQLException {
		sqlExpression = sqlExpression.trim();
		if (sqlExpression.isEmpty()) throw new SQLException("Empty sql expression");

		String[] parts = sqlExpression.split(" +");
		String fileName = parts[parts.length - 1];
		if (fileName.endsWith(";")) fileName = fileName.substring(0, fileName.length() - 1);

		return fileName;
	}

	@Override
	public int executeUpdate(String s) throws SQLException {
		return 0;
	}

	@Override
	public void close() throws SQLException {

	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return 0;
	}

	@Override
	public void setMaxFieldSize(int i) throws SQLException {

	}

	@Override
	public int getMaxRows() throws SQLException {
		return 0;
	}

	@Override
	public void setMaxRows(int i) throws SQLException {

	}

	@Override
	public void setEscapeProcessing(boolean b) throws SQLException {

	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return 0;
	}

	@Override
	public void setQueryTimeout(int i) throws SQLException {

	}

	@Override
	public void cancel() throws SQLException {

	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	@Override
	public void clearWarnings() throws SQLException {

	}

	@Override
	public void setCursorName(String s) throws SQLException {

	}

	@Override
	public boolean execute(String s) throws SQLException {
		
		//throw new SQLException("error1");
		return true;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		if(this.i==0) {
		this.i=1;
		return executeQuery("SELECT * FROM test.csv");
		}
		
		return null;
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return 0;
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return false;
	}

	@Override
	public void setFetchDirection(int i) throws SQLException {

	}

	@Override
	public int getFetchDirection() throws SQLException {
		return 0;
	}

	@Override
	public void setFetchSize(int i) throws SQLException {

	}

	@Override
	public int getFetchSize() throws SQLException {
		return 0;
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return 0;
	}

	@Override
	public int getResultSetType() throws SQLException {
		return 0;
	}

	@Override
	public void addBatch(String s) throws SQLException {

	}

	@Override
	public void clearBatch() throws SQLException {

	}

	@Override
	public int[] executeBatch() throws SQLException {
		return new int[0];
	}

	@Override
	public Connection getConnection() throws SQLException {
		return null;
	}

	@Override
	public boolean getMoreResults(int i) throws SQLException {
		return false;
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return null;
	}

	@Override
	public int executeUpdate(String s, int i) throws SQLException {
		return 0;
	}

	@Override
	public int executeUpdate(String s, int[] ints) throws SQLException {
		return 0;
	}

	@Override
	public int executeUpdate(String s, String[] strings) throws SQLException {
		return 0;
	}

	@Override
	public boolean execute(String s, int i) throws SQLException {
		throw new SQLException("error2");
		//return false;
	}

	@Override
	public boolean execute(String s, int[] ints) throws SQLException {
		throw new SQLException("error3");
		//return false;
	}

	@Override
	public boolean execute(String s, String[] strings) throws SQLException {
		throw new SQLException("error4");
		//return false;
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return 0;
	}

	@Override
	public boolean isClosed() throws SQLException {
		return false;
	}

	@Override
	public void setPoolable(boolean b) throws SQLException {

	}

	@Override
	public boolean isPoolable() throws SQLException {
		return false;
	}

	@Override
	public void closeOnCompletion() throws SQLException {

	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> aClass) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> aClass) throws SQLException {
		return false;
	}
}
