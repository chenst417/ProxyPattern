package com.cn.study.proxypattern.staticproxypartten.simple;

import com.cn.study.proxypattern.staticproxypartten.Person;

public class Test {

	public static void main(String[] args) {
		Person person = new Father(new Son());
		person.findLove();
	}
}
