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
		

		/*String query = "SELECT ArtistID FROM artist WHERE ArtistName =" + artistName + " and Country = " + country;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
		{
			JOptionPane.showMessageDialog(null, "This singer already exists", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}*/
			
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
	public static void addAlbum(String albumName, String artistID, String releaseDate, String genre) throws SQLException
	{
		Connection conn = getConnection(); 
		
		String query = "SELECT AlbumName FROM album WHERE ArtistID =" + artistID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			if(rs.getString("AlbumName").equals(albumName))
			JOptionPane.showMessageDialog(null, "This album already exists", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}	
		try 
		{
			PreparedStatement addalbum = conn.prepareStatement("INSERT INTO album (AlbumName,ArtistID,releaseDate,genre) VALUES('"+albumName+"','"+artistID+"','"+releaseDate+"','"+genre+"')");
			addalbum.executeUpdate();
			JOptionPane.showMessageDialog(null, albumName+"has added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void addSong(String songName, String artistID, String albumID, String genre, String duration, String releaseDate) throws SQLException
	{
		
		Connection conn = getConnection();
		
		String query = "SELECT SongName FROM album WHERE ArtistID =" + artistID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			if(rs.getString("SongName").equals(songName))
			JOptionPane.showMessageDialog(null, "This song already exists", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}	
		
		try 
		{
			PreparedStatement addsong = conn.prepareStatement("INSERT INTO song (SongName,ArtistID,AlbumID,genre,duration,releaseDate) VALUES('"+songName+"','"+artistID+"','"+albumID+"','"+genre+"','"+duration+"','"+releaseDate+"')");
			addsong.executeUpdate();
			JOptionPane.showMessageDialog(null, songName+"has added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
		String query = "SELECT s.SongName, s.duration, a.ArtistName FROM song as s, playlist as p, artist as a WHERE s.genre = '" + genre + "'and p.genre = '" + genre + "'and s.SongID = p.SongID and p.userID = " + userID + " and s.ArtistID = a.ArtistID;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	public static String getArtistName(String SongID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT a.ArtistName FROM artist AS a, song AS s WHERE s.SongID = " + SongID + " and a.ArtistID = s.ArtistID";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs.getString("ArtistName");
	}
	
	
	
	public static String getUserID(String userName) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT userID FROM user WHERE userName = " + userName;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs.getString("userID");
	}
	
	
	
	public static ResultSet getUserName(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT userName FROM user WHERE userID = " + userID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
	}
	
	//Following sutununa sadece premium kullanicilar  eklenebilecek.!!!
	
	
	public static ResultSet getFollowers(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		while(true)
		{
			String query = "SELECT FollowerID FROM follow WHERE FollowingID = " + userID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.getString("FollowerID");
		}
		//userID'si bilinen user'ýn takipcilerini dondurmeli.
		//hepsi donebilir mi emin degilim ama query dogru olmasi lazim donguden cikamadim da
		
	}
	
	public static ResultSet getFollowings(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		while(true)
		{
			String query = "SELECT FollowingID FROM follow WHERE FollowerID = " + userID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.getString("FollowerID");
		}
		
		//userID'si bilinen user'in takip ettiklerini dondurmeli.
		
	}
	
	public static ResultSet getAlbums(String ArtistID) throws SQLException
	{
		Connection conn = getConnection(); 
		while(true)
		{
			String query = "SELECT AlbumID, AlbumName FROM album WHERE ArtistID = " + ArtistID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.getString("AlbumID");
		}
		//ArtistID'si bilinen artistin album isimlerini ve album id'lerini dondurmeli.
		//eksik var aklima gelmedi cozumu. rs'de cift sonuc dondurmem lazim.
		
	}
	public static ResultSet getSongs(String AlbumID) throws SQLException
	{
		Connection conn = getConnection(); 
		while(true)
		{
			String query = "SELECT SongID, SongName FROM song WHERE ArtistID = " + AlbumID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.getString("SongID");
		}
		//AlbumID'si bilinen albumdeki sarki isimlerini ve sarki id'lerini dondurmeli.
		//eksik var aklima gelmedi cozumu. rs'de cift sonuc dondurmem lazim.
	}
	
	
	/*public static void deleteArtist(String ArtistID)
	{
		ArtistIDsi belli olan Artist'in butun sarkilarini ve albumlerini teker teker silmeli. Ilk 
		once sarkilar silinecek, sonra albumler silinecek, en son sanatcinin kendisi silinecek.
		Kontrol etmedim ama FOREIGN KEY iliskilerinden dolayi silme islemleri bu sirayla yapilmadiginda
		problem cikabilir
		
	}*/
	
	
	   
	  
	

}
