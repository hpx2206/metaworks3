package org.uengine.codi.util;

import java.io.File;

public class CodiFileUtil {

	public static boolean mkdirs(String value){
		File file = new File(value);
		if(!file.exists())
			file.mkdirs();
		
		return true;
	}
	 
}
