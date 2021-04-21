import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignInPanel extends JPanel implements MouseListener
{
	JTextField userNameText;
	JButton premiumButton;
	JButton freeButton;
	SignInPanel()
	{
		this.setBackground(new Color(33,33,33));
		userNameText = new JTextField();
		userNameText.setBounds(1280/2-250, 400, 500,50);
		userNameText.setFont(new Font("Consolas", Font.BOLD, 30));
		
		premiumButton = new JButton();
		premiumButton.setBounds(400, 500, 180,80);
		premiumButton.setFont(new Font("Consolas", Font.BOLD, 30));
		premiumButton.setForeground(Color.white);
		premiumButton.setText("Premium");
		premiumButton.setBackground(this.getBackground().brighter());
		premiumButton.setFocusable(false);
		premiumButton.addMouseListener(this);
		
		freeButton = new JButton();
		freeButton.setBounds(700, 500, 180,80);
		freeButton.setFont(new Font("Consolas", Font.BOLD, 30));
		freeButton.setForeground(Color.white);
		freeButton.setText("Free");
		freeButton.setBackground(this.getBackground().brighter());
		freeButton.setFocusable(false);
		freeButton.addMouseListener(this);
		
		
		this.add(userNameText);
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
	public void mousePressed(MouseEvent e) {
		
		if(e.getSource() == premiumButton)
		{
			String userName = userNameText.getText();
			//Database'e kullanýcý adý buradan alýancak.
			//Kullanýcý premium olarak eklencek
			new Screen(new UserTypePanel());
		}
		else if(e.getSource() == freeButton)
		{
			String userName = userNameText.getText();
			//Database'e kullanýcý adý buradan alýnacak.
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
