/*
 * name: 			管理员查找库存界面
 * description：		实现管理员查找库存界面布局及逻辑处理
 * author:			李海
 */

package auction.server.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.UIManager;

 
import auction.bean.local.goodsLocal;

import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class lookProductStcok extends JDialog implements ActionListener, MouseListener {
	
	//全局的位置变量，用于表示鼠标在窗口上的位置
	static Point origin = new Point();
	 // 用于获得窗口的大小
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	// 定义组件
	JPanel closejp,findpanel, showtabeljp, all;
	
	JButton close;
	
	JComboBox<String> type, startnum, endnum;
	
	JTable lookstcoktable;
	JScrollPane jsp;
	
	String[] paras = {"1"};
	goodsLocal lsm;
	// 窗口淡入淡出函数
	public void setOpacity() {
		
		// 窗口设置淡入淡出代码段
		AWTUtilities.setWindowOpacity(this, 0f);
		ActionListener lisener = new ActionListener() { 
			float alpha = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (alpha < 0.9) { 
					AWTUtilities.setWindowOpacity(lookProductStcok.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(lookProductStcok.this, 1);
					Timer source = (Timer) e.getSource();
					source.stop();
				}
			}
		};
		// 设置线程控制
		new Timer(50, lisener).start();
	}
	// 窗体移动函数
	public void WindowMove() {
		
		//设置没有标题的窗口可以拖动
		this.addMouseListener(new MouseAdapter() 
		{
	        public void mousePressed(MouseEvent e)
	        {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
	                origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
	                origin.y = e.getY();
	        }
		});
		this.addMouseMotionListener(new MouseMotionAdapter()
		{
	        public void mouseDragged(MouseEvent e) 
	        {  
	                Point p =getLocation();  //当鼠标拖动时获取窗口当前位置
	                setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
	        }
	     });
	}
	
	public void setbutton(JButton jb, int type) {
		
		if (type == 1) {			
			jb.setContentAreaFilled(false);
		}
		
		jb.setForeground(Color.blue);
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setOpaque(false);
		jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jb.setFont(MyFont.PaddInfotext);
	}
	// 初始化关闭按钮
	public void initColse() {			
		// 关闭按钮
		close = new JButton(new ImageIcon("image/JDialogClose.png"));
		close.setRolloverIcon(new ImageIcon("image/JDialogCloseC.png"));
		close.setBounds((int)(width*0.8f)-365, 13, 22, 22);
		close.setToolTipText("关闭");
		setbutton(close, 1);
		
		closejp.add(close);
	}
	// 初始化查找面板
	public void initfindpanel() {		
		
		Font PaddInfotext=new Font("新宋体",Font.PLAIN,20);	 
		// 开始的数量
		Vector<String> temp1=new Vector<String>();
		
		for (int i = 0; i <= 38; i++) {
			
			temp1.add(String.valueOf(i));
		}
		startnum = new JComboBox<String>(temp1);
		startnum.setEditable(true);
		startnum.setFont(PaddInfotext);
		startnum.addActionListener(this); 
		
		JPanel startjp = new JPanel(new GridLayout(1, 1));
		startjp.setPreferredSize(new Dimension(115, 40));
		startjp.add(startnum);
		 
		Vector<String> temp2=new Vector<String>();
		
		for (int i = 68; i >= 1; i--) {			
			temp2.add(String.valueOf(i));
		}
		endnum = new JComboBox<String>(temp2);
		endnum.setEditable(true);
		endnum.setFont(PaddInfotext);
		endnum.addActionListener(this);
		
		JPanel endjp = new JPanel(new GridLayout(1, 1));
		endjp.setPreferredSize(new Dimension(115, 40));
		endjp.add(endnum);
		
		findpanel = new JPanel();
		findpanel.setPreferredSize(new Dimension(this.getX(), 60));
		findpanel.setOpaque(false); 
		 
		JLabel kong = new JLabel("　　");
		findpanel.add(kong);
		JLabel info = new JLabel("产品库存量区间:");
		info.setFont(PaddInfotext);
		findpanel.add(info);
		findpanel.add(startjp);
		JLabel to = new JLabel("————");
		to.setFont(PaddInfotext);
		findpanel.add(to);
		findpanel.add(endjp);
		
		closejp = new JPanel(null);
		closejp.setPreferredSize(new Dimension(this.getX(), 45));
		closejp.setOpaque(false);
		
		initColse();
		
		JPanel findpanelall = new JPanel(new BorderLayout());
		findpanelall.setPreferredSize(new Dimension(this.getX(), 100));
		
		findpanelall.add(closejp, "North");
		findpanelall.add(findpanel, "Center");
		findpanelall.setOpaque(false);		
		
		all.add(findpanelall, "North");
	}
	public void initshowtable() {
		
		String sql = "select a.Pid, b.PName, b.PType, a.Num from Stcok a, ProductInfo b where a.Pid = b.Pid and 1 = ? order by a.Num";
		lsm = new goodsLocal();
		lsm.showOverageTable("", 0, 30);
		
		lookstcoktable = new JTable(lsm);
		Tools.setTableStyle(lookstcoktable);
		
		jsp = new JScrollPane(lookstcoktable);
		Tools.setJspStyle(jsp);
		
		showtabeljp = new JPanel(new BorderLayout());
		showtabeljp.setOpaque(false);
		showtabeljp.setPreferredSize(new Dimension(this.getX(),	398));
		showtabeljp.add(jsp);		
		all.add(showtabeljp, "Center");
	}
	// 构造函数
	public lookProductStcok() { 
		// 设置控件的样式为当前系统的样式
		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		all = new JPanel(new BorderLayout());
		all.setPreferredSize(new Dimension(560,	498));
		all.setBackground(new Color(60, 148, 212));
		all.setBorder(BorderFactory.createEtchedBorder());
		//all.setBorder(new MatteBorder(2, 1, 1, 1, Color.GRAY));
		
		initfindpanel();
		initshowtable();
		
		this.add(all);
		
		this.setUndecorated(true);
		this.setSize((int)(width*0.8f)-330, (int)(height*0.8f)-200);
		this.setLocationRelativeTo(null);
		setOpacity();
		WindowMove();
		this.setModal(true);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == close) {
			
			dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == type) {			
			updatetable();
		}
		if (e.getSource() == startnum) {			
			updatetable();
		}
		if (e.getSource() == endnum) {			
			updatetable();
		}
	}
	// 更新表格函数
	private void updatetable() {		
		check();
		String getstartnum = startnum.getSelectedItem().toString(); 
		String getendnum = endnum.getSelectedItem().toString();
		
		lsm = new goodsLocal();   
		lsm.updateOverageTable (getstartnum,getendnum, 0, 30);	 
		lookstcoktable.setModel(lsm);
		Tools.setTableStyle(lookstcoktable);
	} 
	// 确保起始的数量要小于结束的数量
	private void check() {		
		if (Integer.valueOf(startnum.getSelectedItem().toString()) > Integer.valueOf(startnum.getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(this, "注意，开始的数量要小于结束的数量！");
			return;
		}
	}
	
}