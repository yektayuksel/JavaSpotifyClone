import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

public class SpotifyDB 
{
	
	static Connection conn;
	SpotifyDB() throws ClassNotFoundException
	{
		try
	    {
	      // create our mysql database connection
	      //String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/sql_spotify";
	      //Class.forName(myDriver);
	      conn = DriverManager.getConnection(myUrl, "root", "malcanus12");
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	     
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public void createTable() throws SQLException, ClassNotFoundException
	{
		
	}
	public void viewTable() throws SQLException {
		 String query = "SELECT * FROM sarki";

	      // create the java statement
	      java.sql.Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = ((java.sql.Statement) st).executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        int SarkiID = rs.getInt("SarkiID");
	        int SanatciID = rs.getInt("SarkiID");
	        int AlbumID = rs.getInt("SarkiID");
	        String tarih = rs.getString("tarih");
	        String Tur = rs.getString("Tur");
	        int sure = rs.getInt("sure");
	        int dinlenmeSayisi = rs.getInt("dinlenmeSayisi");
	        // print the results
	        System.out.format("%d, %d, %d, %s, %s,  %d, %d \n", SarkiID, SanatciID, AlbumID,  tarih, Tur, sure, dinlenmeSayisi);
	      }
	    st.close();
	  }
	
	
	   
	  
	

}
