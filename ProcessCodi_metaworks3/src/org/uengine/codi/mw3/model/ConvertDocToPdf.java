package org.uengine.codi.mw3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class ConvertDocToPdf {
	
	
	public boolean convertPdf(String inputFilePath, String outputFilePath) throws FileNotFoundException, IOException, Exception {
		
		boolean isConvert = false;
		
		File inputFile = new File(inputFilePath);
		File outputFile = new File(outputFilePath);
		 
		// connect to an OpenOffice.org instance running on port 8100
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);

		try {
			connection.connect();
			
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
			
			isConvert = true;
		} catch (ConnectException e) {
			e.printStackTrace();
		}finally{
			connection.disconnect();
		}
		
		return isConvert;
	}

}
