import java.awt.*;
import javax.swing.*;


public class Screen extends JFrame

{
	Screen(JPanel panel)
	{
		
		if(panel.getClass().toString().equals("class AddExistingAlbumArtist"))
		{
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2 - 250, dim.height/2-this.getSize().height/2-200);
			
		}
		else if(panel.getClass().toString().equals("class UpdateExistingCountry"))
		{
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2 - 250, dim.height/2-this.getSize().height/2-150);
		}
		else if(panel.getClass().toString().equals("class UserSettingsPanel"))
		{
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2 - 310, dim.height/2-this.getSize().height/2-325);
		}
		else
		{
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			centerFrame();
		}
	
		this.add(panel);
		this.setVisible(true);
		this.setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("images\\smallSpotifyLogo.png");    
		this.setIconImage(icon);
		
		this.pack();
		this.setTitle("Spotify");
	}
	private void centerFrame() 
	{

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - 1280/2;
        int dy = centerPoint.y - 720/2;    
        setLocation(dx, dy);
	}
	
}
