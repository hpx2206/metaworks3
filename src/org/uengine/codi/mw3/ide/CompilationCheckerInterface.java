package org.uengine.codi.mw3.ide;

import java.util.Map;

import org.eclipse.jdt.core.compiler.IProblem;

public interface CompilationCheckerInterface {
	public IProblem[] getErrors(String sourceName, String source);

	/**
	 * Compiles source String treating it as a java class with name sourceName,
	 * and settings as Compiler Options(JDT)
	 * 
	 * @param sourceName
	 * @param source
	 * @param settings
	 * @return IProblems[] - Errors and warning reported by the compiler 
	 */
	public IProblem[] getErrors(String sourceName, String source, Map settings);

	public void display();
}
