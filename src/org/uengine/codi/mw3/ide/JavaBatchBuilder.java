package org.uengine.codi.mw3.ide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.compiler.CompilationProgress;
import org.eclipse.jdt.core.compiler.batch.BatchCompiler;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeError;

public class JavaBatchBuilder {

	List<String> classPath;
		public List<String> getClassPath() {
			return classPath;
		}
		public void setClassPath(List<String> classPath) {
			this.classPath = classPath;
		}
		
	String sourcePath;
		public String getSourcePath() {
			return sourcePath;
		}
		public void setSourcePath(String sourcePath) {
			this.sourcePath = sourcePath;
		}

	String outputPath;
		public List<String> getBuildClass() {
			return buildClass;
		}
		public void setBuildClass(List<String> buildClass) {
			this.buildClass = buildClass;
		}
	
	List<String> buildClass;
		public String getOutputPath() {
			return outputPath;
		}
		public void setOutputPath(String outputPath) {
			this.outputPath = outputPath;
		}
		
	public boolean build(List<JavaCodeError> errorList){
		Exception exception = null;
		boolean success = false;

		try {
			/*
			System.out.println(this.getSourcePath());
			System.out.println("D:/uEngine/codi-was-metaworks/bin/uengine/codebase/uengine.org/jwtest01/src");
			System.out.println("===========");
			System.out.println(this.getOutputPath());
			System.out.println("D:/uEngine/codi-was-metaworks/bin/uengine/codebase/uengine.org/jwtest01/bin");
			System.out.println("===========");
			System.out.println(this.buildClass.get(0));
			System.out.println("D:/uEngine/codi-was-metaworks/bin/uengine/codebase/uengine.org/jwtest01/src/examples/JavaTest.java");
			*/
			String baseCommand[] = new String[] { "-Xemacs",
					// "-noExit", // not necessary for ecj
					"-source", "1.5", "-target", "1.5", "-classpath",
					this.getSourcePath(), "-nowarn", // we're not currently
					// interested in warnings
					// (works in ecj)
					"-d", this.getOutputPath() // output the
					// classes in
					// the buildPath
					,
					this.buildClass.get(0)
			};
			
			final StringBuffer errorBuffer = new StringBuffer();
			Writer internalWriter = new Writer() {
				public void write(char[] buf, int off, int len) {
					errorBuffer.append(buf, off, len);
				}

				public void flush() {
				}

				public void close() {
				}
			};
			PrintWriter writer = new PrintWriter(internalWriter);

			CompilationProgress progress = null;
			PrintWriter outWriter = new PrintWriter(System.out);

			success = BatchCompiler.compile(baseCommand, outWriter, writer, progress);

			// Close out the stream for good measure
			writer.flush();
			writer.close();

			BufferedReader reader = new BufferedReader(new StringReader(
					errorBuffer.toString()));
			// System.err.println(errorBuffer.toString());

			String line = null;
			while ((line = reader.readLine()) != null) {
				String errorFormat = "([\\w\\d_]+.java):(\\d+):\\s*(.*):\\s*(.*)\\s*";
				String[] pieces = JavaBatchBuilder.match(line, errorFormat);
				// PApplet.println(pieces);

				// if it's something unexpected, die and print the mess to the
				// console
				if (pieces == null) {
					exception = new Exception("Cannot parse error text: "
							+ line);

					// Send out the rest of the error message to the console.
					//System.err.println(line);
					while ((line = reader.readLine()) != null) {
						//System.err.println(line);
					}
					break;
				}

				// translate the java filename and line number into a
				// un-preprocessed
				// location inside a source file or tab in the environment.
				String dotJavaFilename = pieces[1];
				// Line numbers are 1-indexed from javac
				int dotJavaLineIndex = JavaBatchBuilder.parseInt(pieces[2]);	// cjw -1
				String errorMessage = pieces[4];

				if (exception == null) {
					exception = new Exception(errorMessage);
				}
				
				JavaCodeError error = new JavaCodeError();
				error.setLineNumber(dotJavaLineIndex);
				error.setMessage(errorMessage);
				error.setType(JavaCodeError.TYPE_ERROR);
				
				errorList.add(error);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}

	static protected HashMap<String, Pattern> matchPatterns;

	static Pattern matchPattern(String regexp) {
		Pattern p = null;
		if (matchPatterns == null) {
			matchPatterns = new HashMap<String, Pattern>();
		} else {
			p = matchPatterns.get(regexp);
		}
		if (p == null) {
			if (matchPatterns.size() == 10) {
				// Just clear out the match patterns here if more than 10 are being
				// used. It's not terribly efficient, but changes that you have >10
				// different match patterns are very slim, unless you're doing
				// something really tricky (like custom match() methods), in which
				// case match() won't be efficient anyway. (And you should just be
				// using your own Java code.) The alternative is using a queue here,
				// but that's a silly amount of work for negligible benefit.
				matchPatterns.clear();
			}
			p = Pattern.compile(regexp, Pattern.MULTILINE | Pattern.DOTALL);
			matchPatterns.put(regexp, p);
		}
		return p;
	}

	static public String[] match(String str, String regexp) {
		Pattern p = matchPattern(regexp);
		Matcher m = p.matcher(str);
		if (m.find()) {
			int count = m.groupCount() + 1;
			String[] groups = new String[count];
			for (int i = 0; i < count; i++) {
				groups[i] = m.group(i);
			}
			return groups;
		}
		return null;
	}

	static final public int parseInt(boolean what) {
		return what ? 1 : 0;
	}

	/**
	 * Note that parseInt() will un-sign a signed byte value.
	 */
	static final public int parseInt(byte what) {
		return what & 0xff;
	}

	/**
	 * Note that parseInt('5') is unlike String in the sense that it
	 * won't return 5, but the ascii value. This is because ((int) someChar)
	 * returns the ascii value, and parseInt() is just longhand for the cast.
	 */
	static final public int parseInt(char what) {
		return what;
	}

	/**
	 * Same as floor(), or an (int) cast.
	 */
	static final public int parseInt(float what) {
		return (int) what;
	}

	/**
	 * Parse a String into an int value. Returns 0 if the value is bad.
	 */
	static final public int parseInt(String what) {
		return parseInt(what, 0);
	}

	/**
	 * Parse a String to an int, and provide an alternate value that
	 * should be used when the number is invalid.
	 */
	static final public int parseInt(String what, int otherwise) {
		try {
			int offset = what.indexOf('.');
			if (offset == -1) {
				return Integer.parseInt(what);
			} else {
				return Integer.parseInt(what.substring(0, offset));
			}
		} catch (NumberFormatException e) { }
		return otherwise;
	}

	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

	static final public int[] parseInt(boolean what[]) {
		int list[] = new int[what.length];
		for (int i = 0; i < what.length; i++) {
			list[i] = what[i] ? 1 : 0;
		}
		return list;
	}

	static final public int[] parseInt(byte what[]) {  // note this unsigns
		int list[] = new int[what.length];
		for (int i = 0; i < what.length; i++) {
			list[i] = (what[i] & 0xff);
		}
		return list;
	}

	static final public int[] parseInt(char what[]) {
		int list[] = new int[what.length];
		for (int i = 0; i < what.length; i++) {
			list[i] = what[i];
		}
		return list;
	}

	static public int[] parseInt(float what[]) {
		int inties[] = new int[what.length];
		for (int i = 0; i < what.length; i++) {
			inties[i] = (int)what[i];
		}
		return inties;
	}

	/**
	 * Make an array of int elements from an array of String objects.
	 * If the String can't be parsed as a number, it will be set to zero.
	 *
	 * String s[] = { "1", "300", "44" };
	 * int numbers[] = parseInt(s);
	 *
	 * numbers will contain { 1, 300, 44 }
	 */
	static public int[] parseInt(String what[]) {
		return parseInt(what, 0);
	}

	/**
	 * Make an array of int elements from an array of String objects.
	 * If the String can't be parsed as a number, its entry in the
	 * array will be set to the value of the "missing" parameter.
	 *
	 * String s[] = { "1", "300", "apple", "44" };
	 * int numbers[] = parseInt(s, 9999);
	 *
	 * numbers will contain { 1, 300, 9999, 44 }
	 */
	static public int[] parseInt(String what[], int missing) {
		int output[] = new int[what.length];
		for (int i = 0; i < what.length; i++) {
			try {
				output[i] = Integer.parseInt(what[i]);
			} catch (NumberFormatException e) {
				output[i] = missing;
			}
		}
		return output;
	}
}
