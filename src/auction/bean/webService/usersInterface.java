/*
 * name: 			用户WebService-bean(第一层)
 * description：		实现用户相关的WebService服务
 * author:			李海
 */
package auction.bean.webService;

import java.util.HashMap;
import java.util.List;
import auction.bean.core.extraTools;
import auction.bean.core.users;
 
@SuppressWarnings("serial")
public  class usersInterface  {
	

	users usersItem=new users(); 
	 
	public usersInterface()
	{ 
	} 
	
	public String add(String name,String pwd,String gender, String email,String age,String phone, String address)
	{ 
		String result="";
		result=String.valueOf(usersItem.add(name, pwd, gender, email, age, phone, address));
		return result; 
	} 
	public String delete(String name)
	{
		String result="";
		result=String.valueOf(usersItem.delete(name));
		return result; 
	} 
	public String deleteById(String id)
	{
		String result="";
		result=String.valueOf(usersItem.deleteById(id));
		return result; 
	}
	public String query(String name)
	{
		List<HashMap<String, String>> resultList=null; 
		resultList=usersItem.query(name);
		String result=extraTools.Object2Byte2String(resultList); 
	 	return result;
	}
	public String getList(String name,int start, int pageSize)
	{
		List<HashMap<String, String>> resultList=null; 
		resultList=usersItem.getList(name, start, pageSize);
		String result=extraTools.Object2Byte2String(resultList); 
	 	return result; 
	} 
	public String update(String id,String name,String gender, String email,String age,String phone, String address)
	{
		String result="";
		result=String.valueOf(usersItem.update(id, name, gender, email, age, phone, address));
		return result; 	
	}	
	public String changePwd(String name, String oldPwd, String newPwd)
	{
		String result="";
		result=String.valueOf(usersItem.changePwd(name, oldPwd, newPwd));
		return result; 	
	}
	public String auth(String name, String pwd)
	{
		String result="";
		result=String.valueOf(usersItem.auth(name, pwd));
		return result; 	
	}
 
}
