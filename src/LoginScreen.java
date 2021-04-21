import java.awt.*;
import javax.swing.*;


public class LoginScreen extends JFrame
{
	LoginPanel loginPanel = new LoginPanel();
	LoginScreen()
	{
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(loginPanel);
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
	}
	
}
