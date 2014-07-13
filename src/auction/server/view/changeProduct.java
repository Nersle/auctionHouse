/*
 * name: 			管理员修改产品信息界面
 * description：		实现管理员修改产品信息界面布局及逻辑处理
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class changeProduct extends JDialog implements MouseListener, ActionListener {
	
	//全局的位置变量，用于表示鼠标在窗口上的位置
	static Point origin = new Point();
	
	// 定义组件
	JButton close;
	JPanel showinput, all;
	JLabel  goodsId,goodsName,description,stockNum, stockPrice;
	JTextField goodsIdt,goodsNamet, descriptiont,stockNumt, stockPricet;
  	JComboBox<String> typet;
	JButton confirm, cancel;
	
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
	// 显示输入信息面板初始化
	private void initShowinput(goodsLocal goodsItem, int selrow) {		
		showinput = new JPanel(new GridLayout(5, 2, -180, 10));
		showinput.setBounds(50, 40, 400, 250); 
	
		goodsId = new JLabel("产品编号");
		setlab(goodsId);
		goodsIdt = new JTextField();
		goodsIdt.setEditable(false);
		goodsIdt.setText(goodsItem.getValueAt(selrow, 0).toString());
		setjtf(goodsIdt);
		goodsIdt.setForeground(Color.GRAY);
		
		goodsName = new JLabel("产品名称");
		setlab(goodsName);
		goodsNamet = new JTextField();
		goodsNamet.setText(goodsItem.getValueAt(selrow, 1).toString());
		setjtf(goodsNamet);
		
		description = new JLabel("产品描述");
		setlab(description);
		descriptiont = new JTextField();
		descriptiont.setText(goodsItem.getValueAt(selrow, 5).toString());
		setjtf(descriptiont);
		
		stockNum = new JLabel("进货数量");
		setlab(stockNum);
		stockNumt = new JTextField();
		stockNumt.setText(goodsItem.getValueAt(selrow, 3).toString());
		setjtf(stockNumt);
		
		stockPrice = new JLabel("进货价格");
		setlab(stockPrice);
		stockPricet = new JTextField();
		stockPricet.setText(goodsItem.getValueAt(selrow, 2).toString());
		setjtf(stockPricet);
		
		showinput.setOpaque(false); 
		all.add(showinput);
	}
	// 构造函数
	public changeProduct(goodsLocal goodsItem, int selrow) {
		
		// 设置窗体的样式为当前系统的样式
		try {			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 关闭按钮
		close = new JButton(new ImageIcon("image/JDialogClose.png"));
		close.setRolloverIcon(new ImageIcon("image/JDialogCloseC.png"));
		close.setBounds(525, 13, 22, 22);
		close.setForeground(Color.red);
		setbutton(close, 1);
		
		confirm = new JButton("确 定");
		confirm.setBounds(100, 350, 110, 50);
		setbutton(confirm, 2);
		cancel = new JButton("取 消");
		cancel.setBounds(300, 350, 110, 50);
		setbutton(cancel, 2);
		
		this.add(close);		
		
		all = new JPanel(null);
		all.setBackground(Color.white);
		all.setBounds(0, 0, 560, 498);
		all.setBorder(new MatteBorder(2, 2, 2, 2, Color.GRAY));
		initShowinput(goodsItem, selrow);
		all.add(confirm);
		all.add(cancel);
		
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
			String getGoodsId=goodsIdt.getText().trim();
			String getGoodsName = goodsNamet.getText().trim();
			String getDescription = descriptiont.getText().trim();
			String getStockNum = stockNumt.getText().trim();
			String getStockPrice = stockPricet.getText().trim();
			
			if (getGoodsId.equals("") || getGoodsName.equals("") || getDescription.equals("") || getStockNum.equals("") || getStockPrice.equals("")) {
				JOptionPane.showMessageDialog(this, "信息不能为空，请输入对应的信息");
				return;
			}
			if (!(extraTools.isNum(getStockPrice) || extraTools.isNum(getStockNum))) {				
				JOptionPane.showMessageDialog(this, "<html><font style = 'font-size:18'>产品价格或者产品数量非法，请检查　<br/><br/>是否存在下列问题：<br/>1.是否大于0?<br/>2.是否是合法的数字?<br/><br/>", " 温馨提示", JOptionPane.WARNING_MESSAGE);
				return;
			} 
			goodsLocal goodsItem=new goodsLocal();
			int result = goodsItem.update(getGoodsId,getGoodsName,getDescription,getStockNum,getStockPrice);
			
			if (result<0) {				
				JOptionPane.showMessageDialog(this, "抱歉的通知您，修改失败");
				return;
			}			
			JOptionPane.showMessageDialog(this, "修改完成");
			this.dispose();
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
					AWTUtilities.setWindowOpacity(changeProduct.this, alpha+=0.1);
				}
				else {
					AWTUtilities.setWindowOpacity(changeProduct.this, 1);
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
	}

}



