



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
		//new Screen(new FreeUserPanel());
		SpotifyDB db = new SpotifyDB();
		//db.createTable();
		db.viewTable();

		
		 
    }
	
}


