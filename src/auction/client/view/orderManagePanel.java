/*
 * name: 			客户端订单查询界面
 * description：		实现客户端订单查询界面布局及逻辑处理
 * author:			李海
 */

package auction.client.view; 

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.border.MatteBorder; 
import auction.bean.local.ordersLocal; 

@SuppressWarnings("serial")
public class orderManagePanel extends JPanel implements ActionListener, MouseListener, FocusListener {
	
	 // 用于获得窗口的大小
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	// 公用颜色值
	Color color = new Color(22, 120, 195);
	// 显示信息的面板
	JPanel showtabel, showinfoall, showinfo, handle;
	JPanel jadd;
	JButton addc;
	// 装载信息面板的面板
	JPanel showjp;
	JTable ordersTable = null;
	JButton add, modify, delete;
	
	// 右侧面板组件
	JLabel orderId,goodsName,userName,barginPrice,bargainCount,phone,address,createTime;
	
	JTextField orderIdt,goodsNamet,userNamet,barginPricet,bargainCountt,phonet,addresst,createTimet;
	JRadioButton boy, gril;
	
	//定义一个鼠标指针的类型
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//手型鼠标	
	JScrollPane jsp;	
	ordersLocal orderItem = new ordersLocal();
	ordersLocal orderNew = new ordersLocal(); 	
	public void setbutton(JButton jb) {		
		jb.setContentAreaFilled(false);
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setCursor(myCursor);
		jb.setOpaque(false);
	}
	private void setlab(JLabel jlb) { 
		showinfo.add(jlb);
		jlb.setFont(MyFont.Infolab);
		jlb.setForeground(Color.white);
	}
	private void setjtf(final JTextField jtf) {		
		showinfo.add(jtf);
		MatteBorder ubderline = new MatteBorder(0, 0, 1, 0, Color.white);
		jtf.setBorder(ubderline);
		jtf.setOpaque(false);
		jtf.setFont(MyFont.Infotext);
		jtf.setForeground(Color.white);
	}
	public orderManagePanel() {		
		// 处理左侧
		//1.设计jtable
		orderItem = new ordersLocal();
		orderItem.showIntable("", 0, 30);
		//orderItem.query("select Mid, MName, Sex, Age, WMid from MemberInfo where 1=?", paras);
		ordersTable = new JTable(orderItem);		
		// 调用工具Tools类中的设置表格样式方法
		Tools.setTableStyle(ordersTable);
		ordersTable.addMouseListener(this);		
		// 滚动面板
		jsp=new JScrollPane(ordersTable);
		jsp.setBorder(new MatteBorder(0, 1, 1, 0, color));
		Tools.setJspStyle(jsp);		
		showtabel = new JPanel(new BorderLayout());
		showtabel.setBackground(Color.white);
		// 设置只有左边框
		MatteBorder border = new MatteBorder(0, 1, 1, 0, new Color(22, 120, 195));
		showtabel.setBorder(border);
		// 设置面板的大小
		showtabel.setPreferredSize(new Dimension((int)(width*0.8)-250, (int)(height*0.8)-155));
			
		showtabel.add(jsp);		
		handle = new JPanel(new GridLayout(1, 3, ((int)(width*0.8)-625)/6, 10));
		handle.setPreferredSize(new Dimension((int)(width*0.8)-250, 91));
		// 设置只有右边框
		MatteBorder border2 = new MatteBorder(0, 0, 0, 1, new Color(22, 120, 195));
		handle.setBorder(border2);
		handle.setOpaque(false);
		add = new JButton(new ImageIcon("image/add.png"));
		add.setToolTipText("添加订单");
		setbutton(add);
		modify = new JButton(new ImageIcon("image/modify.png"));
		modify.setToolTipText("修改订单");
		setbutton(modify);
		delete = new JButton(new ImageIcon("image/del.png"));
		delete.setToolTipText("删除订单");
		setbutton(delete);
		
		handle.add(add);
		handle.add(modify);
		handle.add(delete);
		
		showjp = new JPanel(new BorderLayout());
		showjp.setOpaque(false);
		showjp.add(showtabel, "Center");
		//showjp.add(handle, "South");
				
		// 处理右侧
		showinfo = new JPanel(new GridLayout(9, 2, -90, 30));
		showinfo.setPreferredSize(new Dimension(350, (int)(height*0.8)));
		showinfo.setOpaque(false); 
		 	
		// 第一列
		orderId = new JLabel(" 订单编号");
		setlab(orderId);
		orderIdt = new JTextField(10);
		//idt.addFocusListener(this); 
		setjtf(orderIdt);  
 
		goodsName = new JLabel(" 商品名称");
		setlab(goodsName);
		goodsNamet = new JTextField(10);
		setjtf(goodsNamet);		
		
		userName = new JLabel(" 购买用户");
		setlab(userName);
		userNamet = new JTextField(10);
		setjtf(userNamet);		 
	
		barginPrice = new JLabel(" 购买价格");
		setlab(barginPrice);
		barginPricet = new JTextField(10);
		setjtf(barginPricet);	
		
		bargainCount = new JLabel(" 购买数量");
		setlab(bargainCount);
		bargainCountt = new JTextField(10);
		setjtf(bargainCountt);	
		
		phone = new JLabel(" 联系电话");
		setlab(phone);
		phonet =new JTextField(10);
		setjtf(phonet);
		
		address = new JLabel(" 联系地址");
		setlab(address);
		addresst = new JTextField(10);
		setjtf(addresst);
		addresst.setFont(new Font("新宋体",Font.PLAIN,13));
 
		
		createTime = new JLabel(" 购买日期");
		setlab(createTime);
		createTimet = new JTextField(10);
		setjtf(createTimet); 
		
		jadd = new JPanel();
		jadd.setPreferredSize(new Dimension(350, 85));
		jadd.setOpaque(false);
		
		addc = new JButton(new ImageIcon("image/addconfirm.png"));
		addc.setVisible(false);
		setbutton(addc);
		
		jadd.add(addc);
		
		showinfoall = new JPanel(new BorderLayout());
		showinfoall.setOpaque(false);
		showinfoall.setPreferredSize(new Dimension(350, (int)(height*0.8)));
		
		showinfoall.add(showinfo, "Center");
		showinfoall.add(jadd, "South");
		
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.add(showjp, "Center");
	//	this.add(showinfoall, "East");
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ordersTable) {				
			showmes();
			addc.setVisible(false);
			delete.setEnabled(true);
			modify.setEnabled(true);
			orderIdt.removeFocusListener(this);
		}
		// 添加按钮
		if (e.getSource() == add) {			
			// 1.清空所有的信息
			orderIdt.setText(""); 
			orderIdt.setVisible(false);
			orderId.setVisible(false);
			orderIdt.setForeground(Color.WHITE); 
			goodsNamet.setText(""); 
			userNamet.setText("");
			phonet.setText("");
			addresst.setText("");
			barginPricet.setText("");
			bargainCountt.setText(""); 
			createTimet.setText(Tools.getlocaldatetime());
			createTime.setVisible(false);
			createTimet.setVisible(false); 
			
			addc.setVisible(true);
			delete.setEnabled(false);
			modify.setEnabled(false);
			
			//idt.addFocusListener(this);
		}
		if (e.getSource() == addc) {			
			// 1.得到信息
			//String getId = idt.getText(); 
			String getGoodsName= goodsNamet.getText(); 			
			String getUserName = userNamet.getText(); 
			String getPhone = phonet.getText();
			String getAddress = addresst.getText();  
			String getBarginPrice = barginPricet.getText();  
			String getBargainCount = bargainCountt.getText();  
			
			// 2.判断信息是否为空
			if (getGoodsName.equals("")||getUserName.equals("")
					||getPhone.equals("")||getAddress.equals("")) {				
				JOptionPane.showMessageDialog(this, "<html><font color = 'red'>不能为空，请输入相应的信息！");
				return;
			}			
			// 3.添加操作
			int result = orderNew.add(getGoodsName,getUserName,getBarginPrice,getBargainCount,getPhone,getAddress); 
			if (result>0) { 
				JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'blue'>添加成功");
			}else {				
				JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>抱歉的通知您，订单添加失败!</font>" +
						"<br />请检查信息是否符合要求！<br />" +
						"常见问题：<br />　　①货物Id无效<br />　　②用户不存在");
			}
		 
