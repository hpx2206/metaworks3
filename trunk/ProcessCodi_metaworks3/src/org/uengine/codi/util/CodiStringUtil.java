package org.uengine.codi.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodiStringUtil {

	public static String firstUpperCase(String value){
		return (new StringBuilder()).append(Character.toUpperCase(value.charAt(0))).append(value.substring(1)).toString();
	}
	 
	public static boolean isValidEmail(String email) {
        Pattern p = Pattern.compile("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
	
	public static String lastLastFileSeparatorChar(String value){
		if(value.charAt(value.length()-1) == File.separatorChar)
			value += File.separatorChar;
		
		return value;
	}
}
