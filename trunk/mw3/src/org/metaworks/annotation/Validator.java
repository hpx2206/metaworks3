package org.metaworks.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Validator {
	String name() default "";
	String message() default "";
	
	String[] events() default {};
	String[] options() default {};
}