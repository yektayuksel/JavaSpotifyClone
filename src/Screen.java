import java.awt.*;
import javax.swing.*;


public class Screen extends JFrame

{
	Screen(JPanel panel)
	{
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
		this.setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("images\\smallSpotifyLogo.png");    
		this.setIconImage(icon);
		centerFrame();
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
