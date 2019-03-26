package com.cn.study.proxypattern.staticproxypartten.simple;

import com.cn.study.proxypattern.staticproxypartten.Person;

public class Son implements Person {

	public void findLove() {
		System.out.println("Son:找个漂亮的媳妇。");
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
