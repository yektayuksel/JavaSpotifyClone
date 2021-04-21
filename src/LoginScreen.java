import java.awt.*;
import javax.swing.*;


public class LoginScreen extends JFrame
{
	LoginScreen(JPanel panel)
	{
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
	}
	
}
