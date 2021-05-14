import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class SpotifyDB 
{
	
	
	
	SpotifyDB() throws ClassNotFoundException
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
			JOptionPane.showMessageDialog(null, userName+", you have been signed up successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
	public static void follow(String UserID, String followingID) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement Follow = conn.prepareStatement("INSERT INTO follow (FollowerID,FollowingID) VALUES('"+UserID+"','"+followingID+"'");
		Follow.executeUpdate();
	}
	public static void addToPlaylist(String userID, String SongID, String genre) throws SQLException
	{
		
		Connection conn = getConnection(); 
		
		String query = "SELECT genre FROM song WHERE SongID = '" + SongID + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		try 
		{			
			PreparedStatement addtoplaylst = conn.prepareStatement("INSERT INTO playlist (userID,SongID,genre) VALUES('"+userID+"','"+SongID+"','"+genre+"')");
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
		String query = "SELECT s.SongName, s.duration, a.ArtistName FROM song as s, playlist as p, artist as a WHERE s.genre = '" + genre + "'and p.genre = '" + genre + "'and s.SongID = p.SongID and p.userID = '" + userID + "' and s.ArtistID = a.ArtistID;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	/*public static ResultSet getAllPlaylist(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT SongID, FROM playlist WHERE userID = " + userID + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}*/
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
	
	
	
	public static ResultSet getFollowers(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT f.FollowerID, u.userName FROM follow f, user uWHERE f.FollowingID = '" + userID + "' and f.FollowerID = u.userID";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	public static ResultSet getFollowings(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT FollowingID FROM follow WHERE FollowerID = " + userID;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
	}
	
	public static ResultSet getAllAlbums(String ArtistID) throws SQLException
	{
		Connection conn = getConnection(); 
	
		String query = "SELECT AlbumID, AlbumName FROM album WHERE ArtistID = '" + ArtistID + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	public static ResultSet getAlbumSongs(String AlbumID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT SongID, SongName, genre FROM song WHERE AlbumID = '" + AlbumID + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
		
	}
	public static ResultSet getArtistSongs(String ArtistID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT SongID, SongName FROM song WHERE ArtistID = '" + ArtistID + "'";
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
	
	public static void deleteSong(String SongID) throws SQLException 
	{
		Connection conn = getConnection();
		PreparedStatement deleteSong = conn.prepareStatement("DELETE FROM song WHERE SongID = '" + SongID +"'");
		deleteSong.executeUpdate();
		
	}
	
	public static void deleteAlbum(String AlbumID) throws SQLException
	{
		ResultSet rs = getAlbumSongs(AlbumID);
		Connection conn = getConnection();
		
		while(rs.next())
		{
			 deleteSong(rs.getString("SongID"));
		}
		
		PreparedStatement deleteAlbum = conn.prepareStatement("DELETE FROM album WHERE AlbumID = '" + AlbumID +"'");
		deleteAlbum.executeUpdate();
	}
	
	public static void deleteArtist(String ArtistID) throws SQLException
	{
		ResultSet rs = getAllAlbums(ArtistID);
		Connection conn = getConnection();
		
		while(rs.next())
		{
			deleteAlbum(rs.getString("AlbumID"));
		}
		
		PreparedStatement deleteAlbum = conn.prepareStatement("DELETE FROM artist WHERE ArtistID = '" + ArtistID +"'");
		deleteAlbum.executeUpdate();
	}
	public static void deletePlayListsSong(String SongID) throws SQLException
	{
		Connection conn = getConnection();
		PreparedStatement deletePlayListsSong = conn.prepareStatement("DELETE FROM playlist WHERE songID = '" + SongID +"'");
		deletePlayListsSong.executeUpdate();
	}
	public static void unfollow(String userID, String followingID) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement unFollow = conn.prepareStatement("DELETE FROM follow WHERE FollowerID = '" + userID + "' and FollowingID = '"+ followingID + "'");
		unFollow.executeUpdate();
	}
	public static ResultSet getPremiumUsers(String userID) throws SQLException
	{
		Connection conn = getConnection(); 
		String query = "SELECT userName, userID FROM user WHERE isPremium = true and userID != '" + userID +"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	public static void updateArtistName(String artistID, String artistCountry) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateArtistName = conn.prepareStatement("UPDATE artist SET ArtistName = '" + artistCountry + "'  WHERE ArtistID = '"+ artistID +"'");
		updateArtistName.executeUpdate();
	}
	public static void updateArtistCountry(String artistID, String artistCountry) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateArtistCountry = conn.prepareStatement("UPDATE artist SET Country = '" + artistCountry + "'  WHERE ArtistID = '"+ artistID +"'");
		updateArtistCountry.executeUpdate();
	}
	public static void updateAlbumName(String albumID, String albumName) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateAlbumName = conn.prepareStatement("UPDATE album SET AlbumName = '" + albumName + "'  WHERE AlbumID = '"+ albumID +"'");
		updateAlbumName.executeUpdate();
	}
	public static void updateAlbumReleaseDate(String albumID, String AlbumReleasedate) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateAlbumReleasedate = conn.prepareStatement("UPDATE album SET releaseDate = '" + AlbumReleasedate + "'  WHERE AlbumID = '"+ albumID +"'");
		updateAlbumReleasedate.executeUpdate();
	}
	public static void updateAlbumGenre(String albumID, String albumGenre) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateAlbumgenre = conn.prepareStatement("UPDATE album SET genre = '" + albumGenre + "'  WHERE AlbumID = '"+ albumID +"'");
		updateAlbumgenre.executeUpdate();
	}
	public static void updateSongName(String songID, String songName) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateSongName = conn.prepareStatement("UPDATE song SET SongName = '" + songName + "'  WHERE SongID = '"+ songID +"'");
		updateSongName.executeUpdate();
	}
	public static void updateSongGenre(String songID, String songGenre) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateSongGenre = conn.prepareStatement("UPDATE song SET genre = '" + songGenre + "'  WHERE SongID = '"+ songID +"'");
		updateSongGenre.executeUpdate();
	}
	public static void updateSongDuration(String songID, String duration) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateSongDuration = conn.prepareStatement("UPDATE song SET duration = '" + duration + "'  WHERE SongID = '"+ songID +"'");
		updateSongDuration.executeUpdate();
	}
	public static void updateSongTimesPlayed(String songID, String TimesPlayed) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateSongTimesPlayed = conn.prepareStatement("UPDATE song SET timesPlayed = '" + TimesPlayed + "'  WHERE SongID = '"+ songID +"'");
		updateSongTimesPlayed.executeUpdate();
	}
	public static void updateSongReleaseDate(String songID, String songReleaseDate) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateSongReleaseDate = conn.prepareStatement("UPDATE song SET releaseDate = '" + songReleaseDate + "'  WHERE SongID = '"+ songID +"'");
		updateSongReleaseDate.executeUpdate();
	}
	public static void updateUserName(String UserID, String UserName) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateUserName = conn.prepareStatement("UPDATE user SET userName = '" + UserName + "'  WHERE userID = '"+ UserID +"'");
		updateUserName.executeUpdate();
	}
	public static void updateUserEmail(String UserID, String Email) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateUserEmail = conn.prepareStatement("UPDATE user SET email = '" + Email + "'  WHERE userID = '"+ UserID +"'");
		updateUserEmail.executeUpdate();
	}
	public static void updateUserPswrd(String UserID, String Pswrd) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateUserPswrd = conn.prepareStatement("UPDATE user SET pswrd = '" + Pswrd + "'  WHERE userID = '"+ UserID +"'");
		updateUserPswrd.executeUpdate();
	}
	public static void updateUserCountry(String UserID, String UserCountry) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateUserCountry = conn.prepareStatement("UPDATE user SET Country = '" + UserCountry + "'  WHERE userID = '"+ UserID +"'");
		updateUserCountry.executeUpdate();
	}
	
	public static void updateUserPremium(String userID, String premiumStat) throws SQLException
	{
		
			Connection conn = getConnection(); 
			PreparedStatement updateUserPremium = conn.prepareStatement("UPDATE user SET isPremium = '" + premiumStat + "'  WHERE userID = '"+ userID +"'");
			updateUserPremium.executeUpdate();
		
	}
	public static void updateUserCardNumber(String UserID, String CardNumber) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateUserCardNumber = conn.prepareStatement("UPDATE user SET cardNumber = '" + CardNumber + "'  WHERE userID = '"+ UserID +"'");
		updateUserCardNumber.executeUpdate();
	}
	public static void updateUserIsPaid(String UserID, String IsPaid) throws SQLException
	{
		Connection conn = getConnection(); 
		PreparedStatement updateUserIsPaid = conn.prepareStatement("UPDATE user SET isPaid = '" + IsPaid + "'  WHERE userID = '"+ UserID +"'");
		updateUserIsPaid.executeUpdate();
	}

}