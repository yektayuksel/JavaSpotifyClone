import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class LoginPanel extends JPanel implements MouseListener
{
	Button loginButton = new Button(Button.SHAPE_CAPSULE, Button.VERTICAL, new Color(29,185,84),new Color(29,185,84).brighter(), Color.white, Color.white);
	Button signButton = new Button(Button.SHAPE_CAPSULE, Button.VERTICAL, new Color(29,185,84),new Color(29,185,84).brighter(), Color.white, Color.white);
   
	public LoginPanel()
	{
		
		
		loginButton.setText("Log in");
		loginButton.setBorderThickness(1);
		loginButton.setFont(new Font("Consolas", Font.PLAIN, 30));
		loginButton.setForeground(Color.white);
		loginButton.setFocusable(false);
		loginButton.addMouseListener(this);
		loginButton.setBounds(400, 720/2+75, 180,80);
		
		signButton.setText("Sign in");
		signButton.setBorderThickness(1);
		signButton.setFont(new Font("Consolas", Font.PLAIN, 30));
		signButton.setForeground(Color.white);
		signButton.setFocusable(false);
		signButton.addMouseListener(this);
		signButton.setBounds(700, 720/2+75, 180,80);
		
		this.add(loginButton);
		this.add(signButton);
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
	if(e.getSource() == loginButton)
	{
		new LoginScreen(new UserTypePanel());
		((Window) getRootPane().getParent()).dispose();
		
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

