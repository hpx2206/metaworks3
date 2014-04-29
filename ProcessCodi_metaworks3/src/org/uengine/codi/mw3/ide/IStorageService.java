package org.uengine.codi.mw3.ide;

import java.io.IOException;

public interface IStorageService {
	public void putObject(String projectId, String projectName, boolean isProd) throws IOException;
}
