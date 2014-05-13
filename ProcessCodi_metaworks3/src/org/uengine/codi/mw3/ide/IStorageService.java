package org.uengine.codi.mw3.ide;

import java.io.IOException;

public interface IStorageService {
	public boolean putObject(String projectId, String projectName, String version, boolean isProd) throws IOException;
}
