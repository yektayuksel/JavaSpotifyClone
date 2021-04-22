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

public class UserTypePanel extends JPanel implements MouseListener
{
	JButton admin;
	JButton user;
	
	public UserTypePanel()
	{
		this.setBackground(new Color(33,33,33));
		admin = new JButton();
		admin.setBounds(1280/2-100, 720/2+50, 200, 75);
		admin.setFont(new Font("Consolas", Font.BOLD, 30));
		admin.setForeground(Color.white);
		admin.setText("Admin");
		admin.setBackground(this.getBackground().brighter());
		admin.setFocusable(false);
		admin.addMouseListener(this);
		
		user = new JButton();
		user.setBounds(1280/2-100, 720/2+150, 200, 75);
		user.setFont(new Font("Consolas", Font.BOLD, 30));
		user.setForeground(Color.white);
		user.setText("User");
		user.setBackground(this.getBackground().brighter());
		user.setFocusable(false);
		user.addMouseListener(this);
		
		this.add(admin);
		this.add(user);
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
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource() == user)
		{
			changePressedBackground(user);
		}
		else if(e.getSource() == admin)
		{
			changePressedBackground(admin);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getSource() == user)
		{
			new Screen(new LoginPanel());
			
		}
		else if(e.getSource() == admin)
		{
			String password = JOptionPane.showInputDialog("Enter Your Password");
			//þifre admin þifresi mi kontrol et ve alttaki kodu çalýþtýr.
			//þifre yanlýþsa uyarý ver ve bu ekranda kalsýn.
			if(password != null)
			{
				new Screen(new AdminPanel());
				((Window) getRootPane().getParent()).dispose();
			}
			
			
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == user)
		{
			changeEnteredBackground(user);
		}
		else if(e.getSource() == admin)
		{
			changeEnteredBackground(admin);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(e.getSource() == user)
		{
			changeExitedBackground(user);
		}
		else if(e.getSource() == admin)
		{
			changeExitedBackground(admin);
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
	
	
	
}
