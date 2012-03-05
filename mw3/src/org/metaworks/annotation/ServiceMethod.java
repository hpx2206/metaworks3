package org.metaworks.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.metaworks.ServiceMethodContext;
import org.metaworks.dao.IDAO;

@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMethod {
	
	
	String when() 	default IDAO.WHEN_EVER;
	String where() 	default IDAO.WHERE_EVER;
	String how() 	default IDAO.HOW_EVER;
	String target() default ServiceMethodContext.TARGET_AUTO;
	
	
	boolean callByContent() default false;
	boolean needToConfirm() default false;	//shows 'Are you sure to do this....?'
	boolean clientSide() 	default false;	//
	
	String[] payload() default {};
	String[] except() default {};
	
	String[] keyBinding() default "";
	
	String mouseBinding() default "";
	
	boolean inContextMenu() default false;
}
