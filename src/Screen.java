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
		this.pack();
	}
	
}
