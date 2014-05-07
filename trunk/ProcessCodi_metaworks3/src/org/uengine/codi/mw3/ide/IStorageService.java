package org.uengine.codi.mw3.ide;

import java.io.IOException;

public interface IStorageService {
	public boolean putObject(String projectId, String projectName, boolean isProd) throws IOException;
}
