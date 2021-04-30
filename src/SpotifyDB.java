import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class SpotifyDB 
{
	
	
	
	SpotifyDB() throws ClassNotFoundException
	{
		
	}
	
	public void createTable() throws SQLException, ClassNotFoundException
	{
		
	}
	public static Connection getConnection()
	{
		Connection conn;
		try
	    {
	      // create our mysql database connection
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String url = "jdbc:mysql://localhost:3306/spotify_db";
	      Class.forName(myDriver);
	      conn = DriverManager.getConnection(url, "root", "malcanus12");
	      return conn;
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	     
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		 return null;
	}
	
	
	public static void addArtist(String artistName, String country) throws SQLException
	{
		
		
		Connection conn = getConnection(); 
		

		String query = "SELECT ArtistName, Country FROM artist WHERE ArtistName =" + artistName + " and Country = " + country;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
		{
			JOptionPane.showMessageDialog(null, "This singer already exists", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		try 
		{
			PreparedStatement addartst = conn.prepareStatement("INSERT INTO artist (ArtistName,Country) VALUES('"+artistName+"','"+country+"')");
			addartst.executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void addAlbum(String albumName, String artistID, String releaseDate, String genre)
	{
		
		Connection conn = getConnection(); 
		try 
		{
			PreparedStatement addalbum = conn.prepareStatement("INSERT INTO album (AlbumName,ArtistID,releaseDate,genre) VALUES('"+albumName+"','"+artistID+"','"+releaseDate+"','"+genre+"')");
			addalbum.executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void addSong(String songName, String artistID, String albumID, String genre, String duration, String releaseDate)
	{
		
		Connection conn = getConnection(); 
		try 
		{
			PreparedStatement addsong = conn.prepareStatement("INSERT INTO song (SongName,ArtistID,AlbumID,genre,duration,releaseDate) VALUES('"+songName+"','"+artistID+"','"+albumID+"','"+genre+"','"+duration+"','"+releaseDate+"')");
			addsong.executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void addToPlaylist(String userID, String SongID) throws SQLException
	{
		
		Connection conn = getConnection(); 
		
		String query = "SELECT genre FROM song WHERE SongID = " + SongID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		try 
		{
			PreparedStatement addtoplaylst = conn.prepareStatement("INSERT INTO playlist (userID,SongID,genre) VALUES('"+userID+"','"+SongID+"','"+rs.getString("genre")+"')");
			addtoplaylst.executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static ResultSet getPlaylist(String userID, String genre) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT s.SongName FROM song as s,playlist as p WHERE s.genre = '" + genre + "'and p.genre = '" + genre + "'and s.SongID = p.SongID and p.userID = " + userID + ";";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	
	   
	  
	

}
