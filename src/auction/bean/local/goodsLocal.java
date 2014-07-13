/*
 * name: 			货物本地bean(第三层)
 * description：		实现货物相关的Remote操作
 * author:			李海
 */
package auction.bean.local; 

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;  
import auction.bean.core.extraTools;   
import auction.bean.stub.AuctionWebserviceGoodsStub; 
import java.util.Vector; 
import javax.swing.table.AbstractTableModel; 
import org.apache.axis2.AxisFault;
 

@SuppressWarnings("serial")
public  class goodsLocal  extends AbstractTableModel {
 
	AuctionWebserviceGoodsStub stub=null; 
	Vector<String> colums;
	Vector<Vector<String>> rows;

public goodsLocal()  
{ 
	 try {
		stub = new AuctionWebserviceGoodsStub();
	} catch (AxisFault e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
 
public int add(String goodsName,String description,String stockNum, String stockPrice) 
{
	 AuctionWebserviceGoodsStub.Add addPara = new AuctionWebserviceGoodsStub.Add();
	 addPara.setGoodsName(goodsName);
	 addPara.setDescription(description);
	 addPara.setStockNum(stockNum);
	 addPara.setStockPrice(stockPrice);
     String outString = null;
	try {
		outString = stub.add(addPara).get_return();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
     int result=Integer.parseInt(outString);
	 return result;
} 
 
public int delete(String id) 
{
	 AuctionWebserviceGoodsStub.Delete delPara = new AuctionWebserviceGoodsStub.Delete();
	 delPara.setId(id);
     String outString = "-1";
	try {
		outString = stub.delete(delPara).get_return();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
     int result=Integer.parseInt(outString);
	 return result;
} 
public List<HashMap<String, String>> query(String goodsId)  
{
	AuctionWebserviceGoodsStub.Query queryPara = new AuctionWebserviceGoodsStub.Query();
	queryPara.setGoodsId(goodsId);
    String outString="-1";
	try {
		outString = stub.query(queryPara).get_return();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    List<HashMap<String, String>> listResult=(List<HashMap<String, String>>)extraTools.String2Byte2Object(outString);
    return listResult;
} 

public int sale(String id, int saleNum, String bargainPrice)  
{
	AuctionWebserviceGoodsStub.Sale salePara = new AuctionWebserviceGoodsStub.Sale();
	salePara.setId(id);
	salePara.setSaleNum(Integer.toString(saleNum));
	salePara.setBargainPrice(bargainPrice);
    String outString="-1";
	try {
		outString = stub.sale(salePara).get_return();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    int result=Integer.parseInt(outString);
	return result; 
}
 
public List<HashMap<String, String>> getList(String goodsName,int start, int pageSize) throws Exception 
{
	AuctionWebserviceGoodsStub.GetList getListPara = new AuctionWebserviceGoodsStub.GetList();
	getListPara.setGoodsName(goodsName);
	getListPara.setStart(Integer.toString(start));
	getListPara.setPageSize(Integer.toString(pageSize)); 
    String  outString=stub.getList(getListPara).get_return(); 
    List<HashMap<String, String>> listResult=(List<HashMap<String, String>>)extraTools.String2Byte2Object(outString);
    return listResult; 
}

public List<HashMap<String, String>> getListByScale(String min, String max, int start, int pageSize) throws Exception 
{
	AuctionWebserviceGoodsStub.GetListByScale getListByScalePara = new AuctionWebserviceGoodsStub.GetListByScale();
	getListByScalePara.setMin(min);
	getListByScalePara.setMax(max);
	getListByScalePara.setStart(Integer.toString(start));
	getListByScalePara.setPageSize(Integer.toString(pageSize)); 
    String  outString=stub.getListByScale(getListByScalePara).get_return(); 
    List<HashMap<String, String>> listResult=(List<HashMap<String, String>>)extraTools.String2Byte2Object(outString);
    return listResult; 
}

public List<HashMap<String, String>> getListBySearch(String parameter) 
{
	AuctionWebserviceGoodsStub.GetListBySearch getListBySearchPara = new AuctionWebserviceGoodsStub.GetListBySearch();
	getListBySearchPara.setParameter(parameter); 
    String outString = null;
	try {
		outString = stub.getListBySearch(getListBySearchPara).get_return();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    List<HashMap<String, String>> listResult=(List<HashMap<String, String>>)extraTools.String2Byte2Object(outString);
    return listResult; 
}

public int update(String id,String goodsName,String description,String stockNum, String stockPrice) 
{	
	AuctionWebserviceGoodsStub.Update updatePara = new AuctionWebserviceGoodsStub.Update();
	updatePara.setId(id);
	updatePara.setGoodsName(goodsName);
	updatePara.setDescription(description);
	updatePara.setStockNum(stockNum);
	updatePara.setStockPrice(stockPrice); 
    String outString = null;
	try {
		outString = stub.update(updatePara).get_return();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    int result=Integer.parseInt(outString);
	return result; 
}	


public int auction(String id, String nowPrice) 
{
	AuctionWebserviceGoodsStub.Auction auctionPara = new AuctionWebserviceGoodsStub.Auction();
	auctionPara.setId(id);
	auctionPara.setNowPrice(nowPrice);
    String outString="-1";
	try {
		outString = stub.auction(auctionPara).get_return();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    int result=Integer.parseInt(outString);
	return result; 
}
@Override
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
	List<HashMap<String, String>> goodsList = null;
	try {
		goodsList = getList("",0,30);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.colums = new Vector<String>();		
	colums.add("产品编号");
	colums.add("产品名称");
	colums.add("采购价格");
	colums.add("采购数目"); 
	colums.add("已售数目");
	Vector <String> keyList=new Vector<String>();
	keyList.add("id");
	keyList.add("goodsName");
	keyList.add("stockPrice");
	keyList.add("stockNum");
	keyList.add("saleNum");	
	keyList.add("description");
	keyList.add("nowPrice");
	keyList.add("bargainPrice"); 
	
	this.rows = new Vector<Vector<String>>();   
	for(int i=0; i<goodsList.size();i++)
	{  
		rows.add(extraTools.map2Vector(goodsList.get(i),keyList));
	}		
}

public void showHomeTable(String control,int start, int pageSize) 
{
	List<HashMap<String, String>> goodsList = null;
	if(control.equals(""))
		try {
			goodsList=getList("",0,30);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	else
		try {
			goodsList=getListBySearch(control);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	this.colums = new Vector<String>();		
	colums.add("产品编号");
	colums.add("产品名称"); 
 	colums.add("描       述");
	colums.add("剩余数目");
	Vector <String> keyList=new Vector<String>();
	keyList.add("id");
	keyList.add("goodsName"); 
	keyList.add("description");
	keyList.add("overageNum"); 
	keyList.add("nowPrice");
	keyList.add("bargainPrice"); 
	
	this.rows = new Vector<Vector<String>>();   
	for(int i=0; i<goodsList.size();i++)
	{  
		Vector<String> result=new Vector<String>();
        String str=null;
        int k=0; 
        while(k<keyList.size())
        {
        	str=keyList.get(k);
        	if(!str.equals("overageNum"))
        		result.add(goodsList.get(i).get(str).toString());
        	else
        	{
        		int overageNum=Integer.parseInt(goodsList.get(i).get("stockNum").toString())-Integer.parseInt(goodsList.get(i).get("saleNum").toString());
        		result.add(Integer.toString(overageNum));        	
        	}        		
        	k++;
        } 
		rows.add(result);
	}		
}


public void showOverageTable(String control,int start, int pageSize)  
{
	List<HashMap<String, String>> goodsList = null;
	if(control.equals(""))
		try {
			goodsList=getList("",0,30);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	else
		try {
			goodsList=getListBySearch(control);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	this.colums = new Vector<String>();		
	colums.add("产品编号");
	colums.add("产品名称");
	colums.add("采购价格");
	colums.add("采购数目"); 
	colums.add("剩余数目");
	Vector <String> keyList=new Vector<String>();
	keyList.add("id");
	keyList.add("goodsName");
	keyList.add("stockPrice");
	keyList.add("stockNum");
	keyList.add("overageNum");	
	keyList.add("description");
	keyList.add("nowPrice");
	keyList.add("bargainPrice"); 
	
	this.rows = new Vector<Vector<String>>();   
	for(int i=0; i<goodsList.size();i++)
	{  
		Vector<String> result=new Vector<String>();
        String str=null;
        int k=0; 
        while(k<keyList.size())
        {
        	str=keyList.get(k);
        	if(!str.equals("overageNum"))
        		result.add(goodsList.get(i).get(str).toString());
        	else
        	{
        		int overageNum=Integer.parseInt(goodsList.get(i).get("stockNum").toString())-Integer.parseInt(goodsList.get(i).get("saleNum").toString());
        		result.add(Integer.toString(overageNum));        	
        	}        		
        	k++;
        } 
		rows.add(result);
	}		
}


public void updateOverageTable( String min, String max,int start, int pageSize)  
{
	List<HashMap<String, String>> goodsList = null;
	try {
		goodsList = getListByScale(min,max,0,30);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.colums = new Vector<String>();		
	colums.add("产品编号");
	colums.add("产品名称");
	colums.add("采购价格");
	colums.add("采购数目"); 
	colums.add("剩余数目");
	Vector <String> keyList=new Vector<String>();
	keyList.add("id");
	keyList.add("goodsName");
	keyList.add("stockPrice");
	keyList.add("stockNum");
	keyList.add("overageNum");	
	keyList.add("description");
	keyList.add("nowPrice");
	keyList.add("bargainPrice"); 
	
	this.rows = new Vector<Vector<String>>();   
	for(int i=0; i<goodsList.size();i++)
	{  
		Vector<String> result=new Vector<String>();
        String str=null;
        int k=0; 
        while(k<keyList.size())
        {
        	str=keyList.get(k);
        	if(!str.equals("overageNum"))
        		result.add(goodsList.get(i).get(str).toString());
        	else
        	{
        		int overageNum=Integer.parseInt(goodsList.get(i).get("stockNum").toString())-Integer.parseInt(goodsList.get(i).get("saleNum").toString());
        		result.add(Integer.toString(overageNum));        	
        	}        		
        	k++;
        } 
		rows.add(result);
	}		
}

}

 
  