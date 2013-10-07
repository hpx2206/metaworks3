package org.uengine.codi.util;

public class CodiStringUtil {

	public static String firstUpperCase(String value){
		return (new StringBuilder()).append(Character.toUpperCase(value.charAt(0))).append(value.substring(1)).toString();
	}
	 
}
