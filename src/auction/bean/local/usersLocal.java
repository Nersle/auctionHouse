/*
 * name: 			用户本地bean(第三层)
 * description：		实现用户相关的Remote操作
 * author:			李海
 */
package auction.bean.local;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.table.*; 
import org.apache.axis2.AxisFault; 
import auction.bean.core.extraTools;
import auction.bean.stub.AuctionWebserviceUsersStub;
 

@SuppressWarnings("serial")
public  class usersLocal  extends AbstractTableModel {
	
	AuctionWebserviceUsersStub stub=null; 
	Vector<String> colums;
	Vector<Vector<String>> rows;
	
	public usersLocal() 
	{
		try {
			stub = new AuctionWebserviceUsersStub();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public int add(String name,String pwd,String gender, String email,String age,String phone, String address)  
	{ 
		AuctionWebserviceUsersStub.Add addPara = new AuctionWebserviceUsersStub.Add();
		 addPara.setName(name);
		 addPara.setPwd(pwd);
		 addPara.setGender(gender);
		 addPara.setEmail(email);
		 addPara.setAge(age);
		 addPara.setPhone(phone);
		 addPara.setAddress(address);
		 String outString = "-1";
		try {
			outString = stub.add(addPara).get_return();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	     int result=Integer.parseInt(outString);
		 return result;
	} 
	public int delete(String name) throws Exception
	{
		AuctionWebserviceUsersStub.Delete delPara = new AuctionWebserviceUsersStub.Delete();
		delPara.setName(name);
	    String  outString=stub.delete(delPara).get_return(); 
	    int result=Integer.parseInt(outString);
		return result;
	} 
	public int deleteById(String id) 
	{
		AuctionWebserviceUsersStub.DeleteById delPara = new AuctionWebserviceUsersStub.DeleteById();
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
	public List<HashMap<String, String>> query(String name) 
	{
		AuctionWebserviceUsersStub.Query queryPara = new AuctionWebserviceUsersStub.Query();
		queryPara.setName(name);
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
	public List<HashMap<String, String>> getList(String name,int start, int pageSize) throws Exception
	{
		AuctionWebserviceUsersStub.GetList getListPara = new AuctionWebserviceUsersStub.GetList();
		getListPara.setName(name);
		getListPara.setStart(start);
		getListPara.setPageSize(pageSize);
	    String  outString=stub.getList(getListPara).get_return(); 
	    List<HashMap<String, String>> listResult=(List<HashMap<String, String>>)extraTools.String2Byte2Object(outString);
	    return listResult; 
	} 
	public int update(String id,String name,String gender, String email,String age,String phone, String address) 
	{
		AuctionWebserviceUsersStub.Update updatePara = new AuctionWebserviceUsersStub.Update();
		updatePara.setId(id);
		updatePara.setName(name);
		updatePara.setGender(gender);
		updatePara.setEmail(email);
		updatePara.setPhone(phone);
		updatePara.setAddress(address);
		updatePara.setAge(age);
		
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
	public int changePwd(String name, String oldPwd, String newPwd)  
	{
		AuctionWebserviceUsersStub.ChangePwd changePwdPara = new AuctionWebserviceUsersStub.ChangePwd();
		changePwdPara.setName(name);
		changePwdPara.setOldPwd(oldPwd);
		changePwdPara.setNewPwd(newPwd);
	    String outString = null;
		try {
			outString = stub.changePwd(changePwdPara).get_return();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    int result=Integer.parseInt(outString);
		return result; 
	}
	public int auth(String name, String pwd) 
	{
		AuctionWebserviceUsersStub.Auth authPara = new AuctionWebserviceUsersStub.Auth();
		authPara.setName(name);
		authPara.setPwd(pwd);
	    String outString = "-1";
		try {
			outString = stub.auth(authPara).get_return();
		} catch (RemoteException e) { 
			e.printStackTrace();
		} 
	    int result=Integer.parseInt(outString);
		return result; 
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.colums.get(column);
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colums.size();
	}

	@SuppressWarnings("rawtypes")
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector) rows.get(rowIndex)).get(columnIndex);
	} 
	
	public void showIntable(String name,int start, int pageSize) 
	{
		List<HashMap<String, String>> usersList=null;
		try {
			usersList = getList("",0,30);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.colums = new Vector<String>();		
		colums.add("会员编号");
		colums.add("姓        名");
		colums.add("邮        箱");
		colums.add("性        别");
//		colums.add("手        机");
 		colums.add("联系地址");
		Vector <String> keyList=new Vector<String>();
		keyList.add("id");
		keyList.add("name");
		keyList.add("email");
		keyList.add("gender");
		keyList.add("address");
		keyList.add("age");
		keyList.add("phone");
		keyList.add("createTime"); 
		
		this.rows = new Vector<Vector<String>>(); 
		for(int i=0; i<usersList.size();i++)
		{  
			rows.add(extraTools.map2Vector(usersList.get(i),keyList));
		}		
	}
}