			orderId.setVisible(true);
			orderIdt.setVisible(true);
			createTime.setVisible(true);
			createTimet.setVisible(true);
			orderItem = new ordersLocal();
			orderItem.showIntable("", 0, 30);
		 	ordersTable.setModel(orderItem);


		}
		if (e.getSource() == modify) {			
			if (modify.isEnabled()) {
				int selrow=ordersTable.getSelectedRow();
				int i = ordersTable.getSelectedRowCount();
				while(i > 1)
				{
					JOptionPane.showMessageDialog(this, "只能操作一行数据，请选中一行操作");
					return;
				}
				if(selrow == -1)
				{
					JOptionPane.showMessageDialog(this, "请选择一行，再进行操作");
					return;
				}
				
				String getOrderId=orderIdt.getText();
				String getGoodsName= goodsNamet.getText(); 			
				String getUserName = userNamet.getText(); 
				String getPhone = phonet.getText();
				String getAddress = addresst.getText();  
				String getBarginPrice = barginPricet.getText();  
				String getBargainCount = bargainCountt.getText();  
				
				if (mesconfirm()) {
							JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'red'>亲！信息没有修改过！</font><br /><font size = '5' color = 'blue'>温馨提示:<br />您可以对右侧相应的信息修改之后再保存修改");
					return;
				}
				if (getGoodsName.equals("")||getUserName.equals("")
						||getPhone.equals("")||getAddress.equals("")) {				
					JOptionPane.showMessageDialog(this, "<html><font color = 'red'>不能为空，请输入相应的信息！");
					return;
				}
				int confirm = JOptionPane.showConfirmDialog(this, "确定要修改吗？", "温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (confirm == 1) {					
					return;
				}else {					
				 	int result = orderNew.update(getOrderId,getGoodsName,getUserName,getBarginPrice,getBargainCount,getPhone,getAddress); 
					if (result>0) {						
						JOptionPane.showMessageDialog(this, "<html><font size = '5'>　修改成功");
					}else {						
						JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>抱歉的通知您，订单修改失败!</font>" +
								"<br />请检查信息是否符合要求！<br />" +
								"常见问题：<br />　　①货物Id无效<br />　　②用户不存在");
					}
					orderItem = new ordersLocal();
					orderItem.showIntable("", 0, 30);
					ordersTable.setModel(orderItem);
				}
			}
		}
		if (e.getSource() == delete) {			
			if (delete.isEnabled()) {				
				int selrow=ordersTable.getSelectedRow();
				int i = ordersTable.getSelectedRowCount();
				while(i > 1)
				{
					JOptionPane.showMessageDialog(this, "只能操作一行数据，请选中一行操作");
					return;
				}
				if(selrow == -1)
				{
					JOptionPane.showMessageDialog(this, "请选择一行，再进行操作");
					return;
				}
				int confirm= JOptionPane.showConfirmDialog(this, "<html><font size = '5'>是否要删除选中员工信息？<br /><br /><font size = '5' color = 'red'>请注意慎重操作<br /><br />", "温馨提示", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (confirm == 0) {
					
					String  userId= orderItem.getValueAt(selrow, 0).toString() ;
					System.out.println(userId.toString());
					int result =orderNew.deleteById(userId); // orderNew.Memberupdate(sql, eid);
					if (result>0) { 
						JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'blue'>恭喜您，删除成功啦");
						orderItem = new ordersLocal();
						orderItem.showIntable("", 0, 30);
						ordersTable.setModel(orderItem);
					}else { 
						JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>抱歉的通知您，删除没有成功!<br />请检查原因！");
					}
				}else {
					
					return;
				}
			}
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
		if (e.getSource() == add) {			
			add.setIcon(new ImageIcon("image/addC.png"));
		}
		if (e.getSource() == addc) {			
			addc.setIcon(new ImageIcon("image/addconfirmC.png"));
		}
		if (e.getSource() == modify) {			
			modify.setIcon(new ImageIcon("image/modifyC.png"));
		}
		if (e.getSource() == delete) {			
			delete.setIcon(new ImageIcon("image/delC.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == add) {			
			add.setIcon(new ImageIcon("image/add.png"));
		}
		if (e.getSource() == addc) {			
			addc.setIcon(new ImageIcon("image/addconfirm.png"));
		}
		if (e.getSource() == modify) {			
			modify.setIcon(new ImageIcon("image/modify.png"));
		}
		if (e.getSource() == delete) {			
			delete.setIcon(new ImageIcon("image/del.png"));
		}
	}
	
	//更新前判断是否修改
	private boolean mesconfirm() {		
		boolean b = false; 
		if (goodsNamet.getText().equals((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 1)) 
				&& userNamet.getText().equals((String)orderNew.getValueAt(ordersTable.getSelectedRow(),5))
				&& phonet.getText().equals((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 6))
				&& addresst.getText().equals((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 4))
				&& bargainCountt.getText().equals((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 4))
				&& barginPricet.getText().equals((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 2))
			) 
		{
			b = true;
		}
		
		return b;
	}
	
	// 信息显示函数
	private void showmes() {	
		orderNew.showIntable("", 0, 30); 
		orderIdt.setText((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 0));
		orderIdt.setEditable(false);  
		orderIdt.setForeground(Color.lightGray);
		goodsNamet.setText((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 1)); 
		userNamet.setText((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 2));
		barginPricet.setText((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 3));
		bargainCountt.setText((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 4)); 
		phonet.setText((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 7));
		addresst.setText((String)orderNew.getValueAt(ordersTable.getSelectedRow(), 5));
		String fdate = (String)orderNew.getValueAt(ordersTable.getSelectedRow(), 6);
		createTimet.setText(fdate.substring(0, 19));
		  
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) { 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
