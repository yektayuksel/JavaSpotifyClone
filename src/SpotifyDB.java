import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

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
	
	
	public static void addArtist(String artistName, String country)
	{
		
		Connection conn = getConnection(); 
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
	public static void addToPlaylist(String songID, String genre)
	{
		
		Connection conn = getConnection(); 
		try 
		{
			PreparedStatement addtoplaylst = conn.prepareStatement("INSERT INTO song (SongID,genre) VALUES('"+songID+"','"+genre+"')");
			addtoplaylst.executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	   
	  
	

}
