package org.metaworks.common;

import java.io.InputStream;
import java.io.OutputStream;

import org.metaworks.Refresh;

public class MetaworksUtil {

	static public void copyStream(InputStream sourceInputStream, OutputStream targetOutputStream) throws Exception{
		int length = 1024;
		byte[] bytes = new byte[length]; 
		int c; 
		int total_bytes=0;
			
		try{
			while ((c = sourceInputStream.read(bytes)) != -1) { 
					total_bytes +=c; 
					targetOutputStream.write(bytes,0,c); 
			} 
		}finally{
			if (sourceInputStream != null) try { sourceInputStream.close(); } catch (Exception e) {}
			if (targetOutputStream != null) try { targetOutputStream.close(); } catch (Exception e) {}
		}
	}
	
	static public Object[] putObjectArray(Object[] array, Object object){
		return MetaworksUtil.putObjectArray(array,  object, true);
	}
	
	// TODO: refectoring
	static public Object[] putObjectArray(Object[] array, Object object, boolean useRefresh){
		Object[] returnObject = new Object[ array.length + 1];

		for (int i = 0; i < array.length; i++) {
			if(useRefresh)
				returnObject[i] = new Refresh(array[i]);
			else
				returnObject[i] = array[i];
		}
		returnObject[returnObject.length - 1] = object;
		
		
		return returnObject;
	}
}
