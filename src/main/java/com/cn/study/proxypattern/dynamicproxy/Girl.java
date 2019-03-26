package com.cn.study.proxypattern.dynamicproxy;

import com.cn.study.proxypattern.staticproxypartten.Person;

public class Girl implements Person {

	public void findLove() {
		System.out.println("找个高富帅嫁了。。。。。。");
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