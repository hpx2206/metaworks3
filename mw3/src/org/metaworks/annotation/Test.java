package org.metaworks.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	
	String[] value() default {};
	String[] next() default {};
	String testName() default "";

	String[] instruction() default {};
}
