/*
 * name: 			货物核心bean(第一层)
 * description：		实现货物相关的逻辑操作
 * author:			李海
 */
package auction.bean.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 

@SuppressWarnings("serial")
public  class goods {
	
public int id;
public String goodsName ;
public int 	stockNum;
public int 	saleNum;
public String description;
public double stockPrice;
public double nowPrice;
public double bargainPrice;
public mysqlAction sqlHandle;
public String table; 

public goods()
{
	sqlHandle=new mysqlAction();
	table="goods";	
	this.saleNum=0;
	this.nowPrice=-1;
	this.bargainPrice=-1;
}
 
public int add(String goodsName,String description,String stockNum, String stockPrice)
{
	HashMap <String,String> keyValue=new HashMap<String,String>(); 
	keyValue.put("goodsName", goodsName);
	keyValue.put("stockPrice",stockPrice);
	keyValue.put("nowPrice", Double.toString(nowPrice));
	keyValue.put("bargainPrice", Double.toString(bargainPrice));
	keyValue.put("stockNum", stockNum);
	keyValue.put("saleNum", Integer.toString(saleNum));
	keyValue.put("description", description );	
	return sqlHandle.insert(table,keyValue);
	 
} 
public int delete(String id)
{
	HashMap <String,String> keyValue=new HashMap<String,String>(); 
	keyValue.put("id", id);  
	return sqlHandle.delete(table, keyValue); 
} 
public List<HashMap<String, String>> query(String goodsId)
{
	List<String> choose=new ArrayList<String>();
	choose.add("id");
	choose.add("goodsName");
	choose.add("description");
	choose.add("stockNum");
	choose.add("saleNum");
	choose.add("nowPrice");
	choose.add("bargainPrice");
	choose.add("stockPrice");
	HashMap<String,String> control=new HashMap<String,String>();
	control.put("id", goodsId); 
 	return sqlHandle.query(table, choose, control);
} 
public int sale(String id, String saleNum, String bargainPrice)
{
	List<HashMap<String, String>> getNum=query(id);
	int saleNumOld=Integer.parseInt(getNum.get(0).get("saleNum"));
	int saleNumNew=saleNumOld+Integer.parseInt(saleNum);
	HashMap<String,String> data=new HashMap<String,String>();
	data.put("id", id);
	data.put("saleNum", Integer.toString(saleNumNew));
	data.put("bargainPrice", (bargainPrice));
	HashMap<String,String> control=new HashMap<String,String>();
	control.put("id",id);
	return sqlHandle.update(table, control, data);	 
}
public List<HashMap<String, String>> getList(String goodsName,int start, int pageSize)
{
	List<String> choose=new ArrayList<String>();
	choose.add("id");
	choose.add("goodsName");
	choose.add("description");
	choose.add("stockNum");
	choose.add("stockPrice");
	choose.add("saleNum");
	choose.add("nowPrice");
	choose.add("bargainPrice");
	HashMap<String,String> control=new HashMap<String,String>();
 	if(goodsName.equals(""))
		control.put("1", "1");
	else
		control.put("goodsName", goodsName); 
	return sqlHandle.getList(table, choose, control,start,pageSize);
 
}
public List<HashMap<String, String>> getListByScale(String min, String max, int start, int pageSize)
{
	List<String> choose=new ArrayList<String>();
	choose.add("id");
	choose.add("goodsName");
	choose.add("description");
	choose.add("stockNum");
	choose.add("stockPrice");
	choose.add("saleNum");
	choose.add("nowPrice");
	choose.add("bargainPrice");
	String control=" where  ( stockNum - saleNum )  <"+ max+" and  ( stockNum - saleNum )  >"+min; 
	return sqlHandle.getListByScale(table, choose, control,start,pageSize);
}
public List<HashMap<String, String>> getListBySearch(String parameter)
{
	List<String> choose=new ArrayList<String>();
	choose.add("id");
	choose.add("goodsName");
	choose.add("description");
	choose.add("stockNum");
	choose.add("stockPrice");
	choose.add("saleNum");
	choose.add("nowPrice");
	choose.add("bargainPrice");
	String control=" where goodsName like '%"+parameter+"%' or "+" id='"+parameter+"'";
	return sqlHandle.getListByScale(table, choose, control,0,100);
}
public int update(String id,String goodsName,String description,String stockNum, String stockPrice)//, String saleNum,String nowPrice,String bargainPrice)
{	
		HashMap<String,String> keyValue=new HashMap<String,String>(); 
		keyValue.put("goodsName", goodsName);
		keyValue.put("description", description);
		keyValue.put("stockNum", stockNum);
		keyValue.put("stockPrice", stockPrice); 
		HashMap<String,String> control=new HashMap<String,String>();
		control.put("id", id);
		return sqlHandle.update(table, control, keyValue);		
}	
public int auction(String id, String nowPrice)
{
	List<HashMap<String, String>> getNum=query(id);
	double nowPriceOld=Double.parseDouble(getNum.get(0).get("nowPrice"));
	 
	if(nowPriceOld<0||nowPriceOld<Double.parseDouble(nowPrice))
	{
		HashMap<String,String> data=new HashMap<String,String>();
		data.put("id", id); 
		data.put("nowPrice", nowPrice);
		HashMap<String,String> control=new HashMap<String,String>();
		control.put("id", id); 
		return sqlHandle.update(table, control, data);			
	}
	else
		return -1; 
} 
 
}
