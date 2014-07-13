/*
 * name: 			订单本地bean(第三层)
 * description：		实现订单相关的Remote操作
 * author:			李海
 */
package auction.bean.local;
 
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector; 
import javax.swing.table.AbstractTableModel; 
import org.apache.axis2.AxisFault; 
import auction.bean.core.extraTools; 
import auction.bean.stub.AuctionWebserviceOrdersStub;


@SuppressWarnings("serial")
public class ordersLocal  extends AbstractTableModel  {
	AuctionWebserviceOrdersStub stub=null; 
	Vector<String> colums;
	Vector<Vector<String>> rows;
	
	public ordersLocal() 
	{
		try {
			stub = new AuctionWebserviceOrdersStub();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
	
	
	public int add(String goodsName,String userName, String bargainPrice, String bargainCount,String phone,String address)  
	{ 
		AuctionWebserviceOrdersStub.Add addPara = new AuctionWebserviceOrdersStub.Add();
		 addPara.setGoodsName(goodsName); 
		 addPara.setUserName(userName);
		 addPara.setBargainPrice(bargainPrice);
		 addPara.setBargainCount(bargainCount);
		 addPara.setAddress(address);
		 addPara.setPhone(phone);
	     String outString="-1";
		try {
			outString = stub.add(addPara).get_return();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	     int result=Integer.parseInt(outString);
		 return result;
	} 
	
	
	public int update(String orderId,String goodsName,String userName, String bargainPrice, String bargainCount,String phone,String address) 
	{
		AuctionWebserviceOrdersStub.Update updatePara = new AuctionWebserviceOrdersStub.Update();
		updatePara.setOrderId(orderId);
		updatePara.setGoodsName(goodsName);
		updatePara.setUserName(userName);
		updatePara.setBargainCount(bargainCount);
		updatePara.setBargainPrice(bargainPrice);
		updatePara.setAddress(address);
		updatePara.setPhone(phone); 
	    String outString = "-1";
		try {
			outString = stub.update(updatePara).get_return();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    int result=Integer.parseInt(outString);
		return result; 		
	}
	
	
	public int deleteById(String id)  
	{
		AuctionWebserviceOrdersStub.DeleteById delPara = new AuctionWebserviceOrdersStub.DeleteById();
		 delPara.setId(id);
	     String outString = "-1";
		try {
			outString = stub.deleteById(delPara).get_return();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	     int result=Integer.parseInt(outString);
		 return result;
	}
	
	
	public List<HashMap<String, String>> query(int id) throws Exception
	{
		AuctionWebserviceOrdersStub.Query queryPara = new AuctionWebserviceOrdersStub.Query();
		queryPara.setId(id);
	    String  outString=stub.query(queryPara).get_return(); 
	    List<HashMap<String, String>> listResult=(List<HashMap<String, String>>)extraTools.String2Byte2Object(outString);
	    return listResult;
	}
	 
	public List<HashMap<String, String>> getList(String id,String type,int start, int pageSize) throws Exception
	{
		AuctionWebserviceOrdersStub.GetList getListPara = new AuctionWebserviceOrdersStub.GetList();
		getListPara.setId(id);
		getListPara.setStart(start);
		getListPara.setPageSize(pageSize); 
	    String  outString=stub.getList(getListPara).get_return(); 
	    List<HashMap<String, String>> listResult=(List<HashMap<String, String>>)extraTools.String2Byte2Object(outString);
	    return listResult; 
	} 
	public String getColumnName(int column) {
		return this.colums.get(column);
	}

	
	public int getRowCount() {
		return this.rows.size();
	}

	
	public int getColumnCount() {
		return this.colums.size();
	}

	
	@SuppressWarnings("rawtypes")
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector) rows.get(rowIndex)).get(columnIndex);
	} 
	
	
	public void showIntable(String name,int start, int pageSize)  
	{
		List<HashMap<String, String>> usersList = null;
		try {
			usersList = getList("","order",0,30);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.colums = new Vector<String>();		
		colums.add("订单编号");
		colums.add("商品名称");
		colums.add("用户姓名");
		colums.add("拍卖价格");
 		colums.add("拍卖数量");
 		colums.add("联系地址");
 		colums.add("下单时间");
		Vector <String> keyList=new Vector<String>();
		keyList.add("id");
		keyList.add("goodsName");
		keyList.add("userName"); 
		keyList.add("bargainPrice");
		keyList.add("bargainCount");
		keyList.add("address"); 
		keyList.add("createTime"); 
		keyList.add("phone");
		keyList.add("type");
		
		this.rows = new Vector<Vector<String>>(); 
		for(int i=0; i<usersList.size();i++)
		{  
			rows.add(extraTools.map2Vector(usersList.get(i),keyList));
		}		
	}
 

 
}
