/*
 * name: 			用户核心bean(第一层)
 * description：		实现用户相关的逻辑操作
 * author:			李海
 */
package auction.bean.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 

@SuppressWarnings("serial")
public  class users  {
	
	public String name;
	public String pwd;
	public String email;
	public mysqlAction sqlHandle;
	public String table;
	 
	
	public users()
	{
		sqlHandle=new mysqlAction();
		table="users";
	} 
	public int add(String name,String pwd,String gender, String email,String age,String phone, String address)
	{ 
		HashMap<String,String> keyValue=new HashMap<String,String>(); 
		keyValue.put("name", name);
		keyValue.put("pwd", pwd);
		keyValue.put("gender", gender);
		keyValue.put("email", email); 
		keyValue.put("age", age);
		keyValue.put("phone", phone);
		keyValue.put("address", address);
		return sqlHandle.insert(table,keyValue);
		 
	} 
	public int delete(String name)
	{
		HashMap<String,String> keyValue=new HashMap<String,String>(); 
		keyValue.put("name", name); 
		return sqlHandle.delete(table, keyValue); 
	} 
	public int deleteById(String id)
	{
		HashMap<String,String> keyValue=new HashMap<String,String>(); 
		keyValue.put("id",id); 
		return sqlHandle.delete(table, keyValue); 
	}
	public List<HashMap<String, String>> query(String name)
	{
		List <String>choose=new ArrayList<String>();
		choose.add("id");
		choose.add("name");
		choose.add("pwd");
		choose.add("email");
		choose.add("age");
		choose.add("gender");
		choose.add("phone");
		choose.add("address");
		choose.add("type");
		choose.add("createTime");
		HashMap<String,String> control=new HashMap<String,String>();
		control.put("name", name); 
     	return sqlHandle.query(table, choose, control);
	}
	public List<HashMap<String, String>> getList(String name,int start, int pageSize)
	{
		List<String> choose=new ArrayList<String>();
		choose.add("id");
		choose.add("name");
		choose.add("pwd");
		choose.add("gender");
		choose.add("email");
		choose.add("age");
		choose.add("phone");
		choose.add("address");
		choose.add("createTime");
		HashMap<String,String> control=new HashMap<String,String>();		
		if(name.equals(""))
			control.put("1", "1");
		else 
			control.put("name", name); 
		return sqlHandle.getList(table, choose, control,start,pageSize);
	} 
	public int update(String id,String name,String gender, String email,String age,String phone, String address)
	{
			HashMap<String,String> keyValue=new HashMap<String,String>();
			keyValue.put("name", name); 
			keyValue.put("gender", gender);
			keyValue.put("email", email); 
			keyValue.put("age", age);
			keyValue.put("phone", phone);
			keyValue.put("address", address);
			HashMap<String,String> control=new HashMap<String,String>();
			control.put("id", id);
			return sqlHandle.update(table, control, keyValue);		
	}	
	public int changePwd(String name, String oldPwd, String newPwd)
	{
		if(oldPwd.equals(newPwd))
			return -1;
		if(auth(name,oldPwd)==1)
		{
			HashMap<String,String> data=new HashMap<String,String>();
			data.put("name", name);
			data.put("pwd", newPwd);
			HashMap<String,String> control=new HashMap<String,String>();
			control.put("name", name);
			return sqlHandle.update(table, control, data);		
		}
		else
			return -1;

	}
	public int auth(String name, String pwd)
	{
		List<HashMap<String, String>> Result=query(name);
		if(Result.size()<1)
		{
			return -1;
		}
		else
		{
			 
			if(Result.get(0).get("pwd").equals(pwd))
				return 1;
			else 
				return -1;
		}
	}
 
}
