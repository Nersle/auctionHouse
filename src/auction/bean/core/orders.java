/*
 * name: 			订单核心bean(第一层)
 * description：		实现订单相关的逻辑操作
 * author:			李海
 */
package auction.bean.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 

@SuppressWarnings("serial")
public class orders  {
	
	public int id;
	public int userId;
	public int goodsId;
	public double bargainPrice;
	public double salequantity; 
	public mysqlAction sqlHandle;
	public String table;
  
	
	public orders()
	{
		sqlHandle=new mysqlAction();
		table="orders";
	}  
	
	
	public int add(String goodsName,String userName, String bargainPrice, String bargainCount,String phone,String address)
	{ 
		HashMap<String,String> keyValue=new HashMap<String,String>(); 
		keyValue.put("goodsName",goodsName ); 
		keyValue.put("userName", userName); 
		keyValue.put("bargainPrice",bargainPrice ); 
		keyValue.put("bargainCount",bargainCount  );
		keyValue.put("phone",phone ); 
		keyValue.put("type","order" ); 
		keyValue.put("address",address  );
		return sqlHandle.insert(table,keyValue);
		 
	} 
	
	
	public int update(String orderId,String goodsName,String userName, String bargainPrice, String bargainCount,String phone,String address)
	{
		HashMap<String,String> keyValue=new HashMap<String,String>(); 
		keyValue.put("goodsName",goodsName ); 
		keyValue.put("userName", userName); 
		keyValue.put("bargainPrice",bargainPrice); 
		keyValue.put("bargainCount",bargainCount);
		keyValue.put("phone",phone ); 
		keyValue.put("address",address );
		HashMap<String,String> control=new HashMap<String,String>();
		control.put("id", orderId);
		return sqlHandle.update(table, control, keyValue);		
	}
	
	
	public int deleteById(String id)
	{
		HashMap<String,String> keyValue=new HashMap<String,String>(); 
		keyValue.put("id",id); 
		return sqlHandle.delete(table, keyValue); 
	}
	
	
	public List<HashMap<String, String>> query(int id)
	{
		List <String>choose=new ArrayList<String>();
		choose.add("id");
		choose.add("userId");
		choose.add("goodsId");
		choose.add("bargainPrice");
		choose.add("salequantity");
		HashMap<String,String> control=new HashMap<String,String>();
		control.put("id", Integer.toString(id)); 
     	return sqlHandle.query(table, choose, control);
	}
	 
	public List<HashMap<String, String>> getList(String id,String type,int start, int pageSize)
	{
		List<String> choose=new ArrayList<String>();
		choose.add("id");
		choose.add("goodsName");
		choose.add("userName"); 
		choose.add("bargainPrice");
		choose.add("bargainCount");
		choose.add("address"); 
		choose.add("createTime"); 
		choose.add("phone");
		choose.add("type"); 
		HashMap<String,String> control=new HashMap<String,String>();		
		if(id=="")
			control.put("1", "1");
		else 
		{ 
			control.put("id", id); 
			if(!type.equals("order"))
				type="auction";
			control.put("type", type);
		}
		return sqlHandle.getList(table, choose, control,start,pageSize);
	} 
 
 
}
