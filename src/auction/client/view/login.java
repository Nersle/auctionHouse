/*
 * name: 			客户端登陆界面
 * description：		实现客户端登陆界面布局及逻辑处理
 * author:			李海
 */

package auction.client.view; 
 
import auction.bean.local.usersLocal; 
import com.sun.awt.AWTUtilities; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter; 
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder; 
import java.io.*;

@SuppressWarnings("serial")
public class login extends JFrame implements MouseListener {
 
	static Point origin = new Point();	
 
	ImagePanel bkim = null;
	JButton min, close, loginqueding;
	JTextField username;
	JPasswordField password;
	JLabel nameLable,pwdLable;
	String[] allparas = {"1"};

	@SuppressWarnings("unused")
	public static void main(String[] args) { 
		login login1 = new login();
	}
	public void setbutton(JButton jb) {
		
		jb.setContentAreaFilled(false);
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setOpaque(false);
	} 
	public void windowsmenu() {
		
		min = new JButton(new ImageIcon("image/Loginmin.png"));
		min.setBounds(346, 0, 27, 21);
		min.setRolloverIcon(new ImageIcon("image/LoginminC.png"));
		setbutton(min);
		min.setToolTipText("最小化");
		
		close = new JButton(new ImageIcon("image/Loginclose.png"));
		close.setBounds(370, 0, 29, 21);
		close.setRolloverIcon(new ImageIcon("image/LogincloseC.png"));
		setbutton(close);
		close.setToolTipText("关闭");
		
		bkim.add(min);
		bkim.add(close);
	}

 
	public login() { 
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		} 
		Image loginbk = null;
		try {		 
			loginbk = ImageIO.read(new File( ("image/loginbk.png") ));
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		bkim = new ImagePanel(loginbk);
		bkim.setLayout(null);
		
	 
		
//		Vector<String> userid =new Vector<String>(); 
//		userid.add("admin");
//		userid.add("2");
//		user = new JComboBox<String>(userid); 
//		
//		user.setEditable(true);
//		user.setBounds(131, 145, 187, 26);
//		user.setFont(MyFont.login);
//		user.addMouseListener(this);
		nameLable = new JLabel("用户名"); 
		nameLable.setBounds(75,143,187,26);
		nameLable.setFont(MyFont.loginText); 
		
		pwdLable=new JLabel("密  码"); 
		pwdLable.setBounds(75, 180, 178, 25);
		pwdLable.setFont(MyFont.loginText);
		
		username = new JTextField();
		username.setEditable(true);
		username.setBounds(131,143,187,26);
		username.setFont(MyFont.login);
		
		
		JScrollPane jsp = new JScrollPane();
		jsp.add(username);
		jsp.setBounds(131, 145, 187, 26);
		jsp.setEnabled(true);
		
		password = new JPasswordField(50);
		password.setBounds(135, 180, 178, 25);
		password.setBorder(new MatteBorder(0, 0, 0, 0, Color.blue));
		password.setOpaque(false);
		password.setFont(MyFont.login);
		password.setEchoChar('*');
		
		loginqueding = new JButton(new ImageIcon("image/loginqueding.png"));
		loginqueding.setRolloverIcon(new ImageIcon( "image/loginquedingC.png"));
		loginqueding.setBounds(110, 253, 180, 31);
		setbutton(loginqueding);
		
		bkim.add(nameLable);
		bkim.add(username);
		bkim.add(pwdLable);
		bkim.add(password);
		bkim.add(loginqueding);
		
		windowsmenu(); 
		this.setUndecorated(true);
		WindowMove();
		setOpacity();
		this.add(bkim);
		this.setSize(400, 290);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	public void WindowMove() {
		 
		this.addMouseListener(new MouseAdapter() 
		{
	        public void mousePressed(MouseEvent e)
	        {  
	                origin.x = e.getX();  
	                origin.y = e.getY();
	        }
		});
		this.addMouseMotionListener(new MouseMotionAdapter()
		{
	        public void mouseDragged(MouseEvent e) 
	        {  
	                Point p =getLocation();  
	              setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
	        }
	     });
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		usersLocal my=new usersLocal();
		// TODO Auto-generated method stub
		if(e.getSource() == min) {			
			setState(JFrame.ICONIFIED);
		}
		if(e.getSource() == close) {			
			dispose();
		}
		if(e.getSource() == loginqueding) {			
			String userName = username.getText().trim();
			String upassword = new String(this.password.getPassword());
			
			if (userName.equals("")) {				
				JOptionPane.showMessageDialog(this, "请输入用户名再登录");
				return;
			}
			if (upassword.equals("")) {				
				JOptionPane.showMessageDialog(this, "请输入密码再登录");
				return;
			}
 
			if (my.auth(userName, upassword)>0) { 
					this.dispose();
				 	new clientPanel();				
			}else {
				
				JOptionPane.showMessageDialog(this, "密码不正确，请重新输入密码");
				this.password.setText("");
				return;
			}
			
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) { 
	}
	@Override
	public void mouseExited(MouseEvent e) { 
	}
	@Override
	public void mousePressed(MouseEvent e) {  
	}
	@Override
	public void mouseReleased(MouseEvent e) { 
	} 
	
	public void setOpacity() { 
		AWTUtilities.setWindowOpacity(login.this, 0f);
		ActionListener lisener = new ActionListener() {
			
			float alpha = 0;
			@Override
			public void actionPerformed(ActionEvent e) { 
				if (alpha < 0.9) { 
					AWTUtilities.setWindowOpacity(login.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(login.this, 1);
					Timer source = (Timer) e.getSource();
					source.stop();
				}
			}
		}; 
		new Timer(50, lisener).start();
	}
}
