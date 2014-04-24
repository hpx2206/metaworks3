package org.uengine.codi.mw3.ide.compare;

public class CompareImportFile extends CompareFile{

	public CompareImportFile()  throws Exception{
		setViewType("definitionDiffView");
		setEditorId(CompareImportFilePanel.FILE_LOCATION);
	}
}
