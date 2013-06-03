package org.uengine.codi.mw3.ide;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.uengine.kernel.GlobalContext;

public class MetadataBundle {
	
	// HashMap<프로젝트 아이디, 해당프로젝트의 메타데이타 파일 경로>
	public static HashMap<String, String> uengineMetadata = new HashMap<String, String>();
	// HashMap<프로젝트 아이디, Properties>
	public static HashMap<String, Properties> projectProperty = new HashMap<String, Properties>();
	
	public MetadataBundle(){
		this(null);
	}
	public MetadataBundle(String projectId){
		// 1. 앱이 처음 만들어 졌을때 xml 데이터를 쓰고, 파일등의 리소스를 모두 다운받는다...
		// 2. 앱이 구동될때, 리소스들을 모두 메모리에 담아 놓는다.. ( MetadataBundle 은 각 앱에서 1회 호출된다 )
		// 3. 로컬 경로에서 모두 리소스를 가져온다. - 
		// 4. TODO 추후에 - 변경로직 체크 ,  스케쥴링을 걸어서 변경된 부분을 담는 작업..
		
		try {
			File mainFile = findMetadataFile( projectId );
			if(!mainFile.exists()){
				throw new Exception("can not find file");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	
	private File findMetadataFile(String projectId) throws Exception{
		String codebase = GlobalContext.getPropertyString("codebase", "codebase");
		String mainPath = codebase + File.separatorChar + "main";
		String mainFileName = "uengine.metadata";	// TODO
		
		File mainFile = new File(mainPath + File.separatorChar + mainFileName);
		return mainFile;
	}
	
	public Properties loadProjectProperty(String projectId) throws Exception{
		String companyId = null;
		
		// TODO metadataPath 의 xml 파일을 읽어서 
		// 프로퍼티에 상위 키값을 모두 찾아서 로딩시킨다.
		Properties props = new Properties();
		
		// 로컬에 있는 리소스를 보고 판단하기...
		// xml 에 type 이 local , remote 로 나누어진다... 
		
		// 1. xml에서 데이터를 읽었을때, 로컬 경로를 찾을때..
		// 2. 또다른 메타데이터를 참조하여서 상위 프로젝트를 찾아야 할 경우..
		/*
		 * 상위 프로젝트 아이디나 어떤 값을 가지고 있어야한다.... 
		 * 상위 프로젝트는 어떻게 찾아가야하나???
		 */
		
		return props;
	}
	
	/**
	 * 메타데이타파일이 변경되었을때, 이 메서드를 호출해서 프로퍼티값을 변경시켜놓는다.
	 * @param projectId
	 * @throws Exception
	 */
	public void changeProjectProperty(String projectId) throws Exception{
		// TODO
		// type : local 은 
	}
}
