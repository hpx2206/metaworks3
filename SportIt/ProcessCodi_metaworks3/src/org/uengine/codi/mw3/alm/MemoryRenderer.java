package org.uengine.codi.mw3.alm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.pmd.IRuleViolation;
import net.sourceforge.pmd.renderers.OnTheFlyRenderer;

public class MemoryRenderer extends OnTheFlyRenderer{

	static public List<Iterator<IRuleViolation>> recentViolations;
	
	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		recentViolations = new ArrayList<Iterator<IRuleViolation>>();
	}

	@Override
	public void renderFileViolations(Iterator<IRuleViolation> violations)
			throws IOException {
		// TODO Auto-generated method stub
//		System.out.println(violations);
		recentViolations.add(violations);
	}

	@Override
	public void end() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
