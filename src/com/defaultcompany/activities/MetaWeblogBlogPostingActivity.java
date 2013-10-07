package com.defaultcompany.activities;

import java.util.Map;

import org.metaworks.FieldDescriptor;
import org.metaworks.Type;
import org.metaworks.inputter.SelectInput;
import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ValidationContext;
import org.uengine.util.UEngineUtil;
/**
 * 사용자 인증은 Role 에서 가져오도록 변경해야 하고
 * <b>카테고리는 사용자별로 틀리기 때문에</b> 이 액티비티가 자체가 WIH를 가지는 휴먼 액티비티로 변경되야 한다.
 * 지금은 관리자 1명의 계정으로 회사 블로그에 올린다고 가정하고 작성 됨.
 * @author allbegray
 *
 */
public class MetaWeblogBlogPostingActivity extends DefaultActivity {
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public static void metaworksCallback_changeMetadata(Type type) {
		type.setFieldOrder(new String[] { "BlogType", "Auth", "Categories", "Post" });
		
		FieldDescriptor blogTypeFd = type.getFieldDescriptor("BlogType");
		blogTypeFd.setInputter(new SelectInput(new String[] { "Generic", "Naver" }, new String[] { "Generic", "Naver" }));
		
		FieldDescriptor categoriesFd = type.getFieldDescriptor("Categories");
		categoriesFd.setInputter(new SelectInput());
		
		type.removeFieldDescriptor("Cost");
		type.removeFieldDescriptor("StatusCode");
	}
	
	public MetaWeblogBlogPostingActivity() {
		super();
		setName("MetaWeblogBlogPostingActivity");
		setAuth(new MetaWeblogAuth());
		setPost(new MetaWeblogPost());
	}
	
	private String blogType;
		public String getBlogType() {
			return blogType;
		}
		public void setBlogType(String blogType) {
			this.blogType = blogType;
		}
		
	private MetaWeblogAuth auth;
		public MetaWeblogAuth getAuth() {
			return auth;
		}
		public void setAuth(MetaWeblogAuth auth) {
			this.auth = auth;
		}

	private String categories;
		public String getCategories() {
			return categories;
		}
		public void setCategories(String categories) {
			this.categories = categories;
		}
	
	private MetaWeblogPost post;
		public MetaWeblogPost getPost() {
			return post;
		}
		public void setPost(MetaWeblogPost post) {
			this.post = post;
		}
		
	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		MetaWeblogBlogPostingExecuter.executeActivity(instance, this);
		
		super.executeActivity(instance);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ValidationContext validate(Map options) {
		ValidationContext vc = super.validate(options);
		
		if (!UEngineUtil.isNotEmpty(this.getCategories())) {
			vc.add("select categories");
		}
		
		if (!UEngineUtil.isNotEmpty(getAuth().getApiUrl()) || !UEngineUtil.isNotEmpty(getAuth().getBlogId())
				|| !UEngineUtil.isNotEmpty(getAuth().getUserName()) || !UEngineUtil.isNotEmpty(getAuth().getPassword())) {
			vc.add("config auth");
		}
		
		if (!UEngineUtil.isNotEmpty(getPost().getTitle()) || !UEngineUtil.isNotEmpty(getPost().getDescription())) {
			vc.add("input post");
		}
		
		return vc;
	}

}
