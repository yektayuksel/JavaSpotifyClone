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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class AdminPanel extends JPanel implements MouseListener
{
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ArrayList<String> buttonTexts = new ArrayList<String>();
	
	AdminPanel()
	{
		this.setBackground(new Color(33,33,33));
		buttonTexts.add("Artist Settings");
		buttonTexts.add("Album Settings");
		buttonTexts.add("Song Settings");
		
		
		
		
		int x,y;
		x = 490;
		y = 380;
		for(int i = 0; i < buttonTexts.size(); i++)
		{
			buttons.add(createButton(buttonTexts.get(i), x,y));
			y+=100;
			this.add(buttons.get(i));
			if(i == 2)
			{
				x += 250;
				y = 380;
			}
		}
		
	
		this.setLayout(new BorderLayout());
		
		this.setPreferredSize(new Dimension(1280,720));
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(new Color(33,33,33));
		g2D.fillRect(0, 0, 1280, 720);
		Image spotifyLogo = new ImageIcon("images\\SpotifyLogo.png").getImage();
		g2D.drawImage(spotifyLogo, 240, 90, 800,240,null);
	}
	
	public JButton createButton(String text, int x, int y)
	{
		JButton button = new JButton();
		button = new JButton();
		button.setBounds(x, y, 300, 75);
		button.setFont(new Font("Consolas", Font.BOLD, 25));
		button.setForeground(Color.white);
		button.setText(text);
		button.setBackground(this.getBackground().brighter());
		button.setFocusable(false);
		button.addMouseListener(this);
		return button;
	}
	public void mousePressed(MouseEvent e) 
    {
		
		
		
		JButton button = (JButton)e.getSource();
		if(button.getText().equals("Artist Settings"))
		{
			try {
				new Screen(new ArtistSettings());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(button.getText().equals("Song Settings"))
		{
			try {
				new Screen(new SongSettings());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(button.getText().equals("Album Settings"))
		{
			new Screen(new AlbumSettings());
		}
		((Window) getRootPane().getParent()).dispose();
	}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
		if(buttons.contains(e.getSource()))
		{
		
			changeEnteredBackground((JButton)e.getSource());
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) 
	{
		

		if(buttons.contains(e.getSource()))
		{
		
			changeExitedBackground((JButton)e.getSource());
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
