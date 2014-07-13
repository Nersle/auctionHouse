/*
 * name: 			客户端商城首页界面
 * description：		实现客户端商城首页界面布局及逻辑处理
 * author:			李海
 */


package auction.client.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.border.MatteBorder; 

import auction.bean.local.goodsLocal;
 

@SuppressWarnings("serial")
public class homePanel extends JPanel implements ActionListener, MouseListener, FocusListener {
    // 用于获得窗口的大小
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	// 公用颜色值
	Color color = new Color(22, 120, 195);
	// 显示信息的面板
	JPanel  showtabel, showinfo, handle;
	// 添加产品信息的面板控件   
	clientPanel mainPanel;
	// 装载信息面板的面板
	JPanel showjp;
	JTable producttable = null;  
	//定义一个鼠标指针的类型
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//手型鼠标
	
	JScrollPane jsp;
	
	goodsLocal goodsItem = new goodsLocal();
	
	public void setbutton(JButton jb, int type) {		
		if (type == 1) {			
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			
		} 
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setCursor(myCursor);
		jb.setOpaque(false);
	}
	public homePanel(clientPanel clientPanelNew) {		
		
		mainPanel= clientPanelNew;  
		showtabel = new JPanel();
		showtabel.setBackground(Color.white);
		// 设置面板的大小
		showtabel.setPreferredSize(new Dimension((int)(width*0.8)-250, (int)(height*0.8)-155));
		// 设计表格
		goodsItem.showHomeTable("", 0, 30);
		producttable = new JTable(goodsItem);
		
		// 调用工具Tools类中的设置表格样式方法
		Tools.setTableStyle(producttable);
		producttable.addMouseListener(this);
		
		// 滚动面板
		jsp=new JScrollPane(producttable);
		jsp.setBorder(new MatteBorder(1, 1, 1, 1, color));
		Tools.setJspStyle(jsp);
		
		showtabel = new JPanel(new BorderLayout());
		showtabel.setBackground(Color.white);
		// 设置面板的大小
		showtabel.setPreferredSize(new Dimension((int)(width*0.8)-250, (int)(height*0.8)-155));
		showtabel.add(jsp, "Center"); 
		
		showjp = new JPanel(new BorderLayout());
		showjp.setOpaque(false);
		// 用于设置面板的边框
		showjp.setBorder(new MatteBorder(0, 1, 0, 1, color)); 
		showjp.add(showtabel, "Center"); 
		
		// 设置窗体的样式为当前系统的样式
		try {			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.add(showjp, "Center"); 
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {  
		// 双击表格修改
		if (e.getSource() == producttable) {			
			if (e.getClickCount() == 2) {				
				int selrow=producttable.getSelectedRow();	 
				mainPanel.setTopMenuStyle(mainPanel.bidManage, "click");
				bidGoods bidManagePanel = new bidGoods(goodsItem,selrow);	
				mainPanel.conjp.add(bidManagePanel, "bidManage");
				mainPanel.card.show(mainPanel.conjp, "bidManage");
			 	 
			}
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
	
	// 更新表格模型函数，当表格需要更新是调用
	public void updatetable() {		
		goodsItem = new goodsLocal();
		goodsItem.showIntable("", 0, 30);
		producttable.setModel(goodsItem);
		Tools.setTableStyle(producttable);
	}
	
	@Override
	public void focusGained(FocusEvent e) { 
		 
	}
	@Override
	public void focusLost(FocusEvent e) {  
	}
	@Override
	public void actionPerformed(ActionEvent e) { 
	}
}
