import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInPanel extends JPanel implements MouseListener
{
	JTextField userNameText;
	JTextField passwordText;
	JTextField emailText;
	
	JComboBox<CBItem> countryCB;
	JButton premiumButton;
	JButton freeButton;
	final int TXTF_LOC_X = 390;
	final int TXTF_LOC_Y = 350;
	final int TXTF_GAPS = 60;
	final int TXTF_W = 500;
	final int TXTF_H = 40;
	final int TXTF_PUNTO = 25;
			
	SignInPanel() throws SQLException
	{
		this.setBackground(new Color(33,33,33));
		
		initTextFields();
		initButtons();
		initCountryCB();
		
		this.add(userNameText);
		this.add(passwordText);
		this.add(emailText);
		this.add(premiumButton);
		this.add(freeButton);
		this.add(countryCB);
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
		userNameText.setBounds(TXTF_LOC_X, TXTF_LOC_Y, TXTF_W,TXTF_H);
		userNameText.setFont(new Font("Consolas", Font.BOLD, TXTF_PUNTO));
		userNameText.setText("Username");
		
		passwordText = new JTextField();
		passwordText.setBounds(TXTF_LOC_X, (int)userNameText.getLocation().getY()+TXTF_GAPS, TXTF_W,TXTF_H);
		passwordText.setFont(new Font("Consolas", Font.BOLD, TXTF_PUNTO));
		passwordText.setText("Password");
		
		emailText = new JTextField();
		emailText.setBounds(TXTF_LOC_X, (int)passwordText.getLocation().getY()+TXTF_GAPS, TXTF_W,TXTF_H);
		emailText.setFont(new Font("Consolas", Font.BOLD, TXTF_PUNTO));
		emailText.setText("Email");
		
	}
	
	public void initCountryCB() throws SQLException
	{
		countryCB = new JComboBox<CBItem>();
		initCountryCB2();
		countryCB.setBounds(TXTF_LOC_X, (int)emailText.getLocation().getY()+TXTF_GAPS, TXTF_W,TXTF_H);
		countryCB.setFont(new Font("Consolas", Font.BOLD, TXTF_PUNTO));
		this.add(countryCB);
		
	}
	public void initCountryCB2() throws SQLException
	{
		ResultSet rs = SpotifyDB.getAllCountires();
		countryCB.removeAllItems();
		while(rs.next())
		{
			countryCB.addItem(new CBItem(rs.getString("countryID"), rs.getString("country")));
		}
		repaint();
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
		String pswrd = passwordText.getText();
		String email = emailText.getText();
		
		
		try 
		{
			if(!SpotifyDB.checkIfUserExists(userName))
			{
				JOptionPane.showMessageDialog(null, "This username is already taken", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!SpotifyDB.checkIfEmailExists(email))
			{
				JOptionPane.showMessageDialog(null, "This e-mail is already taken", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		if(!isValidEmail(email))
		{
			JOptionPane.showMessageDialog(null, "Enter a valid E-mail", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
	
		if(e.getSource() == premiumButton)
		{
			String countryID = ((CBItem)countryCB.getSelectedItem()).getID();
			String cardNumber = JOptionPane.showInputDialog("Enter Your Card Number");
			if(cardNumber == null)
			{
				return;
			}
			else if(cardNumber.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Enter a card number", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			SpotifyDB.addUser(userName, email, pswrd, countryID, true, cardNumber, true);
			new Screen(new OpeningPanel());
		}
		else if(e.getSource() == freeButton)
		{
			String countryID = ((CBItem)countryCB.getSelectedItem()).getID();
			SpotifyDB.addUser(userName, email, pswrd, countryID, false, null, false);
			
			new Screen(new OpeningPanel());
			
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
	public void mouseEntered(MouseEvent e) 
	{
			changeEnteredBackground((JComponent)e.getSource());
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		changeExitedBackground((JComponent)e.getSource());
	}
	
	public void changeEnteredBackground(JComponent obj)
	{
		obj.setBackground(obj.getBackground().darker());
	}
	public void changePressedBackground(JComponent obj)
	{
		obj.setBackground(this.getBackground().darker());
	}
	public void changeExitedBackground(JComponent obj)
	{
		obj.setBackground(this.getBackground().brighter());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
