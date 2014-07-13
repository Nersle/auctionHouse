/*
 * name: 			客户端系统管理界面
 * description：		实现客户端系统管理界面布局及逻辑处理
 * author:			李海
 */

package auction.client.view; 

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List; 
import javax.swing.*;
import javax.swing.border.MatteBorder;  
import auction.bean.local.usersLocal;
 

@SuppressWarnings("serial")
public class systemManagePanel extends JPanel implements ActionListener, MouseListener, FocusListener {

    // 用于获得窗口的大小
    final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
    final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
    // 公用颜色值
    Color color = new Color(22, 120, 195);
    // 显示信息的面板
    JPanel showChangePWD, showInfo,showMenu,jButtonPanel;
    JButton changePwdSubmit,changePwdClear;
    JPanel jadd,multiCard;
    JButton addc;
    // 装载信息面板的面板
    JPanel showInfoBase,showChangePwdBase; 
    JButton add, modify, delete;
    JRadioButton boy, girl;

	JButton nullButton,changePwd, changeInfo, logout;
	
    JLabel id, name, sex, age, phone, address, times, email; 
    JTextField idt, namet, aget, phonet, addresst, timest, emailt;
    JButton modifySubmit,modifyClear;
    
    JLabel oldPwd,newPwd,confirmPwd;
    JPasswordField oldPwdt,newPwdt,confirmPwdt;
    
    CardLayout card;
    usersLocal my;
    HashMap<String, String> myInfo;
    //定义一个鼠标指针的类型
    Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//手型鼠标	
 
    private void setlab(JLabel jLabelItem,JPanel position) { 
    	position.add(jLabelItem);
        jLabelItem.setFont(MyFont.button);
        jLabelItem.setForeground(Color.BLACK);
    }
    private void setjtf(final JTextField jLabelItem,JPanel position) {		
    	position.add(jLabelItem);
        MatteBorder ubderline = new MatteBorder(0, 0, 1, 0, Color.yellow);
        jLabelItem.setBorder(ubderline);
        jLabelItem.setOpaque(false);
        jLabelItem.setFont(MyFont.Infotext);
        jLabelItem.setForeground(Color.BLUE);
    }
    private void setjtfPwd(final JTextField jLabelItem,JPanel position) {		
    	position.add(jLabelItem);
        MatteBorder ubderline = new MatteBorder(0, 0, 1, 0, Color.yellow);
        jLabelItem.setBorder(ubderline);
        jLabelItem.setOpaque(false);
        jLabelItem.setFont(MyFont.Infotext);
        jLabelItem.setForeground(Color.white);
    }
    
	public void setbutton(JButton jb, int type) {		
		if (type == 1) {	 
//	        jb.setContentAreaFilled(false);
//	        jb.setBorderPainted(false); 
		}
		if (type == 2) {			
			jButtonPanel.add(jb);
		}
		jb.setFocusPainted(false);
		jb.addMouseListener(this);
		jb.setCursor(myCursor);
		jb.setOpaque(false);
		jb.setForeground(Color.blue);
		jb.setBorderPainted(false);
		jb.setFocusPainted(false);   
		jb.setFont(MyFont.button);
	}
	
