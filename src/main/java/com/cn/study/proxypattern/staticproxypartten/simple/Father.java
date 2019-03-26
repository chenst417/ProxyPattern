package com.cn.study.proxypattern.staticproxypartten.simple;

import com.cn.study.proxypattern.staticproxypartten.Person;

public class Father implements Person {

	private Person person;

	public Father(Person person) {
		this.person = person;
	}

	public void findLove() {
		System.out.println("父亲开始物色对象。");
		this.person.findLove();
		System.out.println("准备结婚。");
	}

	@Override
	public String a(String a, int b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int b(String a, int b) {
		// TODO Auto-generated method stub
		return 0;
	}

}
