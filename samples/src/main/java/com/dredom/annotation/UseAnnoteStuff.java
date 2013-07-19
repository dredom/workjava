package com.dredom.annotation;

import static java.lang.System.out;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class UseAnnoteStuff {

	public String stuff;

	/**
	 * Do it.
	 */
//	@AnnoteStuff
	@AnnoteStuff(stuffy = "Geronimo")
	public void doit() {
	    final class Local {};
	    Method thisMethod = Local.class.getEnclosingMethod();
	    String thisMethodName = thisMethod.getName();
		System.out.println("method name = " + thisMethodName);

		AnnoteStuff stuff = thisMethod.getAnnotation(AnnoteStuff.class);
		if (stuff != null) {
		    out.format("%s has annotation @AnnoteStuff(stuffy = %s) \n", thisMethodName, stuff.stuffy());
		}

		listAllAnnotations();

	}

	@Deprecated
	public void listAllAnnotations() {
	    out.println("All annotations:");
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
                out.format(" Method %s [ \n", method.getName());
                Annotation[] mannotes = method.getAnnotations();
                for (Annotation mannote : mannotes) {
                    out.format("%s, ", mannote);
                    if (mannote instanceof AnnoteStuff) {
                        out.format("AnnoteStuff stuffy=%s", ((AnnoteStuff)mannote).stuffy());
                    }
                    out.println();
                }
                out.println("]");
            }
        }
	}
}
