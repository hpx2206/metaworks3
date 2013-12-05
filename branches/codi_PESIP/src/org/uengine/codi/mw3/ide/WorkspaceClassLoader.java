package org.uengine.codi.mw3.ide;

import java.io.File;

import org.codehaus.commons.compiler.AbstractJavaSourceClassLoader;

public class WorkspaceClassLoader extends AbstractJavaSourceClassLoader {

    private File[]             sourcePath;
    private String             optionalCharacterEncoding;
    private boolean            debuggingInfoLines;
    private boolean            debuggingInfoVars;
    private boolean            debuggingInfoSource;
    
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

}
