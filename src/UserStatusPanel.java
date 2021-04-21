import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class UserStatusPanel extends JPanel implements MouseListener
{
	JButton premium;
	JButton free;
	UserStatusPanel()
	{
		this.setBackground(new Color(33,33,33));
		premium = new JButton();
		premium.setBounds(1280/2-100, 720/2+50, 200, 75);
		premium.setFont(new Font("Consolas", Font.BOLD, 30));
		premium.setForeground(Color.white);
		premium.setText("Premium");
		premium.setBackground(this.getBackground().brighter());
		premium.setFocusable(false);
		premium.addMouseListener(this);
		
		free = new JButton();
		free.setBounds(1280/2-100, 720/2+150, 200, 75);
		free.setFont(new Font("Consolas", Font.BOLD, 30));
		free.setForeground(Color.white);
		free.setText("Free");
		free.setBackground(this.getBackground().brighter());
		free.setFocusable(false);
		free.addMouseListener(this);
		this.add(free);
		this.add(premium);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1280,720));
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(new Color(33,33,33));
		g2D.fillRect(0, 0, 1280, 720);
		Image spotifyLogo = new ImageIcon("images\\SpotifyLogo.png").getImage();
		g2D.drawImage(spotifyLogo, (1280-800)/2, (720-540)/2, 800,240,null);
	}
public void mousePressed(MouseEvent e) {
		
		if(e.getSource() == free)
		{
			//new LoginScreen(new freeStatusPanel());
			
		}
		else if(e.getSource() == premium)
		{
			//new LoginScreen(new premiumPanel());
		}
		((Window) getRootPane().getParent()).dispose();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == free)
		{
			changeEnteredBackground(free);
		}
		else if(e.getSource() == premium)
		{
			changeEnteredBackground(premium);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(e.getSource() == free)
		{
			changeExitedBackground(free);
		}
		else if(e.getSource() == premium)
		{
			changeExitedBackground(premium);
		}
		
	}
	
	public void changeEnteredBackground(JButton button)
	{
		button.setBackground(button.getBackground().darker());
	}
	public void changePressedBackground(JButton button)
	{
		button.setBackground(this.getBackground().darker());
	}
	public void changeExitedBackground(JButton button)
	{
		button.setBackground(this.getBackground().brighter());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
