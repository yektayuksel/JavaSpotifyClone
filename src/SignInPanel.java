import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInPanel extends JPanel implements MouseListener
{
	JTextField userNameText;
	JTextField passwordText;
	JTextField emailText;
	JButton premiumButton;
	JButton freeButton;
	SignInPanel()
	{
		this.setBackground(new Color(33,33,33));
		
		initTextFields();
		initButtons();
		
		
		this.add(userNameText);
		this.add(passwordText);
		this.add(emailText);
		this.add(premiumButton);
		this.add(freeButton);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1280,720));
	}
	
	private void initButtons()
	{
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
		
		
	}
	private void initTextFields()
	{
		userNameText = new JTextField();
		userNameText.setBounds(1280/2-250, 350, 500,50);
		userNameText.setFont(new Font("Consolas", Font.BOLD, 30));
		userNameText.setText("Username");
		
		passwordText = new JTextField();
		passwordText.setBounds(1280/2-250, 430, 500,50);
		passwordText.setFont(new Font("Consolas", Font.BOLD, 30));
		passwordText.setText("Password");
		
		emailText = new JTextField();
		emailText.setBounds(1280/2-250, 510, 500,50);
		emailText.setFont(new Font("Consolas", Font.BOLD, 30));
		emailText.setText("Email");
		
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
		String email = emailText.getText();
		
		//
		if(!isValidEmail(email))
		{
			JOptionPane.showMessageDialog(null, "Enter a valid E-mail", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//Usarname ve Email database'de var mi yok mu kontrol et.
		//Yoklar ise ekleme islemlerine devam et.
	
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
	
	 public static boolean isValidEmail(String email)
	 {
	     String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
	                         "[a-zA-Z0-9_+&*-]+)*@" +
	                         "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
	                         "A-Z]{2,7}$";
	                              
	     Pattern pat = Pattern.compile(emailRegex);
	     if (email == null)
	         return false;
	     return pat.matcher(email).matches();
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
