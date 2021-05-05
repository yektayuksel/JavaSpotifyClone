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
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String url = "jdbc:mysql://localhost:3306/spotify_db";
	      Class.forName(myDriver);
	      conn = DriverManager.getConnection(url, "root", "malcanus12");
	      return conn;
	     
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		 return null;
	}
	
	public static void addUser(String userName, String email, String pswrd, String country, boolean isPremium, String cardNumber,boolean isPaid)
	{
		Connection conn = getConnection(); 
		try 
		{
			PreparedStatement adduser = conn.prepareStatement("INSERT INTO user (userName,email,pswrd,Country,isPremium, cardNumber,isPaid) "
			+ "VALUES('"+userName+"','"+email+"','"+pswrd+"','"+country+"',"+isPremium+","+cardNumber+","+isPaid+")");
			
			adduser.executeUpdate();
			JOptionPane.showMessageDialog(null, userName+", you have been signed in successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void addArtist(String artistName, String country) throws SQLException
	{
		
		
		Connection conn = getConnection(); 
		try 
		{
			PreparedStatement addartst = conn.prepareStatement("INSERT INTO artist (ArtistName,Country) VALUES('"+artistName+"','"+country+"')");
			addartst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Artist added successfully", "Succes", JOptionPane.INFORMATION_MESSAGE);
			
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
			{
				JOptionPane.showMessageDialog(null, "This album already exists", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
		}	
		try 
		{
			PreparedStatement addalbum = conn.prepareStatement("INSERT INTO album (AlbumName,ArtistID,releaseDate,genre) VALUES('"+albumName+"','"+artistID+"','"+releaseDate+"','"+genre+"')");
			addalbum.executeUpdate();
			JOptionPane.showMessageDialog(null, albumName+" has added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void addSong(String songName, String artistID, String albumID, String genre, String duration, String releaseDate) throws SQLException
	{
		
		Connection conn = getConnection();
		
		String query = "SELECT SongName FROM song WHERE ArtistID = '" + artistID + "'";
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
			JOptionPane.showMessageDialog(null, songName+"The song has added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
		rs.next();
		return rs.getString("ArtistName");
	}
	
	
	
	public static String getUserID(String userName) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT userID FROM user WHERE userName = '" + userName +"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		Integer ID = rs.getInt("userID");
		
		return ID.toString();
	}
	
	
	
	public static ResultSet getUserName(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT userName FROM user WHERE userID = '" + userID + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
	}
	
	//Following sutununa sadece premium kullanicilar  eklenebilecek.!!!
	
	
	public static ResultSet getFollowers(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT FollowerID FROM follow WHERE FollowingID = " + userID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
		//userID'si bilinen user'ýn takipcilerini dondurmeli.
		//hepsi donebilir mi emin degilim ama query dogru olmasi lazim donguden cikamadim da
		
	}
	
	public static ResultSet getFollowings(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT FollowingID FROM follow WHERE FollowerID = " + userID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
		
		//userID'si bilinen user'in takip ettiklerini dondurmeli.
		
	}
	
	public static ResultSet getAllAlbums(String ArtistID) throws SQLException
	{
		Connection conn = getConnection(); 
	
		String query = "SELECT AlbumID, AlbumName FROM album WHERE ArtistID = " + ArtistID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
		//ArtistID'si bilinen artistin album isimlerini ve album id'lerini dondurmeli.
		//eksik var aklima gelmedi cozumu. rs'de cift sonuc dondurmem lazim.
		
	}
	public static ResultSet getAlbumSongs(String AlbumID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT SongID, SongName FROM song WHERE AlbumID = " + AlbumID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
		//AlbumID'si bilinen albumdeki sarki isimlerini ve sarki id'lerini dondurmeli.
		//eksik var aklima gelmedi cozumu. rs'de cift sonuc dondurmem lazim.
	}
	public static ResultSet getArtistSongs(String ArtistID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT SongID, SongName FROM song WHERE ArtistID = " + ArtistID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
	}
	
	public static boolean checkIfUserExists(String userName) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT * FROM user WHERE user.userName = '" + userName + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		
	    return !rs.next();
		
	}
	
	public static boolean checkIfEmailExists(String email) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT userID FROM user WHERE email = '" + email + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return !rs.next();
	}
	/*public static void deleteArtist(String ArtistID)
	{
		ArtistIDsi belli olan Artist'in butun sarkilarini ve albumlerini teker teker silmeli. Ilk 
		once sarkilar silinecek, sonra albumler silinecek, en son sanatcinin kendisi silinecek.
		Kontrol etmedim ama FOREIGN KEY iliskilerinden dolayi silme islemleri bu sirayla yapilmadiginda
		problem cikabilir
		
	}*/
	
	public static boolean checkPassword(String userName, String pswrd) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT * FROM user WHERE pswrd = '" + pswrd + "' and userName = '"+ userName + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs.next();
	}
	
	public static boolean checkPremium(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT * FROM user WHERE isPremium = '" + true + "' and userID = '"+ userID + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs.next();
	}
	
	public static ResultSet getAllArtists() throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT ArtistID, ArtistName FROM artist";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	public static String getAlbumRelaseDate(String AlbumID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT releaseDate FROM album WHERE AlbumID = '" + AlbumID + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		return rs.getString("releaseDate");
	}
	
	   
	  
	

}
