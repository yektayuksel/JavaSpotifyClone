import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignInPanel extends JPanel implements MouseListener
{
	JTextField userNameText;
	JTextField passwordText;
	JButton premiumButton;
	JButton freeButton;
	SignInPanel()
	{
		this.setBackground(new Color(33,33,33));
		userNameText = new JTextField();
		userNameText.setBounds(1280/2-250, 400, 500,50);
		userNameText.setFont(new Font("Consolas", Font.BOLD, 30));
		userNameText.setText("Username");
		
		passwordText = new JTextField();
		passwordText.setBounds(1280/2-250, 480, 500,50);
		passwordText.setFont(new Font("Consolas", Font.BOLD, 30));
		passwordText.setText("Password");
		
		premiumButton = new JButton();
		premiumButton.setBounds(400, 580, 180,80);
		premiumButton.setFont(new Font("Consolas", Font.BOLD, 30));
		premiumButton.setForeground(Color.white);
		premiumButton.setText("Premium");
		premiumButton.setBackground(this.getBackground().brighter());
		premiumButton.setFocusable(false);
		premiumButton.addMouseListener(this);
		
		freeButton = new JButton();
		freeButton.setBounds(700, 580, 180,80);
		freeButton.setFont(new Font("Consolas", Font.BOLD, 30));
		freeButton.setForeground(Color.white);
		freeButton.setText("Free");
		freeButton.setBackground(this.getBackground().brighter());
		freeButton.setFocusable(false);
		freeButton.addMouseListener(this);
		
		
		this.add(userNameText);
		this.add(passwordText);
		this.add(premiumButton);
		this.add(freeButton);
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
	public void mousePressed(MouseEvent e) 
	{
		
		String userName = userNameText.getText();
		String password = passwordText.getText();
		if(e.getSource() == premiumButton)
		{
			
			//Database'e kullanýcý adý ve þifresi buradan alýancak.
			//Kullanýcý premium olarak eklencek
			new Screen(new UserTypePanel());
		}
		else if(e.getSource() == freeButton)
		{
			//Database'e kullanýcý adý ve þifresi buradan alýnacak.
			//Kullanýcý free olarak eklenecek
			new Screen(new UserTypePanel());
			
		}
		((Window) getRootPane().getParent()).dispose();
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == premiumButton)
		{
			changeEnteredBackground(premiumButton);
		}
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(e.getSource() == premiumButton)
		{
			changeExitedBackground(premiumButton);
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
