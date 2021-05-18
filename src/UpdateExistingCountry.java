import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class UpdateExistingCountry extends JPanel implements MouseListener
{
	JLabel selectCountryLabel;
	JComboBox<CBItem> selectCountryCB;
	
	JLabel newCountryTxtLabel;
	JTextField newCountryTxt;
	
	JButton updateButton;
	UpdateExistingCountry() throws SQLException
	{
		this.setBackground(new Color(33,33,33));
		initCountryCB();
		initCountryTxt();
		initComboBoxes();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500,300));
	}
	public void initCountryCB() 
	{
		
		selectCountryLabel = new JLabel();
		selectCountryLabel.setText("Select the country");
		selectCountryLabel.setBounds(50,50, 250, 30);
		selectCountryLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		selectCountryLabel.setForeground(Color.white);
		this.add(selectCountryLabel);
		
		selectCountryCB = new JComboBox<CBItem>();
		selectCountryCB.setEditable(true);
		selectCountryCB.setBounds(50, 90, 400, 30);
		this.add(selectCountryCB);
	}
	public void initCountryTxt()
	{
		newCountryTxtLabel = new JLabel();
		newCountryTxtLabel.setText("Enter new country name");
		newCountryTxtLabel.setBounds(50,140, 250, 30);
		newCountryTxtLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		newCountryTxtLabel.setForeground(Color.white);
		this.add(newCountryTxtLabel);
		
		newCountryTxt = new JTextField();
		newCountryTxt.setBounds(50, 170, 400,30);
		newCountryTxt.setFont(new Font("Consolas", Font.BOLD, 20));
		this.add(newCountryTxt);
		
		
		updateButton = new JButton();
		updateButton.setBounds(350, 220, 100, 30);
		updateButton.setFont(new Font("Consolas", Font.BOLD, 20));
		updateButton.setForeground(Color.white);
		updateButton.setText("Update");
		updateButton.setBackground(this.getBackground().brighter());
		updateButton.setFocusable(false);
		updateButton.addMouseListener(this);
		this.add(updateButton);
		
	}
	
	public void initComboBoxes() throws SQLException
	{
		ResultSet rs = SpotifyDB.getAllCountires();
		while(rs.next())
		{
			CBItem item = new CBItem(rs.getString("countryID"), rs.getString("country"));
			selectCountryCB.addItem(item);
		}
		
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == updateButton)
		{
			String countryID = ((CBItem)selectCountryCB.getSelectedItem()).getID();
			String country = newCountryTxt.getText();
			try {
				SpotifyDB.updateCountry(countryID, country);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
