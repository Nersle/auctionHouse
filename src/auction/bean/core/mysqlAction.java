/*
 * name: 			sql-bean(第零层)
 * description：		实现mysql底层操作相关的抽象接口，
 * 					对上层提供良好的接口
 * author:			李海
 */
package auction.bean.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class mysqlAction {
    private String DBName;
    private String Host;
    private int Port;
    private String Table;
    private String user;
    private String pwd;
    private Connection connect; 
    static Statement statement; 
    
    public mysqlAction()
    {
    	this.Host="127.0.0.1";
    	this.Port=3306;
    	this.user="root";
    	this.pwd="";
    	this.DBName="auctionDB";
    }
    
    public mysqlAction(String Host, int Port,String user, String pwd,String DBName)
    {
    	this.Host=Host;
    	this.Port=Port;
    	this.user=user;
    	this.pwd=pwd;
    	this.DBName=DBName;
    }

    public  void getConnection()
    {
        Connection conn = null;	
        String DBStr="jdbc:mysql://"+this.Host+":"+Integer.toString(this.Port)+"/"+this.DBName+"?characterEncoding=UTF-8";
    	try 
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(
                    DBStr, this.user,this.pwd);
        } catch (Exception e) {
            System.out.println("数据库连接失败" + e.getMessage());
            e.printStackTrace();
        }
        this.connect=conn; 
    } 
 
	public List<List<String>> map2list(HashMap<String,String> data)
    {
    	Iterator<Entry<String, String>> iter=data.entrySet().iterator();
    	List<String> keyList=new ArrayList<String>();
    	List<String> valueList=new ArrayList<String>(); 
    	while(iter.hasNext())
    	{
    		Map.Entry entry=(Map.Entry)iter.next();
    		keyList.add(keyList.size(),entry.getKey().toString());
    		valueList.add(valueList.size(), entry.getValue().toString());
    	}
    	List<List<String>> list=new ArrayList<List<String>>();
    	list.add(keyList);
    	list.add(valueList);
    	return list;    	
    }

	public String map2String(HashMap<String,String> data, String seperator)
    {
		List<List<String>>  list=map2list(data);    	
		List<String> keyList=(List<String>) list.get(0);
		List<String> valueList=(List<String>)  list.get(1);
     	
     	int i=0;
     	int len=keyList.size();
     	String sqlValue="";
     	while(i<len)
     	{ 
     		sqlValue=sqlValue+keyList.get(i).toString()+"=\'"+valueList.get(i).toString()+"\'"+seperator;
     		i++;
     	} 
     	sqlValue=sqlValue.subSequence(0, sqlValue.length()-seperator.length()-1).toString()+"\'"; 
     	return sqlValue;    	
    }
    
	public   void print(String str)
	{
		System.out.println(str);
	}
	
	public  int insert(String table, HashMap<String,String> data) 
    {
    	getConnection(); 
    	List<List<String>>  list=map2list(data);
    	List<String> keyList=(List<String>) list.get(0);
    	List<String> valueList=(List<String>) list.get(1);
    	
    	int i=0;
    	int len=keyList.size();
    	String sqlBase="insert into "+table;
    	String sqlKey="(";
    	String sqlValue=" values (";
    	while(i<len)
    	{
    		sqlKey=sqlKey+keyList.get(i).toString()+",";
    		sqlValue=sqlValue+"\'"+valueList.get(i).toString()+"\',";
    		i++;
    	}
    	sqlKey= sqlKey.subSequence(0, sqlKey.length()-1).toString();
    	sqlKey+=")";
    	sqlValue=sqlValue.subSequence(0, sqlValue.length()-1).toString();
    	sqlValue+=")";  
    	String sql=sqlBase+sqlKey+sqlValue;    	
    	 
    	int result=-1;
        try
        { 
        	statement= (Statement) connect.createStatement();	 
            result = statement.executeUpdate(sql); 
            System.out.println("向"+table+"表中插入 " + result + " 条数据"); 
            connect.close(); 
        } catch (SQLException e) {
            System.out.println("插入数据失败" +sql+ e.getMessage());
        }
        return result;
    }


    public  int update(String table, HashMap<String,String> control, HashMap<String,String> data) {
    	getConnection();  
     	String sqlBase="update "+table+" set ";
     	String sql=sqlBase+map2String(data," , ");
     	sql=sql+" where "+ map2String(control," and "); 
     	System.out.println(sql);
    	int result=-1;
        try {
        	statement = (Statement) connect.createStatement();	 
            result = statement.executeUpdate(sql); 
           // System.out.println(table+" 表中更新 " + result + " 条数据"); 
            connect.close();	 
        } catch (SQLException e) {
            System.out.println("更新数据失败");
        }
        return result;
    }

	public  List<HashMap<String, String>>  getList(String table,List choose, HashMap<String,String> control,int start, int pageSize) {
        getConnection(); 
        int i=0;
        String chooseRow="";
        while(i<choose.size())
        {
       	 chooseRow=chooseRow+choose.get(i).toString()+",";
       	 i++;
        }
       chooseRow=chooseRow.subSequence(0, chooseRow.length()-1).toString();
    	String sqlBase="select "+chooseRow+" from "+table+" where ";
    	String sql=sqlBase+map2String(control,"and");
    	String limit=" limit "+Integer.toString(start)+","+Integer.toString(pageSize);
    	sql=sql+limit;
    	System.out.println(sql);
    	
    	List<HashMap<String, String>> Result=new ArrayList<HashMap<String,String>>();
    	HashMap<String, String> keyValue=null;
    	
       try { 	 
	           statement = (Statement) connect.createStatement();	 
	           ResultSet rs = statement.executeQuery(sql);  
	           while (rs.next()) { 
	           	i=0;
	           	keyValue=new HashMap<String,String>();
	           	while(i<choose.size())
	           	{
	           		keyValue.put(choose.get(i).toString(), rs.getString(choose.get(i).toString()));
	           		i++;
	           	}
	           	Result.add(keyValue);  
	             
	           }
	           connect.close(); 
       } catch (SQLException e) {
           System.out.println("查询数据失败");
       }
       return Result;
   }
	public  List<HashMap<String, String>>  getListByScale(String table,List choose, String control,int start, int pageSize) {
        getConnection(); 
        int i=0;
        String chooseRow="";
        while(i<choose.size())
        {
       	 chooseRow=chooseRow+choose.get(i).toString()+",";
       	 i++;
        }
       chooseRow=chooseRow.subSequence(0, chooseRow.length()-1).toString();
    	String sqlBase="select "+chooseRow+" from "+table+" ";
    	String sql=sqlBase+control;
    	String limit=" limit "+Integer.toString(start)+","+Integer.toString(pageSize);
    	sql=sql+limit;
    	System.out.println(sql);
    	
    	List<HashMap<String, String>> Result=new ArrayList<HashMap<String,String>>();
    	HashMap<String, String> keyValue=null;
    	
       try { 	 
	           statement = (Statement) connect.createStatement();	 
	           ResultSet rs = statement.executeQuery(sql);  
	           while (rs.next()) { 
	           	i=0;
	           	keyValue=new HashMap<String,String>();
	           	while(i<choose.size())
	           	{
	           		keyValue.put(choose.get(i).toString(), rs.getString(choose.get(i).toString()));
	           		i++;
	           	}
	           	Result.add(keyValue);  
	             
	           }
	           connect.close(); 
       } catch (SQLException e) {
           System.out.println("查询数据失败");
       }
       return Result;
   }

    
	public  List<HashMap<String, String>>  query(String table,List choose, HashMap<String,String> control) {
         getConnection(); 
         int i=0;
         String chooseRow="";
         while(i<choose.size())
         {
        	 chooseRow=chooseRow+choose.get(i).toString()+",";
        	 i++;
         }
        chooseRow=chooseRow.subSequence(0, chooseRow.length()-1).toString();
     	String sqlBase="select "+chooseRow+" from "+table+" where ";
     	String sql=sqlBase+map2String(control,"and"); 
     	
     	System.out.println(sql);
     	
     	List<HashMap<String, String>> Result=null;
     	HashMap<String,String> keyValue=new HashMap<String,String>();
     	
        try { 	 
            statement = (Statement) connect.createStatement();	 
            ResultSet rs = statement.executeQuery(sql);  
         	Result=new ArrayList<HashMap<String,String>>(); 
            while (rs.next()) { 
            	i=0;
            	while(i<choose.size())
            	{
            		keyValue.put(choose.get(i).toString(), rs.getString(choose.get(i).toString()));
            		i++;
            	}
            	Result.add(keyValue);              
            }
            connect.close(); 
        } catch (SQLException e) { 
            System.out.println("查询数据失败");
        }
        return Result;
    }

    public  int delete(String table, HashMap<String,String> control) {
    	getConnection();  
     	String sqlBase="delete from "+table+" where ";
     	String sql=sqlBase+map2String(control,"and"); 
     	int result=-1;
        try { 
        	statement = (Statement) connect.createStatement(); 
        	result= statement.executeUpdate(sql); 
            System.out.println(table+" 表中删除 " + result + " 条数据\n");	 
            connect.close(); 
        } catch (SQLException e) {
            System.out.println("删除数据失败");
        }
        return result;
    }
 

}
