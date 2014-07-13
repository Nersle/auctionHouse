/*
 * name: 			订单WebService-bean(第一层)
 * description：		实现订单相关的WebService服务
 * author:			李海
 */
package auction.bean.webService;
 
import java.util.HashMap;
import java.util.List;
import auction.bean.core.extraTools;
import auction.bean.core.orders;

@SuppressWarnings("serial")
public class ordersInterface  {
	 
	orders ordersItem=new orders(); 
	
	public ordersInterface()
	{ 
	}  
	
	public String add(String goodsName,String userName, String bargainPrice, String bargainCount,String phone,String address)
	{ 
		String result="";
		result=String.valueOf(ordersItem.add(goodsName, userName, bargainPrice, bargainCount, phone, address));
		return result; 
	} 
	
	public String update(String orderId,String goodsName,String userName, String bargainPrice, String bargainCount,String phone,String address)
	{
		String result="";
		result=String.valueOf(ordersItem.update(orderId, goodsName, userName, bargainPrice, bargainCount, phone, address));
		return result; 
	}
	
	public String deleteById(String id)
	{
		String result="";
		result=String.valueOf(ordersItem.deleteById(id));
		return result; 
	}
	
	public String query(int id)
	{
		List<HashMap<String, String>> resultList=null; 
		resultList=ordersItem.query(id);
		String result=extraTools.Object2Byte2String(resultList); 
	 	return result;
	}
	 
	public String getList(String id,String type,int start, int pageSize)
	{
		List<HashMap<String, String>> resultList=null; 
		resultList=ordersItem.getList(id, type, start, pageSize);
		String result=extraTools.Object2Byte2String(resultList); 
	 	return result; 
	}  
}
