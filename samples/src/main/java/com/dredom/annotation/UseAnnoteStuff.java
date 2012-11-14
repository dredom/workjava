package com.dredom.annotation;

import static java.lang.System.out;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class UseAnnoteStuff {

	public String stuff;

	/**
	 * Do it.
	 */
	@AnnoteStuff(stuffy = "Geronimo")
	public void doit() {
		System.out.println("Hello stuff");

		Annotation[] annotes = getClass().getAnnotations();

		if (annotes.length > 0) {
			out.print("Class: " + getClass().getName() + "[");
			for (Annotation annote : annotes) {
				System.out.print(annote.toString());
			}
			out.println("]");
		}
		for (Method method : getClass().getMethods() ) {
			if (method.getAnnotations().length > 0) {
				out.format(" Method %s [", method.getName());
				Annotation[] mannotes = method.getAnnotations();
				for (Annotation mannote : mannotes) {
					System.out.println(mannote.toString());
					out.format("%s, ", mannote);
				}
				out.println("]");
			}
		}

	}
}
