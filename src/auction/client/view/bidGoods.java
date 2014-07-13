/*
 * name: 			客户端拍卖界面
 * description：		实现客户端拍卖界面布局及逻辑处理
 * author:			李海
 */

package auction.client.view; 

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.border.MatteBorder; 
 
import auction.bean.local.goodsLocal;
import auction.bean.local.ordersLocal;
import auction.bean.local.usersLocal; 
import auction.client.view.MyFont;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset; 
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;

@SuppressWarnings("serial")
public class bidGoods extends JPanel implements Runnable,ActionListener, MouseListener, FocusListener {
	
	 // 用于获得窗口的大小
	final static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	Color color = new Color(22, 120, 195);
	JButton close;
	JButton confirm, cancel;
	
	JPanel baseJPanel, topJPanel,centerJPanel, showinfo,bottomJPanel;
 
	JLabel showBidsInfo,showMyPrice,showMaxPrice;
	JTextField bidsPrice;
	JButton bids;
	Double leaveTime;
	boolean timeFlag;
	MeterPlot plot;
	JFreeChart chart ;
	ChartPanel chartpanel;
	
	String goodsName;
	String goodsId;
	Double maxPrice; 
	Double myPrice; 
	String bidsPriceValue;
	
	boolean checkTime=true;

	goodsLocal goodsItem = new goodsLocal ();
	ordersLocal  ordersItem=new ordersLocal ();
	Double initTime=30.0; 	
	
