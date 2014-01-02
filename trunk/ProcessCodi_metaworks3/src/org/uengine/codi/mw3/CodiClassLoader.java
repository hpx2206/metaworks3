package org.uengine.codi.mw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.codehaus.commons.compiler.AbstractJavaSourceClassLoader;
import org.codehaus.commons.compiler.ICompilerFactory;
import org.codehaus.commons.compiler.jdk.ByteArrayJavaFileManager;
import org.codehaus.commons.compiler.jdk.ByteArrayJavaFileManager.ByteArrayJavaFileObject;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.metadata.MetadataBundle;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.kernel.GlobalContext;

public class CodiClassLoader extends AbstractJavaSourceClassLoader {

	public final static String DEFAULT_NAME = "root";
	public final static String PATH_SEPARATOR = "/";
	
	String						codebase;

	private File[]             sourcePath;
	private String             optionalCharacterEncoding;
	private boolean            debuggingInfoLines;
	private boolean            debuggingInfoVars;
	private boolean            debuggingInfoSource;
	private Collection<String> compilerOptions = new ArrayList<String>();

	private JavaCompiler    compiler;
	private JavaFileManager fileManager;

	public static CodiClassLoader codiClassLoader; //works for default classloader. 


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

	public String getCodebase() {
		return codebase;
	}
	public void setCodebase(String codebase) {
		this.codebase = codebase;
	}

	public static CodiClassLoader getMyClassLoader(){
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

		return (CodiClassLoader) contextClassLoader;
	}

	/*
	public String sourceCodeBase(){

		//TODO. setting a collect sourcePath 

		//if(sourcePath!=null)
		//	return sourcePath[0].getPath();

		String mySourceCodeBase = mySourceCodeBase();

		if(mySourceCodeBase!=null){

			File wcDir = new File(mySourceCodeBase).getParentFile(); //project folder is one level parent folder than 'src'

			if (wcDir.exists()) {
				setSourcePath(new File[]{new File(mySourceCodeBase)});

				return mySourceCodeBase;
			}
		}

		String userId = null;
		try{
			userId = (String) TransactionContext.getThreadLocalInstance().getRequest().getSession().getAttribute("userId");
		}catch(Exception e){

		}

		//if(userId==null)
		userId = "main";

		String codebaseRoot = getCodeBaseRoot();

		String dir = codebaseRoot + userId;


		File f = new File(dir);
		if(!f.exists()) //f.mkdirs();
			dir = codebaseRoot + "main";

		// 1. 메타데이터가 있는 경우 우선적으로 메타데이터 path 를 보도록 한다.
		String firstSourcePath = null;
		if(MetadataBundle.projectBundle != null){
			firstSourcePath = MetadataBundle.projectBundle.getProperty("sourceCodePath");
			setSourcePath(new File[]{new File(firstSourcePath)});
		}
		if( firstSourcePath != null ){
			File[] sourcePath2 = new File[sourcePath.length+1];
			System.arraycopy(sourcePath, 0, sourcePath2, 0, sourcePath.length);
			sourcePath2[sourcePath.length] = new File(dir + "/src/");
			sourcePath = sourcePath2;
		}else{
			setSourcePath(new File[]{new File(dir + "/src/")});
		}

		return sourcePath[0].getPath();
	}
	*/

	public static String getCodeBaseRoot() {
		String coderoot = GlobalContext.getPropertyString("codebase", "codebase/");

		if(!coderoot.endsWith("/")) coderoot=coderoot+"/";

		return coderoot;
	}

