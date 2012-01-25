
/*
 * Janino - An embedded Java[TM] compiler
 *
 * Copyright (c) 2001-2010, Arno Unkrig
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of conditions and the
 *       following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 *       following disclaimer in the documentation and/or other materials provided with the distribution.
 *    3. The name of the author may not be used to endorse or promote products derived from this software without
 *       specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.uengine.codi.mw3;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.tools.*;
import javax.tools.JavaFileManager.Location;
import javax.tools.JavaFileObject.Kind;

import org.codehaus.commons.compiler.Cookable;
import org.directwebremoting.ServerContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

/**
 * A {@link ForwardingJavaFileManager} that maps accesses to a particular {@link Location} and {@link Kind} to
 * a path-based search in the file system.
 */
final class FileInputJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    private final Location location;
    private final Kind     kind;
    private final File[]   path;
    private final String   optionalCharacterEncoding;

    /**
     * @param path                      List of directories to look through
     * @param optionalCharacterEncoding Encoding of the files being read
     */
    FileInputJavaFileManager(
        JavaFileManager delegate,
        Location        location,
        Kind            kind,
        File[]          path,
        String          optionalCharacterEncoding
    ) {
        super(delegate);
        this.location                  = location;
        this.kind                      = kind;
        this.path                      = path;
        this.optionalCharacterEncoding = optionalCharacterEncoding;
    }

//    @Override
//    public Iterable<JavaFileObject> list(
//        Location  location,
//        String    packageName,
//        Set<Kind> kinds,
//        boolean   recurse
//    ) throws IOException {
//
//        if (location == this.location && kinds.contains(this.kind)) {
//            Collection<JavaFileObject> result = new ArrayList<JavaFileObject>();
//
//            String rel = packageName.replace('.', File.separatorChar);
//            for (File directory : this.path) {
//                File packageDirectory = new File(directory, rel);
//                result.addAll(list(
//                    packageDirectory,
//                    packageName.isEmpty() ? "" : packageName + ".",
//                    this.kind,
//                    recurse
//                ));
//            }
//            return result;
//        }
//
//        return super.list(location, packageName, kinds, recurse);
//    }


    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        if (location == this.location) {
            return ((InputFileJavaFileObject) file).getBinaryName();
        }
        return super.inferBinaryName(location, file);
    }

    @Override
    public boolean hasLocation(Location location) {
        return location == this.location || super.hasLocation(location);
    }

    @Override
    public JavaFileObject getJavaFileForInput(
        Location location,
        String   className,
        Kind     kind
    ) throws IOException {
        if (location == this.location && kind == this.kind) {

//            // Find the source file through the source path.
//            final File sourceFile;
//            FIND_SOURCE: {
//                String rel = className.replace('.', File.separatorChar) + kind.extension;
//                for (File sourceDirectory : this.path) {
//                    File f = new File(sourceDirectory, rel);
//                    if (f.exists()) {
//                        sourceFile = f.getCanonicalFile();
//                        break FIND_SOURCE;
//                    }
//                }
//                return null;
//            }
//
//            // Create and return a JavaFileObject.
//            return new InputFileJavaFileObject(sourceFile, className);
        	ServletContext srvCtx = ServerContextFactory.get().getServletContext();
        	ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(srvCtx);
        	ProcessManagerRemote processManager = (ProcessManagerRemote) appContext.getBean("processManagerBean");
        	ClassDefinition classDef;
        	
			try {
				
				String resourceName = className.replace('.', '/') + ".java";
				
				String defVerId = processManager.getProcessDefinitionProductionVersionByAlias(resourceName);
				String classDefinition = processManager.getResource(defVerId);
				classDef = (ClassDefinition) GlobalContext.deserialize(classDefinition, ClassDefinition.class);
				
				//TODO: should be changed to use ForReadOnly for performance enhancement
//				try{
//					processManager.applyChanges();
//				}catch(Exception ex){
//					
//				}
				
            } catch (Exception e) {
            	
	        	try{
					processManager.cancelChanges();
				}catch(Exception ex){
					
				}

	        	throw new IOException("due to: " + e.getMessage());
            } finally{
				try{
					processManager.remove();
				}catch(Exception ex){
					
				}
			}

        	String sourceCode = classDef.getSourceCode().getCode();
			
        	return new InputFileJavaFileObject(className, sourceCode);
        }

        return super.getJavaFileForInput(location, className, kind);
    }

   	static URI uriFromString(String uri) {
		try {
		    return new URI(uri);
		} catch (URISyntaxException e) {
		    throw new IllegalArgumentException(e);
		}
	}

    /**
     * A {@link JavaFileObject} that reads from a {@link File}.
     */
    private class InputFileJavaFileObject extends SimpleJavaFileObject {

    	String sourceCode;
    	String binaryName;
    	
        public InputFileJavaFileObject(String className, String sourceCode) {
        	
            super(uriFromString("mfm:///" + className.replace('.','/') + Kind.SOURCE.extension), FileInputJavaFileManager.this.kind);
            
            this.sourceCode = sourceCode;
            this.binaryName = className;
        }
        
 
        @Override
        public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
            return (
            		new StringReader(sourceCode)
            		
//                FileInputJavaFileManager.this.optionalCharacterEncoding == null
//                ? new FileReader(this.file)
//                : new InputStreamReader(
//                    new FileInputStream(this.file),
//                    FileInputJavaFileManager.this.optionalCharacterEncoding
//                )
            );
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            Reader r = this.openReader(true);
            try {
                return Cookable.readString(r);
            } finally {
                r.close();
            }
        }
        
        String getBinaryName() {
            return this.binaryName;
        }
    }
}
