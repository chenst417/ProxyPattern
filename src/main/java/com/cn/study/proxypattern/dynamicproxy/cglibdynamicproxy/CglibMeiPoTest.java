package com.cn.study.proxypattern.dynamicproxy.cglibdynamicproxy;

import com.cn.study.proxypattern.dynamicproxy.Girl;
import com.cn.study.proxypattern.staticproxypartten.Person;

public class CglibMeiPoTest {

	public static void main(String[] args) {
		
		CglibMeiPo p = new CglibMeiPo();
		
		Person person = (Person) p.getInstance(new Girl().getClass());
		
		person.findLove();
	}
	
}