    public systemManagePanel() {
    	
		this.card = new CardLayout(); 
		
        showInfo = new JPanel(new GridLayout(8, 2, -80,20));
        showInfo.setBackground(Color.white); 
        MatteBorder border = new MatteBorder(0, 1, 0, 0, new Color(22, 120, 195));
        showInfo.setBorder(border); 
        showInfo.setPreferredSize(new Dimension((int)(width*0.8)-300, (int)(height*0.8)-300));
   
        my=new usersLocal();
        List<HashMap<String, String>> result=my.query("merry"); 
        myInfo=result.get(0);
        
        // 第一列
        id = new JLabel(" 编    号",JLabel.CENTER);
        setlab(id,showInfo);
        idt = new JTextField(10); 
        idt.setText(myInfo.get("id"));
        setjtf(idt,showInfo); 

        name = new JLabel(" 姓    名",JLabel.CENTER);
        setlab(name,showInfo);
        namet = new JTextField(10);
        namet.setText(myInfo.get("name"));
        setjtf(namet,showInfo);

        sex = new JLabel(" 性    别",JLabel.CENTER);
        setlab(sex,showInfo);
        boy =new JRadioButton("男");
        boy.setOpaque(false);
        boy.setFocusPainted(false);
        boy.setBorderPainted(false);

        girl = new JRadioButton("女");
        girl.setOpaque(false);
        girl.setFocusPainted(false);
        girl.setBorderPainted(false);
        ButtonGroup sext = new ButtonGroup();
        sext.add(boy);
        sext.add(girl);
        JPanel sextp = new JPanel(new GridLayout(1, 2));
        sextp.setOpaque(false);
        sextp.add(boy);
        sextp.add(girl);
        showInfo.add(sextp);
        
        if(myInfo.get("gender").equals(boy.getText()))
        	boy.setSelected(true);
        else
        	girl.setSelected(true); 

        age = new JLabel(" 年    龄",JLabel.CENTER);
        setlab(age,showInfo);
        aget = new JTextField(10);
        aget.setText(myInfo.get("age"));
        setjtf(aget,showInfo);		
 
        email = new JLabel(" 邮   箱",JLabel.CENTER);
        setlab(email,showInfo);
        emailt = new JTextField(10);
        emailt.setText(myInfo.get("email"));
        setjtf(emailt,showInfo);
        
        phone = new JLabel(" 联系电话",JLabel.CENTER);
        setlab(phone,showInfo);
        phonet =new JTextField(10);
        setjtf(phonet,showInfo);
        phonet.setText(myInfo.get("phone"));

        address = new JLabel(" 联系地址",JLabel.CENTER);
        setlab(address,showInfo);
        addresst = new JTextField(10);
        setjtf(addresst,showInfo);
        addresst.setFont(new Font("新宋体",Font.PLAIN,15));
        addresst.setText(myInfo.get("address"));
         
        times = new JLabel(" 加入日期",JLabel.CENTER);
        setlab(times,showInfo);
        timest = new JTextField(10);
        setjtf(timest,showInfo);
        timest.setText(myInfo.get("createTime")); 
 
		JPanel nulljp = new JPanel(new GridLayout(3, 1, 0,10));
		JLabel none=new JLabel("",JLabel.CENTER);
		JLabel none2=new JLabel("",JLabel.CENTER);
		JLabel title=new JLabel("个人信息",JLabel.CENTER);
		title.setFont(MyFont.Infolab); 
        MatteBorder borderNull = new MatteBorder(1, 1, 0, 0, new Color(22, 120, 195));
        nulljp.setBorder(borderNull); 
        nulljp.add(none); 
        nulljp.add(title);
        nulljp.add(none2);
		nulljp.setPreferredSize(new Dimension(new Dimension((int)(width*0.8)-300, 90)));
		nulljp.setBackground(Color.WHITE);  
		 
		JPanel nulljp4 = new JPanel();
		nulljp4.setPreferredSize(new Dimension(new Dimension((int)(width*0.8)-300,60)));
		nulljp4.setBackground(Color.white); 
		MatteBorder borderNull2 = new MatteBorder(0, 1, 1, 0, new Color(22, 120, 195));
        nulljp4.setBorder(borderNull2); 
	
        modifySubmit=new JButton("提交修改");
        //modifySubmit.setFont(MyFont.gray);
        modifySubmit.setPreferredSize(new Dimension(new Dimension(150,40)));
		setbutton(modifySubmit,1); 
        nulljp4.add(modifySubmit);  
        modifyClear=new JButton("清      空");
        setbutton(modifyClear,1);
        modifyClear.setFont(MyFont.myInfo); 
        modifyClear.setPreferredSize(new Dimension(new Dimension(150,40)));
		nulljp4.add(modifyClear);  
        
		showInfoBase = new JPanel(new BorderLayout());   
		showInfoBase.setBackground(Color.white); 
        showInfoBase.add(showInfo, "Center");
        showInfoBase.add(nulljp, "North"); 
        showInfoBase.add(nulljp4, "South"); 
        
        showChangePWD = new JPanel(new GridLayout(4, 2, 0, 30));
        //showChangePWD.setOpaque(false);
        showChangePWD.setBackground(new Color(22, 120, 195)); 
        MatteBorder borderPwd = new MatteBorder(1, 1, 1, 1, new Color(22, 120, 195));
        showChangePWD.setBorder(borderPwd); 
        showChangePWD.setPreferredSize(new Dimension((int)(width*0.6-250), (int)(height*0.7-250)));
  
        oldPwd = new JLabel(" 旧 密 码",JLabel.CENTER); 
        setlab(oldPwd,showChangePWD);
        oldPwd.setForeground(color.white);
        oldPwdt = new JPasswordField(5);
        setjtfPwd(oldPwdt,showChangePWD); 
        
        newPwd = new JLabel(" 新 密 码",JLabel.CENTER);
        setlab(newPwd,showChangePWD);
        newPwd.setForeground(color.white);
        newPwdt = new JPasswordField(5);
        setjtfPwd(newPwdt,showChangePWD); 

        confirmPwd = new JLabel(" 确认密码",JLabel.CENTER);
        setlab(confirmPwd,showChangePWD);
        confirmPwd.setForeground(color.white);
        confirmPwdt = new JPasswordField(5);
        setjtfPwd(confirmPwdt,showChangePWD);
         
        changePwdSubmit=new JButton("提    交");
        changePwdSubmit.setFont(MyFont.myInfo);
        setbutton(changePwdSubmit,1);
        showChangePWD.add(changePwdSubmit); 
        
        changePwdClear=new JButton("清    空");  
        changePwdClear.setFont(MyFont.myInfo);
        setbutton(changePwdClear,1);  
        showChangePWD.add(changePwdClear);        
  	  
		JPanel nulljp2 = new JPanel();
		nulljp2.setPreferredSize(new Dimension((int)(width*0.8-250), (int)(height*0.13)));
		nulljp2.setOpaque(false);  
        
        JPanel basePWD = new JPanel();
        basePWD.setPreferredSize(new Dimension((int)(width*0.8-250), (int)(height*0.8-250)));
        basePWD.setOpaque(false);
        basePWD.add(showChangePWD);
        
        showChangePwdBase = new JPanel(new BorderLayout());
 		showChangePwdBase.setOpaque(false); 
 		showChangePwdBase.setBackground(Color.white);
 		showChangePwdBase.setPreferredSize(new Dimension((int)(width*0.8-250), (int)(height*0.8-250)));
        
        showChangePwdBase.add(nulljp2, "North");
 		showChangePwdBase.add(basePWD, "Center"); 
        
    	multiCard = new JPanel(card);
    	  
		multiCard.setOpaque(false);
		multiCard.add(showInfoBase, "showInfoBase"); 
		multiCard.add(showChangePwdBase, "showChangePWD");  
        
        // 处理右侧
        showMenu = new JPanel();
        showMenu.setPreferredSize(new Dimension(350, (int)(height*0.8)));
        showMenu.setOpaque(false);

		jButtonPanel = new JPanel(new GridLayout(3, 1, 10, 50));
		jButtonPanel.setPreferredSize(new Dimension(217, (int)(height*0.8)-354));
		jButtonPanel.setOpaque(false);
        
        changePwd = new JButton(new ImageIcon("./image/changePwd.png"));
		setbutton(changePwd, 2);
		changePwd.setRolloverIcon(new ImageIcon("./image/changePwd.png"));
		changeInfo = new JButton(new ImageIcon("./image/changeInfo.png"));
		setbutton(changeInfo, 2);
		changeInfo.setRolloverIcon(new ImageIcon("./image/changeInfo.png"));
		logout = new JButton(new ImageIcon("./image/logout.png"));
		setbutton(logout, 2);
		logout.setRolloverIcon(new ImageIcon("./image/logout.png"));
		
		JPanel nulljp3 = new JPanel();
		nulljp3.setPreferredSize(new Dimension(350, 100));
		nulljp3.setOpaque(false); 
		
		showMenu.add(nulljp3);
		showMenu.add(jButtonPanel);  

        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(multiCard, "Center");
        this.add(showMenu, "East");
        this.setVisible(true); 
		changeInfoView(false);	
		 
		setBorder(0);
    }
    
