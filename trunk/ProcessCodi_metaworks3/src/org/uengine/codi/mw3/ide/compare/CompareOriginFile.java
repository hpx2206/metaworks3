package org.uengine.codi.mw3.ide.compare;

public class CompareOriginFile extends CompareFile {

	public CompareOriginFile() throws Exception{
		setViewType("definitionDiffEdit");
		setEditorId(CompareOriginFilePanel.FILE_LOCATION);
	}
}
