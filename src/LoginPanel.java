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

public class LoginPanel extends JPanel implements MouseListener
{
	JButton loginButton;
	JTextField userNameText;
	LoginPanel()
	{
		this.setBackground(new Color(33,33,33));
		
		userNameText = new JTextField();
		userNameText.setBounds(1280/2-250, 400, 500,50);
		userNameText.setFont(new Font("Consolas", Font.BOLD, 30));
		
		loginButton = new JButton();
		loginButton.setBounds(1280/2-100, 500, 200, 75);
		loginButton.setFont(new Font("Consolas", Font.BOLD, 30));
		loginButton.setForeground(Color.white);
		loginButton.setText("Log in");
		loginButton.setBackground(this.getBackground().brighter());
		loginButton.setFocusable(false);
		loginButton.addMouseListener(this);
		
		
		
		this.add(loginButton);
		this.add(userNameText);
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
		
		
		if(e.getSource() == loginButton)
		{
			//Kullan�c�y� kontrol et ve premium veya free kullan�c� olmas�na g�re aksiyon al
		}
		((Window) getRootPane().getParent()).dispose();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == loginButton)
		{
			changeEnteredBackground(loginButton);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		
		if(e.getSource() == loginButton)
		{
			changeExitedBackground(loginButton);
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
