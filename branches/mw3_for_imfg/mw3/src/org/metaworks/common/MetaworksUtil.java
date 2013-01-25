package org.metaworks.common;

import java.io.InputStream;
import java.io.OutputStream;

public class MetaworksUtil {

	static public void copyStream(InputStream sourceInputStream, OutputStream targetOutputStream) throws Exception{
		int length = 1024;
		byte[] bytes = new byte[length]; 
		int c; 
		int total_bytes=0;
			
		while ((c = sourceInputStream.read(bytes)) != -1) { 
				total_bytes +=c; 
				targetOutputStream.write(bytes,0,c); 
		} 
		
		if (sourceInputStream != null) try { sourceInputStream.close(); } catch (Exception e) {}
		if (targetOutputStream != null) try { targetOutputStream.close(); } catch (Exception e) {}
	}	
}
