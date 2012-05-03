package com.defaultcompany.aes;

import java.net.URLDecoder;
import java.net.URLEncoder;




public class RoboEncryptor {


	private final static String EncKeyString = "PROCROBO!KEY";
	private final static String EncInitVector = "PROCROBO!VECTOR";

	StringEncrypter se = null;

	public RoboEncryptor() throws Exception
	{
		se = new StringEncrypter(EncKeyString, EncInitVector);
	}
	

	public String encrypt(String value) throws Exception
	{
		return se.encrypt(value);
	}


	public String decrypt(String value) throws Exception
	{
		return se.decrypt(value);
	}
	
	public static void main(String[] args) throws Exception {
		RoboEncryptor re = new RoboEncryptor();
		System.out.println(re.decrypt("G+EbspwdHjmyqbtYYmR7lA=="));
		System.out.println(re.decrypt("m4oIS41ZCz5jBh0QxjuhZg=="));
		
	}
}
