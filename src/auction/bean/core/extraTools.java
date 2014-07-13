/*
 * name: 			extraTools bean
 * description：		实现数字验证、HashMap与String之间的转换等操作
 * author:			李海
 */
package auction.bean.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

public class extraTools {
	
	public static Vector<String> map2Vector(HashMap<String,String> data,Vector <String> key)
	{
		Vector<String> result=new Vector<String>();
        String str=null;
        int i=0; 
        while(i<key.size())
        {
        	str=key.get(i);
        	result.add(data.get(str).toString());
        	i++;
        } 
		return result;		
	}
	public static boolean isNum(String ch)  
    {  
        try  
        {     
            double i = Double.parseDouble(ch);
            return true;  
        }  
        catch (NumberFormatException e)  
        {  
            return false;  
        }
    }
	public static  java.lang.Object String2Byte2Object(String inputString)
	{
		try{
 
		    byte [] outBytesRes = String2byte(inputString);
			java.lang.Object obj ;
	 		
			java.io.ByteArrayInputStream byteInput=new  java.io.ByteArrayInputStream(outBytesRes);
			java.io.ObjectInputStream obejectInput=new java.io.ObjectInputStream(byteInput);
			obj=obejectInput.readObject();
			byteInput.close();
			obejectInput.close();
			return obj;
		}
		catch (Exception e)
		{
			System.out.print("err2"+e.getMessage()); 
			e.printStackTrace();
			return null;
		}
	}  
	private static String byte2String(byte[] bytes) {
	    String resString = "";
	    for (int i = 0; i < bytes.length; i++) {
            resString += String.valueOf(bytes[i]);
            resString += ":";
        }
	    //System.out.println(resString);
	    return resString;
	}
	private static byte[] String2byte(String str) {
	    byte[] bytes = new byte[str.length()/2];
	    int len = 0;
	    for (int i = 0; i < str.length(); i++) {
            boolean isNegative = false;
            if(str.charAt(i) == '-') {
                isNegative = true;
                i++;
            }
            byte temp = 0;
            while(str.charAt(i)!=':') {
                temp = (byte) (temp*10 + (str.charAt(i) - '0'));
                i++;
            }
            if (isNegative) {
                temp = (byte) -temp;
            }
            bytes[len++] = temp;
         //   System.out.print(bytes[len-1] + "  ");
        }
	    //System.out.println();
	    
	    return bytes;
	}
	public static String Object2Byte2String(java.lang.Object obj)
	{
		try
		{
			//对象转化为字节数组
			java.io.ByteArrayOutputStream byteOutput=new java.io.ByteArrayOutputStream();
			java.io.ObjectOutputStream obejectOutput=new java.io.ObjectOutputStream(byteOutput);
			byte[] bytes;
			obejectOutput.writeObject(obj);
			bytes=byteOutput.toByteArray();
			byteOutput.close();
			obejectOutput.close(); 
			return byte2String(bytes);
 
		}
		catch(Exception e)
		{
			System.out.print("err1"+e.getMessage());
			return "nothing";
		}
	}
	public static String getListHashMap(String hash) 
	{
     	List<HashMap<String, String>> Result ;
     	List<HashMap<String, String>> tmp ;
     	Result=new ArrayList<HashMap<String,String>>(); 
     	HashMap<String,String> keyValue=new HashMap<String,String>();
     	keyValue.put("hash",hash); 
     	keyValue.put("hash1","神马hash"); 
    	Result.add(keyValue);  
     	keyValue=new HashMap<String,String>();
     	keyValue.put("hash2",hash); 
     	keyValue.put("hash21","神马hash2"); 
    	Result.add(keyValue);   
    	String outString = Object2Byte2String(Result);   
    	tmp= (List<HashMap<String, String>>) String2Byte2Object(outString);
    	System.out.println(tmp.size()+tmp.get(0).get("hash")+"\n" ); 
    	return outString;  
	}
	public static List<List<String>> map2list(HashMap<String,String> data)
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
	public static void printHash(List<HashMap<String, String>> items)
	{ 
		for(int i=0; i<items.size();i++)
		{ 
			List<List<String>> list=new ArrayList<List<String>>();
	    	list=map2list(items.get(i));
			List<String> keyList=(List<String>) list.get(0);
	    	List<String> valueList=(List<String>) list.get(1); 
	    	System.out.println(i);
			for(int j=0; j<keyList.size(); j++)
			{
				System.out.println(keyList.get(j)+":::"+valueList.get(j));
			}
		}
	}
}