	@Override
	public InputStream getResourceAsStream(String name) {

		if(name != null){
			//if(name.endsWith(".ejs") || name.endsWith(".ejs.js") || name.endsWith(".xml") || name.endsWith(".process") || name.endsWith(".process2") || name.endsWith(".sql") || name.endsWith(".wpd") || name.endsWith("metadata")){

			for(File file : this.sourcePath){
				try {
					if(file.exists()){
						FileInputStream fis = new FileInputStream(file.getAbsolutePath() + "/" + name);
						return fis;
					}
				} catch (FileNotFoundException e) {
				}				
			}
			//}
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

		//TODO: it's too complicated to use. so we switch to use HotSwapper on behalf of this

		//    	// setting security filters for more fine-grained control
		//    	try {
		//    		pool = ClassPool.getDefault();
		//    		
		//    		pool.appendClassPath("/Users/jyjang/Documents/workspace/ProcessCodi_metaworks3/WebContent/WEB-INF/mongo-2.7.2.jar");
		//    		
		//    		CtClass cc = pool.get("com.mongodb.Mongo");
		//    		
		//    		CtConstructor cm = cc.getDeclaredConstructor(new CtClass[]{});
		//    		
		//    		cm.instrument(
		//    		    new ExprEditor() {
		//    		    	boolean checked = false;
		//    		    	
		//    		        public void edit(MethodCall m)
		//    		                      throws CannotCompileException
		//    		        {
		//    		        	
		//    		        	if(!checked){
		//    		                m.replace("{ System.out.println(\"how many test\"); throw new SecurityException(\"platform denied you\"); $_ = $proceed($$); }");
		//    		             
		//    		                checked = true;
		//    		        	}
		//    		        }
		//    		    });
		//    		
		////    	    Loader cl = new Loader(pool);
		////    	    
		////    	    SampleLoader loader = new SampleLoader(pool);
		////    	    loader.findClass("java.io.File");
		//    	}catch(Exception e){
		//    		throw new RuntimeException(e);
		//    	}
		//    	

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


		//TODO: it looks bad so, we switch to use HotSwapper in the javassist

		//    	if(className.equals("com.mongodb.Mongo")){
		//            try {
		//                CtClass cc = pool.get(className);
		//                // modify the CtClass object here
		//                byte[] b = cc.toBytecode();
		//                return defineClass(className, b, 0, b.length);
		//            } catch (NotFoundException e) {
		//                //throw new ClassNotFoundException();
		//            } catch (IOException e) {
		//                //throw new ClassNotFoundException();
		//            } catch (CannotCompileException e) {
		//                //throw new ClassNotFoundException();
		//            }
		//
		//    	}



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
					//                   throw new DiagnosticException("Source for '" + className + "' not found");
					throw new ClassNotFoundException("Source for '" + className + "' not found");

					//return null;
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

	public static CodiClassLoader createClassLoader(String projectId, String tenantId, boolean useDefault){

		CodiClassLoader cl = new CodiClassLoader(CodiMetaworksRemoteService.class.getClassLoader());

		String sep = System.getProperty("os.name").toLowerCase().indexOf("win") >= 0 ? ";" : ":";

		if( CodiMetaworksRemoteService.class.getClassLoader() instanceof URLClassLoader ){

			URLClassLoader classLoader = (URLClassLoader) CodiMetaworksRemoteService.class.getClassLoader();
			URL urls[] = classLoader.getURLs();
			StringBuffer sbClasspath = new StringBuffer();
			for(URL url : urls){
				String urlStr = url.getFile().toString();
				sbClasspath.append(urlStr).append(sep);
				//needed for javassist version of metaworks2
				//			try {
				//				ObjectType.classPool.insertClassPath(urlStr);
				//			} catch (NotFoundException e) {
				//				// TODO Auto-generated catch block
				//				e.printStackTrace();
				//			}
				//end of needed for
			}

			cl.setCompilerOptions(
					new String[]{
							"-classpath", sbClasspath.toString()
					});
		}
		
		//Loads user-defined library files


		//ClassPool setting
		if(projectId == null)
			projectId = MetadataBundle.getProjectId();

		List<File> sourcePath = new ArrayList<File>();

		String codeBaseRoot = CodiClassLoader.getCodeBaseRoot();
		String projectRoot = codeBaseRoot + projectId + PATH_SEPARATOR;
		String projectDefaultPath = projectRoot + DEFAULT_NAME + PATH_SEPARATOR;
		
		// high tenant cobe base
		if(!useDefault){
			String projectTenantPath = projectRoot + tenantId + PATH_SEPARATOR;
			
			sourcePath.add(new File(projectTenantPath));
		}
		
		sourcePath.add(new File(projectDefaultPath));

		cl.setCodebase(sourcePath.get(0).getAbsolutePath());
		cl.setSourcePath(sourcePath.toArray(new File[sourcePath.size()]));

		return cl;
	}  

	public static void refreshClassLoader(String resourceName){

		try {
			MetaworksRemoteService.getInstance().clearMetaworksType(resourceName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		String sourceCodeBase = null;

		if(TransactionContext.getThreadLocalInstance()!=null && TransactionContext.getThreadLocalInstance().getRequest()!=null){
			HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession();
			if(session!=null){
				sourceCodeBase = (String) session.getAttribute("sourceCodeBase");
			}
		}

		//TODO: looks sourceCodeBase is not required
		CodiClassLoader cl = CodiClassLoader.createClassLoader(sourceCodeBase);

		Thread.currentThread().setContextClassLoader(cl);
		codiClassLoader = cl;
		 */
	}

	public static void initClassLoader(){
		if(codiClassLoader==null)
			refreshClassLoader(null);

		Thread.currentThread().setContextClassLoader(codiClassLoader);
	}

}