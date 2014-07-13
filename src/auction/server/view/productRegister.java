/*
 * name: 			管理员添加产品入库界面
 * description：		实现管理员添加产品入库界面布局及逻辑处理
 * author:			李海
 */
 
package auction.server.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
 

 

import auction.bean.core.extraTools;
import auction.bean.local.goodsLocal;

import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class productRegister extends JDialog implements ActionListener, MouseListener {
	
	//全局的位置变量，用于表示鼠标在窗口上的位置
	static Point origin = new Point();
	
	// 定义组件
	JButton close;
	JPanel showinput, all;
	JLabel title,goodsId,goodsName,goodsNum, addDate, operator;
	JTextField titlet,goodsIdt, goodsNumt, addDatet, operatort;

	JButton confirm, cancel;	

	goodsLocal goodsItem; 
	
	public static String ptype;
	
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
	private void setlab(JLabel jlb) {		
		showinput.add(jlb);
		jlb.setFont(MyFont.Infolab);
	}
	private void setjtf(final JTextField jtf) {		
		showinput.add(jtf);
		jtf.setOpaque(false);
		jtf.setFont(MyFont.PaddInfotext);
	}
	// 初始化放置所有信息的面板
	public void initall() {	
		goodsItem=new goodsLocal();
		// 设置窗体的样式为当前系统的样式
		try {			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		all = new JPanel(null);
		all.setBackground(Color.white);
		all.setBounds(0, 0, 560, 498);
		all.setBorder(new MatteBorder(3, 3, 3, 3, new Color(60, 148, 212)));		
	}
	// 初始化关闭按钮
	public void initColse() {			
		// 关闭按钮
		close = new JButton(new ImageIcon("image/JDialogClose.png"));
		close.setRolloverIcon(new ImageIcon("image/JDialogCloseC.png"));
		close.setBounds(525, 13, 22, 22);
		close.setForeground(Color.red);
		setbutton(close, 1);
	}
	// 显示输入信息面板初始化
	private void initShowinput() {		
		showinput = new JPanel(new GridLayout(5, 2, -100, 10));
		showinput.setBounds(50, 90, 400, 200);	 
		 
		title=new JLabel("             产品入库");  
		setlab(title);  
		titlet = new JTextField();
		setjtf(titlet);
		titlet.setVisible(false);
		
		goodsId = new JLabel("  产品编号");
		setlab(goodsId);
		goodsIdt = new JTextField();
		setjtf(goodsIdt);
		
		goodsNum = new JLabel("  产品数量");
		setlab(goodsNum);
		goodsNumt = new JTextField();
		setjtf(goodsNumt);
		
		addDate = new JLabel("  入库日期");
		setlab(addDate);
		addDatet = new JTextField();
		setjtf(addDatet);
		addDatet.setText(Tools.getlocaldatetime());
		addDatet.setEditable(false);
		addDatet.setForeground(Color.GRAY);
		
		operator = new JLabel("  备    注");
		setlab(operator);
		operatort = new JTextField("无");
		setjtf(operatort);
		
		showinput.setOpaque(false);		
		all.add(showinput);
	}

	public void initWindowsStyle() {		
		confirm = new JButton("确 定");
		confirm.setBounds(100, 350, 110, 50);
		setbutton(confirm, 2);
		cancel = new JButton("退 出");
		cancel.setBounds(300, 350, 110, 50);
		setbutton(cancel, 2);
		
		all.add(confirm);
		all.add(cancel);
		
		this.add(close);
		this.add(all);
		
		this.setUndecorated(true);
		this.setLayout(null);
		this.setSize(560, 498);
		this.setLocationRelativeTo(null);
		WindowMove();
		setOpacity();
		this.setModal(true);
		this.setVisible(true);
	}
	// 构造函数1
	public productRegister() {		
		initall();
		initColse();
		initShowinput();
		initWindowsStyle();
	}
	
	// 构造函数2
	public productRegister(goodsLocal pm, int selrow) {
		
		initall();
		initColse();
		initShowinput();
		// 设置产品编号
		goodsIdt.setText(pm.getValueAt(selrow, 0).toString());
		initWindowsStyle();
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
	                //设置窗口的位置
	                //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
	                setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
	        }
	     });
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == close) {			
			dispose();
		}
		if (e.getSource() == confirm) {
 
			String getGoodsId = goodsIdt.getText().trim();
			String getGoodsNum = goodsNumt.getText().trim();
			String getAddDate = addDatet.getText();
			String getOperator = operatort.getText().trim();
			
			// 1.确定所有信息都不为空
			if (getGoodsId.equals("") || getGoodsNum.equals("") || getAddDate.equals("") || getOperator.equals("")) {
				JOptionPane.showMessageDialog(this, "信息不能为空，请输入对应的信息");
				return;
			}			
			// 2.确保数量是合法的
			if (!extraTools.isNum(getGoodsNum) || Double.valueOf(getGoodsNum) <= 0.0) {				
				JOptionPane.showMessageDialog(this, "<html><font style = 'font-size:16'>输入的数量非法　<br/><br/>是否存在下列问题：<br/>1.是否大于0?<br/>2.是否是合法的数字?<br/><br/>", " 温馨提示", JOptionPane.WARNING_MESSAGE);
				return;
			}			
			// 3.判断是否存在该产品
			List<HashMap<String, String>> item=null;
			item=goodsItem.query(getGoodsId);
			System.out.println(item.size());
			if(item.size()==0)
			{
				int i = JOptionPane.showConfirmDialog(this, "<html><br/><font style= 'font-size:18'>没有产品编号为:<font color = 'red'>"+getGoodsId+"</font>　的产品信息　<br/>是否需要添加?</font><br/><br/>", " 温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (i == 0) {	
					this.dispose();				
					new addProductPanel(this.getX()+20, this.getY()+50);
				}else {					
					return;
				}
			}else {
				String descriptionOld=item.get(0).get("description");
				String goodsNameOld=item.get(0).get("goodsName");
				int stockNumOld=Integer.parseInt(item.get(0).get("stockNum"));
				int stockNumNew=stockNumOld+Integer.parseInt(getGoodsNum);
				String stockPriceOld=item.get(0).get("stockPrice");
				int result=0;
				result=goodsItem.update(getGoodsId, goodsNameOld, descriptionOld, Integer.toString(stockNumNew),stockPriceOld);
				if (result<1) {					
					JOptionPane.showMessageDialog(this, "抱歉的通知您，入库没有成功");
					return;
				}
				else {					
					// 更新产品库存表中的库存数量
					// 1.检查库存表中是否有该产品 
					this.dispose();
					int i = JOptionPane.showConfirmDialog(this, "<html><font style = 'font-size:15'>入库成功，是否继续添加?<br/>", " 温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (i == 0) {
						new productRegister();
					}
				}		
			}
		}
		if (e.getSource() == cancel) {			
			dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
					AWTUtilities.setWindowOpacity(productRegister.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(productRegister.this, 1);
					Timer source = (Timer) e.getSource();
					source.stop();
				}
			}
		};
		// 设置线程控制
		new Timer(50, lisener).start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

