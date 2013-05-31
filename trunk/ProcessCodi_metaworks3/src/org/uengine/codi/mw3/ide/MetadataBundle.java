package org.uengine.codi.mw3.ide;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.uengine.kernel.GlobalContext;

public class MetadataBundle {
	
	static String CODI_BASE_ID = "CODI";
	
	// HashMap<프로젝트 아이디, 해당프로젝트의 메타데이타 파일 경로>
	public static HashMap<String, String> uengineMetadata = new HashMap<String, String>();
	// HashMap<프로젝트 아이디, Properties>
	public static HashMap<String, Properties> projectProperty = new HashMap<String, Properties>();
	
	public MetadataBundle(){
		// TODO 유엔진 메타데이터를 찾아서 uengineMetadata 에 담아놓는다.
		String codebase = GlobalContext.getPropertyString("codebase", "codebase");
		String mainPath = codebase + File.separatorChar + "main";
		String mainFileName = "uengine.metadata";	// TODO
		File mainFile = new File(mainPath + File.separatorChar + mainFileName);
		if(!mainFile.exists()){
			try {
				mainFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Object getMetadataBundel(String projectId, String key) throws Exception{
		// TODO 키값에 해당하는 번들을 찾고, key값에 맞는 값을 리턴해준다.
		Properties props = null;
		if( projectProperty.containsKey(projectId) ){
			props = projectProperty.get(projectId);
		}else{
			props = loadProjectProperty(projectId);
			projectProperty.put(projectId, props);
		}
		
		if( props != null ){
			// TODO url, string 으로 넘길지 분리하는 로직이 필요함
			return props.get(key);
		}else{
			return null;
		}
	}
	
	public Properties loadProjectProperty(String projectId) throws Exception{
		String companyId = null;
		
		String codiMetadataPath = uengineMetadata.get(CODI_BASE_ID);
		String companyMetadataPath = uengineMetadata.get(companyId);
		String projectMetadataPath = uengineMetadata.get(projectId);
		// TODO metadataPath 의 xml 파일을 읽어서 
		// 프로퍼티에 상위 키값을 모두 찾아서 로딩시킨다.
		Properties props = new Properties();
		
		// 1. 코디쪽 메타데이터를 읽어서 프로퍼티에 쓴다
		// 2. 테넌트 메인 메타데이터를 읽어서 프로퍼티에 쓴다(같은 키값 오버라이딩)
		// 3. 프로젝트 메타데이터를 읽어서 프로퍼티에 쓴다(같은 키값 오버라이딩)
		
		return props;
	}
	
	/**
	 * 메타데이타파일이 변경되었을때, 이 메서드를 호출해서 프로퍼티값을 변경시켜놓는다.
	 * @param projectId
	 * @throws Exception
	 */
	public void changeProjectProperty(String projectId) throws Exception{
		// TODO
	}
}
