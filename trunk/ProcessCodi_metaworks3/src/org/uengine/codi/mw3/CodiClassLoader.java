package org.uengine.codi.mw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.apache.tools.ant.filters.StringInputStream;
import org.codehaus.commons.compiler.AbstractJavaSourceClassLoader;
import org.codehaus.commons.compiler.ICompilerFactory;
import org.codehaus.commons.compiler.jdk.ByteArrayJavaFileManager.ByteArrayJavaFileObject;
import org.codehaus.commons.compiler.jdk.JavaSourceClassLoader.DiagnosticException;
import org.codehaus.commons.compiler.jdk.ByteArrayJavaFileManager;
import org.codehaus.commons.compiler.jdk.JavaSourceClassLoader;
import org.directwebremoting.ServerContextFactory;
import org.metaworks.dao.TransactionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

import com.sun.xml.bind.v2.runtime.Name;

public class CodiClassLoader extends AbstractJavaSourceClassLoader {

    private File[]             sourcePath;
    private String             optionalCharacterEncoding;
    private boolean            debuggingInfoLines;
    private boolean            debuggingInfoVars;
    private boolean            debuggingInfoSource;
    private Collection<String> compilerOptions = new ArrayList<String>();

    private JavaCompiler    compiler;
    private JavaFileManager fileManager;

    /**
     * @see ICompilerFactory#newJavaSourceClassLoader()
     */
    public CodiClassLoader() {
        this.init();
    }

    /**
     * @see ICompilerFactory#newJavaSourceClassLoader(ClassLoader)
     */
    public CodiClassLoader(ClassLoader parentClassLoader) {
        super(parentClassLoader);
        this.init();
    }

    
	public static String sourceCodeBase(){
		
		String userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");
		
		return "/Users/jyjang/codebase/"+ userId + "/src/";
	}
    
	@Override
	public InputStream getResourceAsStream(String name) {
		
		String firstSourcePath = sourcePath[0].getPath();

		if(name.endsWith(".ejs") || name.endsWith(".ejs.js") || name.endsWith("xml")){
			try {
				FileInputStream fis = new FileInputStream(firstSourcePath + name);
				return fis;
			} catch (FileNotFoundException e) {
			}
			
		}

		return super.getResourceAsStream(name);
	}
	
	

//	@Override
//	protected synchronized Class<?> loadClass(String name,
//			boolean resolve) throws ClassNotFoundException {
//
//		if(securedClasses.containsKey(name)){
//           throw new ClassNotFoundException(securedClasses.get(name), new SecurityException());
//		}
//		
//		return super.loadClass(name, resolve);
//	}
//
//
//
//	protected Class<?> findClass(String className)
//			throws ClassNotFoundException {
//
//		if(securedClasses.containsKey(className)){
//			throw new RuntimeException(securedClasses.get(className));
//		}
//		
//		return super.findClass(className);
//	}
	
	
	
	
	//----------------------------------------     from this line, the codes are just copied from JavaSourceClassLoader.java in janino ---------------------------//
	
	

	
	
	
	
	
	
