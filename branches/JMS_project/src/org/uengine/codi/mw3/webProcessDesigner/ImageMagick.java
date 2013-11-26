package org.uengine.codi.mw3.webProcessDesigner;

public class ImageMagick {

	public void convertFile(String fromFile, String toFile){
		  
		try {
			String cmd = "c:/ImageMagick-6.8.7-Q16/convert.exe -stroke white -font Malgun-Gothic " + fromFile + " " + toFile;
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
            System.out.println(p.exitValue());
            p.destroy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
