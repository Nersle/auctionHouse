/*
 * name: 			货物WebService-bean(第一层)
 * description：		实现货物相关的WebService服务
 * author:			李海
 */
package auction.bean.webService;
  
import java.util.HashMap;
import java.util.List; 
import auction.bean.core.extraTools;
import auction.bean.core.goods;


@SuppressWarnings("serial")
public  class goodsInterface {
	 
goods goodsItem=new goods(); 

public goodsInterface()
{ 
}
public String sayHello(String name)
{
	return name+"么么哒";
}
 
public String add(String goodsName,String description,String stockNum, String stockPrice)
{
	String result="";
	result=String.valueOf(goodsItem.add(goodsName, description, stockNum, stockPrice));
	return result; 
} 
public String delete(String id)
{ 
	String result="";
	result=String.valueOf(goodsItem.delete(id));
	return result; 
} 
public String query(String goodsId)
{
	List<HashMap<String, String>> resultList=null; 
	resultList=goodsItem.query(goodsId);
	String result=extraTools.Object2Byte2String(resultList); 
 	return result;
} 

public String sale(String id, String saleNum, String bargainPrice)
{
	String result="";
	result=String.valueOf(goodsItem.sale(id,saleNum, bargainPrice));
	return result;  
}
 
public String getList(String goodsName,String start, String pageSize)
{
	List<HashMap<String, String>> resultList=null; 
	resultList=goodsItem.getList(goodsName,Integer.parseInt(start),Integer.parseInt(pageSize));
	String result=extraTools.Object2Byte2String(resultList); 
 	return result;  
}
public String getListByScale(String min, String max,String start, String pageSize)
{
	List<HashMap<String, String>> resultList=null; 
	resultList=goodsItem.getListByScale(min, max,Integer.parseInt(start),Integer.parseInt(pageSize));
	String result=extraTools.Object2Byte2String(resultList); 
 	return result; 
}

public String getListBySearch(String parameter)
{
	List<HashMap<String, String>> resultList=null; 
	resultList=goodsItem.getListBySearch(parameter);
	String result=extraTools.Object2Byte2String(resultList); 
 	return result; 
}

public String update(String id,String goodsName,String description,String stockNum, String stockPrice)//, String saleNum,String nowPrice,String bargainPrice)
{	 
	String result="";
	result=String.valueOf(goodsItem.update(id, goodsName, description, stockNum, stockPrice));
	return result;  	
}	
 
public String auction(String id, String nowPrice)
{
	String result="";
	result=String.valueOf(goodsItem.auction(id, nowPrice));
	return result;  
} 
 
}
