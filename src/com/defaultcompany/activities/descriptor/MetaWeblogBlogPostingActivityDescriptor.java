package com.defaultcompany.activities.descriptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.FieldDescriptor;
import org.metaworks.inputter.Inputter;
import org.metaworks.inputter.SelectInput;
import com.defaultcompany.activities.MetaWeblogAuth;
import org.uengine.kernel.Activity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.descriptor.ActivityDescriptor;
import org.uengine.processdesigner.ProcessDesigner;
import org.uengine.util.UEngineUtil;

public class MetaWeblogBlogPostingActivityDescriptor extends ActivityDescriptor {
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	static Map<String, String> apiUrl = new HashMap<String, String>();
	static {
		apiUrl.put("Naver", "https://api.blog.naver.com/xmlrpc");
	}
	
	private Inputter authInputter = null;
	private Inputter categoriesInputter = null;
	private Inputter blogTypeInputter = null;

	public MetaWeblogBlogPostingActivityDescriptor() throws Exception {
		super();
	}

	@Override
	public void initialize(ProcessDesigner value, Activity activity) {
		super.initialize(value, activity);
		
		FieldDescriptor categoriesFd = getFieldDescriptor("Categories");
		categoriesInputter = categoriesFd.getInputter();
		
		FieldDescriptor authFd = getFieldDescriptor("Auth");
		authInputter = authFd.getInputter();
		authInputter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (authInputter != null) {
					MetaWeblogAuth metaWeblogAuth = (MetaWeblogAuth) authInputter.getValue();
					if (metaWeblogAuth != null) {
						String blogId = metaWeblogAuth.getBlogId();
						String userName = metaWeblogAuth.getUserName();
						String password = metaWeblogAuth.getPassword();
						String apiUrl = metaWeblogAuth.getApiUrl();
						if (UEngineUtil.isNotEmpty(blogId) && UEngineUtil.isNotEmpty(userName) && UEngineUtil.isNotEmpty(password)) {
							try {
								Map<String, String> requestMap = new HashMap<String, String>();
								requestMap.put("apiUrl", apiUrl);
								requestMap.put("blogId", blogId);
								requestMap.put("userName", userName);
								requestMap.put("password", password);
								
								InputStream is = ProcessDesigner.getClientProxy().postStream("/processmanager/blog/blogCategories.jsp", requestMap);
								Object[] categorieList = (Object[]) GlobalContext.deserialize(is, String.class);
								
								Object[] selections = new Object[categorieList.length];
								Object[] values = new Object[categorieList.length];
								
								if (categorieList != null) {
									for (int i = 0; i < categorieList.length; i++) {
										Map<String, String> categorie = (Map<String, String>) categorieList[i];
										selections[i] = categorie.get("description");
										values[i] = categorie.get("title");
									}
								}

								((SelectInput) categoriesInputter).setSelections(selections);
								((SelectInput) categoriesInputter).setValues(values);
							} catch (Exception e1) {
								((SelectInput) categoriesInputter).setSelections(new String[] {});
								((SelectInput) categoriesInputter).setValues(new String[] {});
							}
							
						}
					}
				}
			}
		});
		
		FieldDescriptor blogTypeFd = getFieldDescriptor("BlogType");
		blogTypeInputter = blogTypeFd.getInputter();
		blogTypeInputter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (blogTypeInputter != null) {
					String key = (String) blogTypeInputter.getValue();
					if (apiUrl.containsKey(key)) {
						MetaWeblogAuth metaWeblogAuth = (MetaWeblogAuth) authInputter.getValue();
						metaWeblogAuth.setApiUrl(apiUrl.get(key));
						authInputter.setValue(metaWeblogAuth);
					} else {
						authInputter.setValue(new MetaWeblogAuth());
					}
				}
				((SelectInput) categoriesInputter).setSelections(new String[] {});
				((SelectInput) categoriesInputter).setValues(new String[] {});
			}
		});
		
		setFieldDisplayNames(MetaWeblogBlogPostingActivityDescriptor.class);
	}

}
