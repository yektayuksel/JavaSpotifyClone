



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;



public class Main 
{

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		
		//new Screen(new OpeningPanel());
		//new Screen(new AdminPanel());
        new Screen(new FreeUserPanel("7"));
		//new Screen(new SignInPanel());
		//new Screen(new LoginPanel());
		//new Screen(new AlbumSettings());
		//new Screen(new AddExistingAlbumArtist());
		//new Screen(new ArtistSettings());
    }
	
}


