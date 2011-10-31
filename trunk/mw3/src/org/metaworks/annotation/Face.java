package org.metaworks.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Face {
	public String displayName() default "";
	public String ejsPath() default "";
	public String ejsPathForArray() default "";
	
	public String[] options() default {};
	public String[] values() default {};
	
	public String[] ejsPathMappingByContext() default {};
	
}
