package com.dredom;

public class OutputterSysout implements Outputter {

	public void output(Object stuff) {
		System.out.println(stuff.toString());
	}

}
