import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserSettingsPanel extends JPanel implements MouseListener
{
	String userID;
	JLabel newUserNameLabel;
	JTextField newUserNameTxt;
	JButton changeUsernameButton;
	
	JLabel changeYourStatusLabel;
	JComboBox<CBItem> userStatusCB;
	JButton updateUserStatsButton;
	
	JLabel newPasswordLabel;
	JTextField newPasswordTxt;
	JButton changePasswordButton;
	
	JLabel newEmailLabel;
	JTextField newEmailTxt;
	JButton changeEmailButton;
	
	JLabel newCountryLabel;
	JTextField newCountryTxt;
	JButton changeCountryButton;
	
	JLabel newCCLabel;
	JTextField newCCTxt;
	JButton changeCCButton;
	UserSettingsPanel(String userID) throws SQLException
	{
		this.userID = userID;
		this.setBackground(new Color(33,33,33));
		initUsernameField();
		initUserStatusField();
		initPasswordField();
		initEmilaField();
		initCountryField();
		initCCField();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(620,650));
	}
	public void initUsernameField() 
	{
		
		newUserNameLabel = new JLabel();
		newUserNameLabel.setText("Enter your new username");
		newUserNameLabel.setBounds(50,50, 500, 30);
		newUserNameLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newUserNameLabel.setForeground(Color.white);
		this.add(newUserNameLabel);
		
		newUserNameTxt = new JTextField();
		newUserNameTxt.setBounds(50, 80, 400,30);
		newUserNameTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newUserNameTxt);
		
		changeUsernameButton = new JButton();
		changeUsernameButton.setBounds(480, 80, 100, 30);
		changeUsernameButton.setFont(new Font("Consolas", Font.BOLD, 20));
		changeUsernameButton.setForeground(Color.white);
		changeUsernameButton.setText("Change");
		changeUsernameButton.setBackground(this.getBackground().brighter());
		changeUsernameButton.setFocusable(false);
		changeUsernameButton.addMouseListener(this);
		this.add(changeUsernameButton);
		
	}
	
	
	public void initUserStatusField()
	{
		changeYourStatusLabel = new JLabel();
		changeYourStatusLabel.setText("Change your status");
		changeYourStatusLabel.setBounds(50,140, 250, 30);
		changeYourStatusLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		changeYourStatusLabel.setForeground(Color.white);
		this.add(changeYourStatusLabel);
		
		userStatusCB = new JComboBox<CBItem>();
		userStatusCB.addItem(new CBItem("1", "Premium"));
		userStatusCB.addItem(new CBItem("0", "Free"));
		userStatusCB.setBounds(50,170,400,30);
		this.add(userStatusCB);
		
		
		updateUserStatsButton = new JButton();
		updateUserStatsButton.setBounds(480, 170, 100, 30);
		updateUserStatsButton.setFont(new Font("Consolas", Font.BOLD, 20));
		updateUserStatsButton.setForeground(Color.white);
		updateUserStatsButton.setText("Change");
		updateUserStatsButton.setBackground(this.getBackground().brighter());
		updateUserStatsButton.setFocusable(false);
		updateUserStatsButton.addMouseListener(this);
		this.add(updateUserStatsButton);
		
	}
	public void initPasswordField()
	{
		
		newPasswordLabel = new JLabel();
		newPasswordLabel.setText("Enter your new password");
		newPasswordLabel.setBounds(50,230, 500, 30);
		newPasswordLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newPasswordLabel.setForeground(Color.white);
		this.add(newPasswordLabel);
		
		newPasswordTxt = new JTextField();
		newPasswordTxt.setBounds(50, 280, 400,30);
		newPasswordTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newPasswordTxt);
		
		changePasswordButton = new JButton();
		changePasswordButton.setBounds(480, 280, 100, 30);
		changePasswordButton.setFont(new Font("Consolas", Font.BOLD, 20));
		changePasswordButton.setForeground(Color.white);
		changePasswordButton.setText("Change");
		changePasswordButton.setBackground(this.getBackground().brighter());
		changePasswordButton.setFocusable(false);
		changePasswordButton.addMouseListener(this);
		this.add(changePasswordButton);
	}
	
	public void initEmilaField()
	{
		
		newEmailLabel = new JLabel();
		newEmailLabel.setText("Enter your new email");
		newEmailLabel.setBounds(50,330, 500, 30);
		newEmailLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newEmailLabel.setForeground(Color.white);
		this.add(newEmailLabel);
		
		newEmailTxt = new JTextField();
		newEmailTxt.setBounds(50, 380, 400,30);
		newEmailTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newEmailTxt);
		
		changeEmailButton = new JButton();
		changeEmailButton.setBounds(480, 380, 100, 30);
		changeEmailButton.setFont(new Font("Consolas", Font.BOLD, 20));
		changeEmailButton.setForeground(Color.white);
		changeEmailButton.setText("Change");
		changeEmailButton.setBackground(this.getBackground().brighter());
		changeEmailButton.setFocusable(false);
		changeEmailButton.addMouseListener(this);
		this.add(changeEmailButton);
	}
	
	public void initCountryField()
	{
		
		newCountryLabel = new JLabel();
		newCountryLabel.setText("Enter your new country");
		newCountryLabel.setBounds(50,430, 500, 30);
		newCountryLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newCountryLabel.setForeground(Color.white);
		this.add(newCountryLabel);
		
		newCountryTxt = new JTextField();
		newCountryTxt.setBounds(50, 480, 400,30);
		newCountryTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newCountryTxt);
		
		changeCountryButton = new JButton();
		changeCountryButton.setBounds(480, 480, 100, 30);
		changeCountryButton.setFont(new Font("Consolas", Font.BOLD, 20));
		changeCountryButton.setForeground(Color.white);
		changeCountryButton.setText("Change");
		changeCountryButton.setBackground(this.getBackground().brighter());
		changeCountryButton.setFocusable(false);
		changeCountryButton.addMouseListener(this);
		this.add(changeCountryButton);
	}
	
	public void initCCField()
	{
		
		newCCLabel = new JLabel();
		newCCLabel.setText("Enter your new country");
		newCCLabel.setBounds(50,530, 500, 30);
		newCCLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newCCLabel.setForeground(Color.white);
		this.add(newCCLabel);
		
		newCCTxt = new JTextField();
		newCCTxt.setBounds(50, 580, 400,30);
		newCCTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newCCTxt);
		
		changeCCButton = new JButton();
		changeCCButton.setBounds(480, 580, 100, 30);
		changeCCButton.setFont(new Font("Consolas", Font.BOLD, 20));
		changeCCButton.setForeground(Color.white);
		changeCCButton.setText("Change");
		changeCCButton.setBackground(this.getBackground().brighter());
		changeCCButton.setFocusable(false);
		changeCCButton.addMouseListener(this);
		this.add(changeCCButton);
	}
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
		try {
			
			if(e.getSource() == changeUsernameButton)
			{
				String newName = newUserNameTxt.getText();
				SpotifyDB.updateUserName(this.userID, newName);
			}
			else if(e.getSource() == updateUserStatsButton)
			{
				CBItem item = (CBItem)userStatusCB.getSelectedItem();
				SpotifyDB.updateUserPremium(this.userID, item.getID());
			}
			else if(e.getSource() == changePasswordButton)
			{
				String newPW = newPasswordTxt.getText();
				SpotifyDB.updateUserPswrd(this.userID, newPW);
			}
			else if(e.getSource() == changeEmailButton)
			{
				String newEmail = newEmailTxt.getText();
				SpotifyDB.updateUserEmail(this.userID, newEmail);
			}
			else if(e.getSource() == changeCCButton)
			{
				String newCC = newCCTxt.getText();
				SpotifyDB.updateUserCardNumber(this.userID, newCC);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