    public void changeInfoView(boolean flag)
    { 
    	this.idt.setEditable(flag);
    	this.namet.setEditable(flag);
    	this.aget.setEditable(flag);
    	this.boy.setEnabled(false);;
    	this.girl.setEnabled(false);
    	this.phonet.setEditable(flag);
    	this.addresst.setEditable(flag);
    	this.timest.setEditable(flag);
    	this.emailt.setEditable(flag);
    	this.modifyClear.setVisible(flag);
    	this.modifySubmit.setVisible(flag); 
    }
    
    public void setBorder(int flag)
    {   
    	MatteBorder ubderline;
    	if(flag==1) 
    		ubderline= new MatteBorder(0, 0, 1, 0, Color.yellow);
    	else
    		 ubderline = new MatteBorder(0, 0, 0, 0, Color.yellow);
    	 
    	idt.setBorder(ubderline);
    	namet.setBorder(ubderline);
    	aget.setBorder(ubderline);
    	phonet.setBorder(ubderline);
    	addresst.setBorder(ubderline);
    	timest.setBorder(ubderline);
    	emailt.setBorder(ubderline);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.changeInfo) {
			setBorder(1);
			changeInfoView(true); 
			this.idt.setEditable(false);
			this.timest.setEditable(false);
			this.boy.setEnabled(true);
			this.girl.setEnabled(true);
			this.card.show(multiCard, "showInfoBase");
		}
		if (e.getSource() == this.changePwd) {	
			changeInfoView(false);		 
			setBorder(0);
			this.card.show(multiCard, "showChangePWD");
		}
		if (e.getSource() == this.logout) {	
			changeInfoView(false);		 
			setBorder(0);	 			 
			int i = JOptionPane.showConfirmDialog(this, "是否要退出登陆？", "温馨提示", 
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(i == 0) {
				System.exit(0);
			}else {
				return;
			}
		}
		if (e.getSource() == this.modifyClear) { 
	    	this.namet.setText("");
	    	this.aget.setText("");
	    	this.girl.setSelected(false);
	    	this.boy.setSelected(false);
	    	this.phonet.setText("");
	    	this.addresst.setText("");
	    	this.timest.setText("");
	    	this.emailt.setText(""); 
		} 
		if (e.getSource() == this.changePwdClear) { 
	        oldPwdt.setText("");
	        newPwdt.setText("");
	        confirmPwdt.setText("");
		}
		if (e.getSource() == this.changePwdSubmit) { 
			String getOldPwd= new String(this.oldPwdt .getPassword());
			String getNewPwd=new String(this.newPwdt .getPassword());
			String getConfirmPwd=new String(this.confirmPwdt .getPassword());

			if (getOldPwd.equals("")||getNewPwd.equals("")
					||getConfirmPwd.equals("")) {				
				JOptionPane.showMessageDialog(this, "<html><font color = 'red'>不能为空，请输入相应的信息！");
				return;
			}
			if(!getNewPwd.equals(getConfirmPwd))
			{
				JOptionPane.showMessageDialog(this, "<html><font color = 'red'>两次输入密码不一致！");
				return ;
			}
			if(getOldPwd.equals(getNewPwd))
			{
				JOptionPane.showMessageDialog(this, "<html><font color = 'red'>新密码不能同于旧密码");
				return ;
			}
			
			int result = my.changePwd(myInfo.get("name"),getOldPwd, getNewPwd);
			if (result>0) {						
				JOptionPane.showMessageDialog(this, "<html><font size = '5'>　密码修改成功");
			}else {						
				JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>密码修改失败!</font>");
			}
			return ;
		}
		if (e.getSource() == this.modifySubmit) {
			String getId = idt.getText();
			String getName = namet.getText();
			String getGender =null;
			if (boy.isSelected()) {					
				getGender = boy.getText();
			} else if (girl.isSelected()) {					
				getGender = girl.getText();
			}				
			String getAge = aget.getText(); 
			String getPhone = phonet.getText();
			String getAddress = addresst.getText(); 
			String getEmail = emailt.getText();
			if(getName.equals("")||getGender.equals("")||getAge.equals("")
					||getPhone.equals("")||getAddress.equals("")||getEmail.equals(""))
			{
				JOptionPane.showMessageDialog(this, "<html><font color = 'red'>不能为空，请输入相应的信息！");
				return;					
			}
			int y = JOptionPane.showConfirmDialog(this, "确定要修改吗？", "温馨提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (y == 1) {					
				return;
			}else {					 
				int result = my.update(getId,getName,getGender, getEmail,getAge,getPhone,getAddress); 
			 	if (result>0) {						
					JOptionPane.showMessageDialog(this, "<html><font size = '5'>　修改成功");
				}else {						
					JOptionPane.showMessageDialog(this, "<html><font size = '5' color = red>抱歉的通知您，修改没有成功!</font>" +
							"<br />请检查信息是否符合要求！<br />" +
							"常见问题：<br />　　①年龄超出范围<br />　　②某些字段只能为数字　");
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