	//定义一个鼠标指针的类型
	Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);//手型鼠标	
 	
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
	public bidGoods()
	{ 
		leaveTime=30.0;
	}
	public bidGoods(goodsLocal  goodsItem, int selrow) {
		leaveTime=initTime;
		// 设置窗体的样式为当前系统的样式
		try {			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		}	 
		
		goodsId= goodsItem.getValueAt(selrow, 0).toString(); 
		goodsName= goodsItem.getValueAt(selrow, 1).toString(); 
		
		myPrice=0.0;
		maxPrice=0.0;
		
		baseJPanel = new JPanel(new BorderLayout());
		baseJPanel.setBackground(Color.white);
		baseJPanel.setBorder(new MatteBorder(0, 0, 0, 0, color));
		 
		
		topJPanel=new JPanel(new BorderLayout());
		topJPanel.setBackground(Color.white);
		JPanel topTopJPanel=new JPanel();
		JPanel topBottomJPanel=new JPanel(); 
		topTopJPanel.setPreferredSize(new Dimension((int)(width*0.1), (int)(height*0.02)));
	  	
		topJPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.white));
		topJPanel.setPreferredSize(new Dimension((int)(width*0.1), (int)(height*0.1)));
		showBidsInfo=new JLabel(goodsName);
		showBidsInfo.setHorizontalAlignment(JLabel.CENTER);
		showBidsInfo.setForeground(Color.black);
		showBidsInfo.setFont(MyFont.Infolab);
 		
		showMyPrice=new JLabel("");
		showMyPrice.setText("  my "+Double.toString(myPrice));
		showMyPrice.setForeground(Color.yellow);
		showMyPrice.setFont(MyFont.Infolab);
		
		showMaxPrice=new JLabel();
		showMaxPrice.setText("  max "+Double.toString(maxPrice));
		showMaxPrice.setForeground(Color.RED);
		showMaxPrice.setFont(MyFont.Infolab);
		
		topBottomJPanel.add(showBidsInfo);
		topBottomJPanel.add(showMyPrice); 
		topBottomJPanel.add(showMaxPrice);
		
		topTopJPanel.setBackground(Color.orange);
		topBottomJPanel.setBackground(Color.orange);
		
		topJPanel.add(topTopJPanel,"North");
		topJPanel.add(topBottomJPanel,"Center");
		
		baseJPanel.add(topJPanel,"North");
		
		centerJPanel=new JPanel();
		//centerJPanel.setBackground(Color.gray);
		//centerJPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
		centerJPanel.setPreferredSize(new Dimension((int)(width*0.4), (int)(height*0.42)));
		 
		DefaultValueDataset data = new DefaultValueDataset(leaveTime);
		plot = new MeterPlot(data);
		plot.setUnits("s");
		
		plot.setDialShape(DialShape.CHORD);
		plot.setDialBackgroundPaint(Color.WHITE);
		plot.setRange(new Range(0, 30));
		plot.setDialOutlinePaint(Color.yellow);
		plot.setNeedlePaint(Color.red);
		plot.setTickLabelsVisible(true);
		plot.setTickLabelPaint(Color.BLACK);
		plot.setTickPaint(Color.green);
		plot.setTickLabelFormat(NumberFormat.getNumberInstance());
		plot.setTickSize(3);
		plot.setValuePaint(Color.BLACK);
		plot.addInterval(new MeterInterval("Low", new Range(0, 5), null, null,new Color(255, 128, 128,90) ));
		plot.addInterval(new MeterInterval("Normal", new Range(5,15), null, null, new Color(255, 255, 128,90)));
		plot.addInterval(new MeterInterval("High", new Range(15, 30), null, null, new Color(128, 255, 128,90)));
	    
		chart = new JFreeChart("剩余时间:30s", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        
        chartpanel = new ChartPanel(chart);  
		chartpanel.setOpaque(false);
		chartpanel.setPreferredSize(new Dimension((int)(width*0.4), (int)(height*0.4)));
		chartpanel.setBackground(Color.white);  
		 
		centerJPanel.add(chartpanel); 
		baseJPanel.add(centerJPanel,"Center"); 
		bottomJPanel=new JPanel();
		bottomJPanel.setBorder(new MatteBorder(0, 1, 1, 1, Color.yellow));
		bottomJPanel.setPreferredSize(new Dimension((int)(width*0.1), (int)(height*0.1)));
		
		bidsPrice=new JTextField(16);
		bidsPrice.setPreferredSize(new Dimension((int)(width*0.08), (int)(height*0.06)));
		bidsPrice.setFont(MyFont.Infolab);	
		bids=new JButton("竞价");
		bids.addMouseListener(this);
		bids.setPreferredSize(new Dimension((int)(width*0.08), (int)(height*0.06)));
		bids.setFont(MyFont.Infolab);	
		bottomJPanel.add(bidsPrice);
		bottomJPanel.add(bids);
		
		baseJPanel.add(bottomJPanel,"South");
		JPanel tmp=new JPanel(); 
		tmp.setPreferredSize(new Dimension((int)(width*0.1), (int)(height*0.1)));
		this.add(baseJPanel); 
		this.setOpaque(false);
		updateData();
	}  
	public int updateTime()
	{
		leaveTime=leaveTime-1;
		if(leaveTime<1&&timeFlag==false)
		{
			timeFlag=true;
			if(myPrice<maxPrice)
			{ 
				JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'blue'>很遗憾，其他用户拍得该物品，请再接再厉！");
			}else
			{
				JOptionPane.showMessageDialog(this, "<html><font size = '5' color = 'red'>恭喜您！成功拍得该物品！");
				goodsItem.sale(goodsId,1,  this.bidsPriceValue );
				//用户的信息
				usersLocal  my=new usersLocal ();
		        List<HashMap<String, String>> result=my.query("merry"); 
		        HashMap<String, String>  myInfo=result.get(0);
				ordersItem.add(goodsName, myInfo.get("name"), this.bidsPriceValue, "1",myInfo.get("phone"),myInfo.get("address"));
			}
			return 0;
		}
		if(timeFlag)
		{
			return 0;
		} 
		DefaultValueDataset data = new DefaultValueDataset(leaveTime);
		plot.setDataset(data); 
		int leaveTimeInt=Integer.parseInt(new java.text.DecimalFormat("0").format(leaveTime)) ;
		
		String leaveTimeStr="剩余时间:"+Integer.toString(leaveTimeInt)+"s";
		
		chart = new JFreeChart(leaveTimeStr, JFreeChart.DEFAULT_TITLE_FONT, plot, false);
		chartpanel.setChart(chart);
		chartpanel.updateUI(); 
		updateData();
		return 1;
	}

	public void updateData()
	{
		List<HashMap<String, String>> Result=goodsItem.query(goodsId);
		HashMap<String, String> goodsNow=Result.get(0);
		String nowPrice=goodsNow.get("nowPrice");
		if(Double.parseDouble(nowPrice)>myPrice&&Double.parseDouble(nowPrice)>maxPrice)
		{ 
			showMaxPrice.setText("  max "+nowPrice); 
			leaveTime=initTime+1;
			maxPrice=Double.parseDouble(nowPrice);
			if(checkTime)
			{
				new Thread(this).start();
				checkTime=false;
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {  
		 
		if(e.getSource()==bids)
		{  
			bidsPriceValue=bidsPrice.getText();
			if(bidsPriceValue.equals("")||!Tools.isNum(bidsPriceValue))
			{
				JOptionPane.showMessageDialog(this, "竞价不符合要求"); 
			}
			else
			{  
				if(goodsItem.auction(goodsId, bidsPriceValue)>0)
				{
					myPrice=Double.parseDouble(bidsPriceValue);
					showMaxPrice.setText("  max "+bidsPriceValue);  
					leaveTime=initTime+1; 
					showMyPrice.setText("  my "+Double.toString(myPrice));
					//new Thread(this).start();
					if(checkTime)
					{
						new Thread(this).start();
						checkTime=false;
					}
				}
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
	 
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) { 
	}
	public void actionPerformed(ActionEvent e) {
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			updateTime();
            repaint(); 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
 
 

}
