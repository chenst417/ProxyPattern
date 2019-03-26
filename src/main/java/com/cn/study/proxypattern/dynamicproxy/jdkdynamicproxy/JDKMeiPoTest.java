package com.cn.study.proxypattern.dynamicproxy.jdkdynamicproxy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.cn.study.proxypattern.ProxyGenerator;
import com.cn.study.proxypattern.dynamicproxy.Girl;
import com.cn.study.proxypattern.staticproxypartten.Person;

public class JDKMeiPoTest {

	public static void main(String[] args) {
		
		JDKMeiPo jdkMeiPo = new JDKMeiPo();
		
		Person person = (Person) jdkMeiPo.getInstance(new Girl());
		person.findLove();
		
		
		byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[] {Person.class});
		
		try {
			FileOutputStream fos = new FileOutputStream("E:/$Proxy.class");
			
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
