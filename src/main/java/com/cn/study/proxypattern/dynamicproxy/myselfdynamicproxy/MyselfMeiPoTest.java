package com.cn.study.proxypattern.dynamicproxy.myselfdynamicproxy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.cn.study.proxypattern.ProxyGenerator;
import com.cn.study.proxypattern.dynamicproxy.Girl;
import com.cn.study.proxypattern.staticproxypartten.Person;

public class MyselfMeiPoTest {

	public static void main(String[] args) {
		
		MyselfMeiPo myselfMeiPo = new MyselfMeiPo();
		
		Person person = (Person) myselfMeiPo.getInstance(new Girl());
		person.findLove();
		
		
		byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy1", new Class[] {Person.class});
		
		try {
			FileOutputStream fos = new FileOutputStream("E:/$Proxy1.class");
			
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
