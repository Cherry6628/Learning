package classes;
/*
 * 
Annotations:
	Annotations in Java are small labels added to code that give extra information to the compiler or runtime.
	They do not change the actual logic of the program.
	They are just instructions or metadata that tell Java how to treat the code.

Why We Use Annotations
    To give special instructions to the compiler or frameworks
    To help Java understand what we want
    To avoid mistakes (@Override)
    To mark outdated code (@Deprecated)


Retention (How long the annotation is kept)
Used only when creating your own annotations:\
    SOURCE → removed after compilation
    CLASS → kept in .class file (default)
    RUNTIME → available while the program runs


Target (Where you can put an annotation)
Examples:
    CLASS → on a class
    METHOD → on a method
    FIELD → on a variable
    PARAMETER → on a parameter
 */

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Annotation;

import java.lang.reflect.*;

public class AnnotationSample {
	@Account(uname="admin", pwd="admin@12341234")
	public static void main(String[]args) throws NoSuchMethodException, SecurityException {
		Class<?> annotationSample = AnnotationSample.class;
		Method main = annotationSample.getDeclaredMethod("main", String[].class);
		for(Annotation annotation: main.getDeclaredAnnotations()) {
//			System.out.println(annotation.);
			if(annotation instanceof Account a) {
				System.out.println(a.uname());
				System.out.println(a.pwd());
			}
		}
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) 
@interface Account{
	String uname() default "admin";
	String pwd();
}