    private void init() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
        if (this.compiler == null) {
            throw new UnsupportedOperationException(
                "JDK Java compiler not available - probably you're running a JRE, not a JDK"
            );
        }
    }

    /**
     * Creates the underlying {@link JavaFileManager} lazily, because {@link #setSourcePath(File[])} and consorts
     * are called <i>after</i> initialization.
     */
    public JavaFileManager getJavaFileManager() {
        if (this.fileManager == null) {

            // Get the original FM, which reads class files through this JVM's BOOTCLASSPATH and
            // CLASSPATH.
            JavaFileManager jfm = this.compiler.getStandardFileManager(null, null, null);
    
            // Wrap it so that the output files (in our case class files) are stored in memory rather
            // than in files.
            jfm = new ByteArrayJavaFileManager<JavaFileManager>(jfm);
    
            // Wrap it in a file manager that finds source files through the source path.
            jfm = new FileInputJavaFileManager(
                jfm,
                StandardLocation.SOURCE_PATH,
                Kind.SOURCE,
                this.sourcePath,
                this.optionalCharacterEncoding
            );
    
            this.fileManager = jfm;
        }
        return this.fileManager;
    }

    @Override
    public void setSourcePath(File[] sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public void setSourceFileCharacterEncoding(String optionalCharacterEncoding) {
        this.optionalCharacterEncoding = optionalCharacterEncoding;
    }

    @Override
    public void setDebuggingInfo(boolean lines, boolean vars, boolean source) {
        this.debuggingInfoLines  = lines;
        this.debuggingInfoVars   = vars;
        this.debuggingInfoSource = source;
    }

    /**
     * Notice: Don't use the '-g' options - these are controlled through {@link #setDebuggingInfo(boolean, boolean,
     * boolean)}.
     *
     * @param compilerOptions All command line options supported by the JDK JAVAC tool
     */
    public void setCompilerOptions(String[] compilerOptions) {
        this.compilerOptions = Arrays.asList(compilerOptions);
    }

    /**
     * Implementation of {@link ClassLoader#findClass(String)}.
     *
     * @throws ClassNotFoundException
     */
    protected Class<?> findClass(String className) throws ClassNotFoundException {

        byte[] ba;
        int    size;
        try {

            // Maybe the bytecode is already there, because the class was compiled as a side effect of a preceding
            // compilation.
            JavaFileObject classFileObject = this.getJavaFileManager().getJavaFileForInput(
                StandardLocation.CLASS_OUTPUT,
                className,
                Kind.CLASS
            );

            if (classFileObject == null) {

                // Get the sourceFile.
                JavaFileObject sourceFileObject = this.getJavaFileManager().getJavaFileForInput(
                    StandardLocation.SOURCE_PATH,
                    className,
                    Kind.SOURCE
                );
                if (sourceFileObject == null) {
                    throw new DiagnosticException("Source for '" + className + "' not found");
                }

                // Compose the effective compiler options.
                List<String> options = new ArrayList<String>(this.compilerOptions);
                options.add(this.debuggingInfoLines ? (
                    this.debuggingInfoSource ? (
                        this.debuggingInfoVars
                        ? "-g"
                        : "-g:lines,source"
                    ) : this.debuggingInfoVars ? "-g:lines,vars" : "-g:lines"
                ) : this.debuggingInfoSource ? (
                    this.debuggingInfoVars
                    ? "-g:source,vars"
                    : "-g:source"
                ) : this.debuggingInfoVars ? "-g:vars" : "-g:none");

              //  this.compiler
                
                // Run the compiler.
                if (!this.compiler.getTask(
                    null,                                   // out
                    this.getJavaFileManager(),              // fileManager
                    new DiagnosticListener<JavaFileObject>() { // diagnosticListener

                        @Override
                        public void report(final Diagnostic<? extends JavaFileObject> diagnostic) {
                            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                                throw new DiagnosticException(diagnostic);
                            }
                        }
                    },
                    options,                                // options
                    null,                                   // classes
                    Collections.singleton(sourceFileObject) // compilationUnits
                ).call()) {
                    throw new ClassNotFoundException(className + ": Compilation failed");
                }

                classFileObject = this.getJavaFileManager().getJavaFileForInput(
                    StandardLocation.CLASS_OUTPUT,
                    className,
                    Kind.CLASS
                );

                if (classFileObject == null) {
                    throw new ClassNotFoundException(className + ": Class file not created by compilation");
                }
            }

            if (classFileObject instanceof ByteArrayJavaFileObject) {
                ByteArrayJavaFileObject bajfo = (ByteArrayJavaFileObject) classFileObject;
                ba = bajfo.toByteArray();
                size = ba.length;
            } else
            {
                ba = new byte[4096];
                size = 0;
                InputStream is = classFileObject.openInputStream();
                try {
                    for (;;) {
                        int res = is.read(ba, size, ba.length - size);
                        if (res == -1) break;
                        size += res;
                        if (size == ba.length) {
                            byte[] tmp = new byte[2 * size];
                            System.arraycopy(ba, 0, tmp, 0, size);
                            ba = tmp;
                        }
                    }
                } finally {
                    is.close();
                }
            }
        } catch (IOException ioe) {
            throw new DiagnosticException(ioe);
        }

        return this.defineClass(className, ba, 0, size, (
            this.optionalProtectionDomainFactory == null
            ? null
            : this.optionalProtectionDomainFactory.getProtectionDomain(getSourceResourceName(className))
        ));
    }

    /**
     * Construct the name of a resource that could contain the source code of
     * the class with the given name.
     * <p>
     * Notice that member types are declared inside a different type, so the relevant source file
     * is that of the outermost declaring class.
     *
     * @param className Fully qualified class name, e.g. "pkg1.pkg2.Outer$Inner"
     * @return the name of the resource, e.g. "pkg1/pkg2/Outer.java"
     */
    private static String getSourceResourceName(String className) {

        // Strip nested type suffixes.
        {
            int idx = className.lastIndexOf('.') + 1;
            idx = className.indexOf('$', idx);
            if (idx != -1) className = className.substring(0, idx);
        }

        return className.replace('.', '/') + ".java";
    }

    public static class DiagnosticException extends RuntimeException {

        private static final long serialVersionUID = 5589635876875819926L;

        public DiagnosticException(String message) {
            super(message);
        }

        public DiagnosticException(Throwable cause) {
            super(cause);
        }

        public DiagnosticException(Diagnostic<? extends JavaFileObject> diagnostic) {
            super(diagnostic.toString());
        }
    }
    
    
	
}